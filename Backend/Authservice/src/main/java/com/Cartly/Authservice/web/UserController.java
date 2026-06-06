package com.Cartly.Authservice.web;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Cartly.Authservice.model.User;
import com.Cartly.Authservice.security.*;
import com.Cartly.Authservice.services.UserService;
import com.Cartly.Authservice.web.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.server.ResponseStatusException;

@Validated
@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;
  private final AuthenticationManager authenticationManager;

  public UserController(UserService userService, AuthenticationManager authenticationManager) {
    this.userService = userService;
    this.authenticationManager = authenticationManager;
  }

  @GetMapping("/csrf")
  public Map<String, String> csrf(CsrfToken token) {
    return Map.of(
        "headerName", token.getHeaderName(),
        "parameterName", token.getParameterName(),
        "token", token.getToken());
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
    User user = userService.register(request);
    return AuthResponse.of(true, "Registration successful", user);
  }

  @PostMapping("/login")
  public AuthResponse login(
      @Valid @RequestBody LoginRequest request,
      HttpServletRequest httpRequest) {
    try {
      Authentication authentication = authenticationManager.authenticate(
          UsernamePasswordAuthenticationToken
              .unauthenticated(
                  request.email(),
                  request.password()));
      SecurityContext context = SecurityContextHolder.createEmptyContext();
      context.setAuthentication(authentication);
      SecurityContextHolder.setContext(context);
      HttpSession session = httpRequest.getSession(true);
      session.setAttribute(
          "SPRING_SECURITY_CONTEXT",
          context);
      AppUserPrincipal principal = (AppUserPrincipal) authentication.getPrincipal();
      Optional<User> optionalUser = principal.getUser();
      if (optionalUser.isPresent()) {
        User user = optionalUser.get();
        return AuthResponse.of(true, "Login successful", user);
      }

    } catch (org.springframework.security.core.AuthenticationException e) {
      return new AuthResponse(false, "Invalid username or password", null);
    }
    return new AuthResponse(false, "Login failed", null);
  }

  @GetMapping("/admin/dashboard")
  public Map<String, String> adminDashboard(Principal principal) {
    return Map.of(
        "message", "Admin endpoint reached",
        "principal", principal.getName());
  }

  @GetMapping("/check-auth")
  public AuthResponse checkAuth(Authentication authentication) {
    if (authentication == null || !(authentication.getPrincipal() instanceof AppUserPrincipal appUserPrincipal)) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not authenticated");
    }
    User user = appUserPrincipal.getUser()
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User is not authenticated"));
    return AuthResponse.of(true, "Authenticated", user);
  }
}
