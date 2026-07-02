package com.Cartly.cartservice.repo;

import com.Cartly.cartservice.model.Cart;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, UUID> {

  @EntityGraph(attributePaths = "items")
  Optional<Cart> findByUserIdAndStatus(String userId, Cart.CartStatus status);
}
