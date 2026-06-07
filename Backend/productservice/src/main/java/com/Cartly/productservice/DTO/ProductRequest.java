package com.Cartly.productservice.DTO;

import lombok.Data;

@Data
public class ProductRequest {

  private String title;
  private String category;
  private String brand;
  private Double price;
  private Double salePrice;
  private Integer totalStock;
  private String description;
  private String image;

}
