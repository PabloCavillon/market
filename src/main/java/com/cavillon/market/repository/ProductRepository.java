package com.cavillon.market.repository;

import com.cavillon.market.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findByNameIgnoreCaseOrCodeIgnoreCaseOrIdIgnoreCase(String name, String code, String id);
    boolean existsByNameIgnoreCaseOrCodeIgnoreCase(String name, String code);
}
