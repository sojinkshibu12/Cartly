package com.Cartly.Authservice.security;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Cartly.Authservice.model.*;

public class AppUserPrincipal implements UserDetails {

  private final User user;

  public AppUserPrincipal(User user) {
    this.user = user;
  }

  public Optional<User> getUser() {
    return Optional.of(this.user);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getEmail();
  }
}
