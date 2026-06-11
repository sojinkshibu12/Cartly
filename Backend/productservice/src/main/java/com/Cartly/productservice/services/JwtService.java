package com.Cartly.productservice.services;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

  private static final String SECRET = "my-super-secret-key-my-super-secret-key";

  private Key getSigningKey() {
    return Keys.hmacShaKeyFor(
        SECRET.getBytes(StandardCharsets.UTF_8));
  }

  public String extractUsername(String token) {
    return Jwts.parser()
        .verifyWith((SecretKey) getSigningKey())
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getSubject();
  }

  public String extractRole(String token) {
    return Jwts.parser()
        .verifyWith((SecretKey) getSigningKey())
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .get("role", String.class);
  }

}
