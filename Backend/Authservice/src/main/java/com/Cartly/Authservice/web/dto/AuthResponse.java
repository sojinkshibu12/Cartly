package com.Cartly.Authservice.web.dto;

import com.Cartly.Authservice.model.User;
import com.Cartly.Authservice.model.UserRole;

public record AuthResponse(
    boolean success,
    String message,
    UserDto user) {

  public static AuthResponse of(boolean success, String message, User user) {
    return new AuthResponse(success, message, new UserDto(user.getId(), user.getName(), user.getRole()));
  }

  public record UserDto(
      Long id,
      String name,
      UserRole role) {
  }
}
