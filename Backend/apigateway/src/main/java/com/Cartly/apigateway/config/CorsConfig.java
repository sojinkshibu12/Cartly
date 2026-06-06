package com.Cartly.apigateway.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

  private static final List<String> ALLOWED_ORIGINS = List.of(
      "http://localhost:*",
      "http://127.0.0.1:*",
      "http://[::1]:*",
      "http://localhost:5173",
      "http://127.0.0.1:5173");

  private static final List<String> ALLOWED_METHODS = List.of(
      "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS");

  private static final List<String> EXPOSED_HEADERS = List.of(
      "Authorization",
      "Content-Disposition");

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = buildCorsConfiguration();

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return source;
  }

  private CorsConfiguration buildCorsConfiguration() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOriginPatterns(ALLOWED_ORIGINS);
    config.setAllowedMethods(ALLOWED_METHODS);
    config.setAllowedHeaders(List.of("*"));
    config.setExposedHeaders(EXPOSED_HEADERS);
    config.setAllowCredentials(true);
    config.setMaxAge(3600L);
    return config;
  }
}
