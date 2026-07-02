package com.Cartly.productservice.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
  private String id;

  @JsonProperty("_id")
  private String legacyId;

  private String title;
  private String category;
  private String brand;
  private Double price;
  private Double salePrice;
  private Integer totalStock;
  private String description;
  private String image;
}
