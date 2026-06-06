package com.Cartly.Authservice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Cartly.Authservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);

}
