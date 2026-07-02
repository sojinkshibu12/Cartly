package com.Cartly.cartservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;

@Builder
public record CartResponse(
    String id,
    @JsonProperty("_id") String legacyId,
    String userId,
    List<CartItemResponse> items,
    int totalItems,
    BigDecimal totalAmount) {
}
