package com.Cartly.productservice.controller;

import com.Cartly.productservice.DTO.ProductRequest;
import com.Cartly.productservice.DTO.ProductResponse;
import com.Cartly.productservice.model.Product;
import com.Cartly.productservice.services.MinioService;
import com.Cartly.productservice.services.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;
  private final MinioService minioService;

  @PostMapping
  public ProductResponse createProduct(@RequestBody ProductRequest request) throws Exception {

    System.out.println("CREATE PRODUCT HIT");
    Product product = productService.createProduct(request);

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

  @PostMapping("/upload")
  public Map<String, Object> uploadImage(@RequestPart("image") MultipartFile image) throws Exception {
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

  @GetMapping("/{id}")
  public Map<String, Object> fetchProductDetails(@PathVariable String id) {
    return Map.of(
        "success", true,
        "data", productService.fetchById(id));
  }
}
