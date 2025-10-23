package com.turkcell.product_service.application.ports;

import com.turkcell.product_service.domain.entities.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepositoryPort {
    List<Product> findAll();
    Optional<Product> findById(UUID id);
    void save(Product product);
    void deleteById(UUID id);
}
