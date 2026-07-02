package com.Cartly.cartservice.dto;

import lombok.Builder;

@Builder
public record CartItemResponse(
    String productId,
    String title,
    String image,
    Double price,
    Double salePrice,
    int quantity) {
}
