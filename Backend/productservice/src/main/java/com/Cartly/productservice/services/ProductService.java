package com.Cartly.productservice.services;

import com.Cartly.productservice.DTO.*;
import com.Cartly.productservice.model.*;
import com.Cartly.productservice.repo.*;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public Product createProduct(
      ProductRequest request) throws Exception {

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
    productRepository.findAll().forEach(product -> {
      list.add(
          ProductResponse.builder()
              .id(product.getId())
              .title(product.getTitle())
              .category(product.getCategory())
              .brand(product.getBrand())
              .price(product.getPrice())
              .salePrice(product.getSalePrice())
              .totalStock(product.getTotalStock())
              .image(product.getImage())
              .description(product.getDescription())
              .build());
    });
    return list;
  }
}
