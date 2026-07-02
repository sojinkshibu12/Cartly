package com.Cartly.cartservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class ProductSnapshot {

  @Id
  private String id;

  private String image;

  private String title;

  private Double price;

  private Double salePrice;

  private Integer totalStock;
}
