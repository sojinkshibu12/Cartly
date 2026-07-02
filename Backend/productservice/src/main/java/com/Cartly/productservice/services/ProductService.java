package com.Cartly.productservice.services;

import com.Cartly.productservice.DTO.ProductRequest;
import com.Cartly.productservice.DTO.ProductResponse;
import com.Cartly.productservice.model.Product;
import com.Cartly.productservice.repo.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public Product createProduct(ProductRequest request) throws Exception {

    Product product = Product.builder()
        .title(request.getTitle())
        .category(request.getCategory())
        .brand(request.getBrand())
        .price(request.getPrice())
        .salePrice(request.getSalePrice())
        .totalStock(request.getTotalStock())
        .image(request.getImage())
        .description(request.getDescription())
        .build();

    return productRepository.save(product);
  }

  public List<ProductResponse> fetchAll() {
    List<ProductResponse> list = new ArrayList<>();
    productRepository.findAll().forEach(product -> list.add(toResponse(product)));
    return list;
  }

  public ProductResponse fetchById(String id) {
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

    return toResponse(product);
  }

  private ProductResponse toResponse(Product product) {
    return ProductResponse.builder()
        .id(product.getId())
        .legacyId(product.getId())
        .title(product.getTitle())
        .category(product.getCategory())
        .brand(product.getBrand())
        .price(product.getPrice())
        .salePrice(product.getSalePrice())
        .totalStock(product.getTotalStock())
        .image(product.getImage())
        .description(product.getDescription())
        .build();
  }
}
