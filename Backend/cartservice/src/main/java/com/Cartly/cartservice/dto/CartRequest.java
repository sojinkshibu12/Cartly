package com.Cartly.cartservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequest {

  @NotBlank
  private String userId;

  @NotBlank
  private String productId;

  @Min(1)
  private int quantity;
}
