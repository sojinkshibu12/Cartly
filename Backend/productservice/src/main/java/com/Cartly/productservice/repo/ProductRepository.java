package com.Cartly.productservice.repo;

import com.Cartly.productservice.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
    extends JpaRepository<Product, String> {
}
