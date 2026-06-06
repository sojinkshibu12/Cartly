package com.Cartly.Authservice.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.Cartly.Authservice.model.*;
import com.Cartly.Authservice.model.UserRole;
import com.Cartly.Authservice.repo.UserRepository;
import com.Cartly.Authservice.web.dto.*;

@Service
public class UserService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository repo;

  public UserService(UserRepository repo, PasswordEncoder passwordEncoder) {
    this.repo = repo;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public User register(RegisterRequest request) {
    Optional<User> existingUser = repo.findByEmail(request.email().trim().toLowerCase());
    if (existingUser.isPresent()) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already registered");
    }

    User user = User.builder()
        .name(request.name().trim())
        .email(request.email().trim().toLowerCase())
        .password(passwordEncoder.encode(request.password()))
        .role(UserRole.CUSTOMER)
        .build();

    return repo.save(user);
  }

  @Transactional(readOnly = true)
  public User getByEmail(String email) {
    return repo.findByEmail(email.trim().toLowerCase())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
  }
}
