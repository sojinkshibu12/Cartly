package com.Cartly.cartservice.controller;

import com.Cartly.cartservice.dto.ApiResponse;
import com.Cartly.cartservice.dto.CartRequest;
import com.Cartly.cartservice.dto.CartResponse;
import com.Cartly.cartservice.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shop/cart")
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;

  @PostMapping("/add")
  public ApiResponse<CartResponse> addToCart(@Valid @RequestBody CartRequest request) {
    return ApiResponse.<CartResponse>builder()
        .success(true)
        .message("Product added to cart")
        .data(cartService.addToCart(request))
        .build();
  }

  @GetMapping("/get/{userId}")
  public ApiResponse<CartResponse> getCart(@PathVariable String userId) {
    return ApiResponse.<CartResponse>builder()
        .success(true)
        .message("Cart fetched successfully")
        .data(cartService.getCartByUserId(userId))
        .build();
  }

  @PutMapping("/update-cart")
  public ApiResponse<CartResponse> updateCart(@Valid @RequestBody CartRequest request) {
    return ApiResponse.<CartResponse>builder()
        .success(true)
        .message("Cart updated successfully")
        .data(cartService.updateCartQuantity(request))
        .build();
  }

  @DeleteMapping("/{userId}/{productId}")
  public ApiResponse<CartResponse> deleteCartItem(@PathVariable String userId, @PathVariable String productId) {
    return ApiResponse.<CartResponse>builder()
        .success(true)
        .message("Cart item deleted successfully")
        .data(cartService.deleteCartItem(userId, productId))
        .build();
  }
}
