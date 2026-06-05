package com.Cartly.apigateway.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsDebugFilter extends OncePerRequestFilter {

  private static final Logger log = LoggerFactory.getLogger(CorsDebugFilter.class);

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    log.info(
        "CORS debug: method={}, path={}, origin={}, acrm={}, acrh={}, host={}",
        request.getMethod(),
        request.getRequestURI(),
        request.getHeader("Origin"),
        request.getHeader("Access-Control-Request-Method"),
        request.getHeader("Access-Control-Request-Headers"),
        request.getHeader("Host"));

    filterChain.doFilter(request, response);
  }
}
