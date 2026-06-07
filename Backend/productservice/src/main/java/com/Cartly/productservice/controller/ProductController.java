package com.Cartly.productservice.controller;

import com.Cartly.productservice.DTO.*;
import com.Cartly.productservice.model.Product;
import com.Cartly.productservice.services.*;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;
  private final MinioService minioService;

  @PostMapping
  public ProductResponse createProduct(
      @RequestBody ProductRequest request) throws Exception {

    System.out.println("CREATE PRODUCT HIT");
    Product product = productService.createProduct(request);

    return ProductResponse.builder()

        .title(product.getTitle())
        .category(product.getCategory())
        .brand(product.getBrand())
        .price(product.getPrice())
        .salePrice(product.getSalePrice())
        .totalStock(product.getTotalStock())
        .image(product.getImage())
        .build();
  }

  @PostMapping("/upload")
  public Map<String, Object> uploadImage(
      @RequestPart("image") MultipartFile image) throws Exception {
    System.out.println("UPLOAD HIT");

    String objectName = minioService.upload(image);

    return Map.of(
        "success", true,
        "uri", minioService.getImageUrl(objectName));
  }

  @GetMapping
  public List<ProductResponse> fetchall() {
    List<ProductResponse> products = new ArrayList<>();
    products = productService.fetchAll();
    System.out.println(products);
    return products;
  }

}
