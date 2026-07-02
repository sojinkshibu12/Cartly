package com.Cartly.cartservice.repo;

import com.Cartly.cartservice.model.ProductSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSnapshotRepository extends JpaRepository<ProductSnapshot, String> {
}
