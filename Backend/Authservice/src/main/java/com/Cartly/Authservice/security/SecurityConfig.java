package com.Cartly.Authservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(
      HttpSecurity http,
      CorsConfigurationSource corsConfigurationSource)
      throws Exception {

    http
        .logout(logout -> logout
            .logoutUrl("/api/users/logout")
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .deleteCookies("JSESSIONID")
            .logoutSuccessHandler(
                (request, response, authentication) -> {
                  response.setStatus(HttpServletResponse.SC_OK);
                }))
        .cors(cors -> cors.configurationSource(corsConfigurationSource))
        .csrf(csrf -> csrf
            .csrfTokenRepository(
                CookieCsrfTokenRepository.withHttpOnlyFalse())
            .ignoringRequestMatchers(
                "/api/users/register",
                "/api/users/login",
                "/api/users/check-auth",
                "/api/users/logout",
                "/api/users/me"))
        .formLogin(form -> form.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .requestMatchers(
                "/api/users/register",
                "/api/users/login",
                "/api/users/check-auth",
                "/api/users/logout",
                "/login",
                "/error",
                "/api/users/me")
            .permitAll()
            .anyRequest().authenticated());

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
      throws Exception {
    return configuration.getAuthenticationManager();
  }
}
