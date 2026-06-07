package com.Cartly.productservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String image;

  private String title;

  private String category;

  private String brand;

  private Double price;

  private Double salePrice;

  private Integer totalStock;

  private String description;
}
