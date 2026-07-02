package com.Cartly.cartservice.dto;

import lombok.Builder;

@Builder
public record ApiResponse<T>(
    boolean success,
    String message,
    T data) {
}
