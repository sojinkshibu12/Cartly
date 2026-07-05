package com.Cartly.cartservice.service;

import com.Cartly.cartservice.dto.CartItemResponse;
import com.Cartly.cartservice.dto.CartRequest;
import com.Cartly.cartservice.dto.CartResponse;
import com.Cartly.cartservice.model.Cart;
import com.Cartly.cartservice.model.CartItem;
import com.Cartly.cartservice.model.ProductSnapshot;
import com.Cartly.cartservice.repo.CartRepository;
import com.Cartly.cartservice.repo.ProductSnapshotRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CartService {

  private final CartRepository cartRepository;
  private final ProductSnapshotRepository productSnapshotRepository;

  @Transactional
  public CartResponse addToCart(CartRequest request) {
    ProductSnapshot product = getProductOrThrow(request.getProductId());
    validateRequestedQuantity(request.getQuantity(), product.getTotalStock());

    Cart cart = cartRepository.findByUserIdAndStatus(request.getUserId(), Cart.CartStatus.ACTIVE)
        .orElseGet(() -> Cart.builder()
            .userId(request.getUserId())
            .items(new ArrayList<>())
            .build());

    CartItem existingItem = cart.findItemByProductId(request.getProductId());
    int updatedQuantity = request.getQuantity();

    if (existingItem != null) {
      updatedQuantity = existingItem.getQuantity() + request.getQuantity();
      validateRequestedQuantity(updatedQuantity, product.getTotalStock());
      existingItem.setQuantity(updatedQuantity);
      syncItemFromProduct(existingItem, product);
    } else {
      cart.addItem(buildCartItem(product, request.getQuantity()));
    }

    return toResponse(cartRepository.save(cart));
  }

  @Transactional(readOnly = true)
  public CartResponse getCartByUserId(String userId) {
    return cartRepository.findByUserIdAndStatus(userId, Cart.CartStatus.ACTIVE)
        .map(this::toResponse)
        .orElseGet(() -> emptyCart(userId));
  }

  @Transactional
  public CartResponse updateCartQuantity(CartRequest request) {
    ProductSnapshot product = getProductOrThrow(request.getProductId());
    validateRequestedQuantity(request.getQuantity(), product.getTotalStock());

    Cart cart = getActiveCartOrThrow(request.getUserId());
    CartItem item = Optional.ofNullable(cart.findItemByProductId(request.getProductId()))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart item not found"));

    item.setQuantity(request.getQuantity());
    syncItemFromProduct(item, product);

    return toResponse(cartRepository.save(cart));
  }

  @Transactional
  public CartResponse deleteCartItem(String userId, String productId) {
    Cart cart = getActiveCartOrThrow(userId);
    CartItem item = Optional.ofNullable(cart.findItemByProductId(productId))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart item not found"));

    cart.removeItem(item);

    return toResponse(cartRepository.save(cart));
  }

  private Cart getActiveCartOrThrow(String userId) {
    return cartRepository.findByUserIdAndStatus(userId, Cart.CartStatus.ACTIVE)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found"));
  }

  private ProductSnapshot getProductOrThrow(String productId) {
    return productSnapshotRepository.findById(productId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
  }

  private static final int MAX_QUANTITY = 99;

  private void validateRequestedQuantity(int quantity, Integer totalStock) {
    if (totalStock != null && quantity > totalStock) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requested quantity exceeds stock");
    }
    if (quantity > MAX_QUANTITY) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Maximum quantity per item is " + MAX_QUANTITY);
    }
  }

  private CartItem buildCartItem(ProductSnapshot product, int quantity) {
    return CartItem.builder()
        .productId(product.getId())
        .productName(product.getTitle())
        .price(BigDecimal.valueOf(product.getPrice()))
        .quantity(quantity)
        .build();
  }

  private void syncItemFromProduct(CartItem item, ProductSnapshot product) {
    item.setProductName(product.getTitle());
    item.setPrice(BigDecimal.valueOf(product.getPrice()));
  }

  private CartResponse toResponse(Cart cart) {
    BigDecimal totalAmount = BigDecimal.ZERO;
    int totalItems = 0;

    var items = cart.getItems().stream()
        .map(item -> {
          ProductSnapshot product = productSnapshotRepository.findById(item.getProductId()).orElse(null);
          double salePrice = product != null && product.getSalePrice() != null ? product.getSalePrice() : 0D;
          String image = product != null ? product.getImage() : null;
          String title = product != null ? product.getTitle() : item.getProductName();

          Integer stock = product != null ? product.getTotalStock() : null;

          return CartItemResponse.builder()
              .productId(item.getProductId())
              .title(title)
              .image(image)
              .price(item.getPrice().doubleValue())
              .salePrice(salePrice)
              .quantity(item.getQuantity())
              .totalStock(stock)
              .build();
        })
        .toList();

    for (CartItem item : cart.getItems()) {
      totalItems += item.getQuantity();
      totalAmount = totalAmount.add(item.getSubtotal());
    }

    String cartId = cart.getId() != null ? cart.getId().toString() : null;

    return CartResponse.builder()
        .id(cartId)
        .legacyId(cartId)
        .userId(cart.getUserId())
        .items(items)
        .totalItems(totalItems)
        .totalAmount(totalAmount)
        .build();
  }

  private CartResponse emptyCart(String userId) {
    return CartResponse.builder()
        .id(null)
        .legacyId(null)
        .userId(userId)
        .items(new ArrayList<>())
        .totalItems(0)
        .totalAmount(BigDecimal.ZERO)
        .build();
  }
}
