package com.turkcell.product_service.infrastructure.repositories;

import com.turkcell.product_service.application.ports.ProductServicePort;
import com.turkcell.product_service.domain.entities.Product;
import com.turkcell.product_service.domain.repositories.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Product.ProductId, Product> productStore = new ConcurrentHashMap<>();


    @Override
    public Product save(Product product) {
        productStore.put(product.getId(), product);
        return product;
    }

    @Override
    public Optional<Product> findById(Product.ProductId id) {
        return Optional.ofNullable(productStore.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productStore.values());
    }

    //TODO:: tekrar bak
    @Override
    public List<Product> findByNameContaining(String name) {
        return productStore.values().stream().filter(p -> p.getName().toLowerCase().contains(name.toLowerCase())).toList();
    }

    @Override
    public List<Product> findInStockProducts() {
        return productStore.values().stream().filter(Product::isInStock).toList();
    }

    @Override
    public List<Product> findOutOfStockProducts() {
        return productStore.values().stream().filter(p -> !p.isInStock()).toList();
    }

    @Override
    public List<Product> findByPriceRange(double minPrice, double maxPrice) {
        return productStore.values().stream().filter(p -> p.getPrice().getAmount().doubleValue() >= minPrice && p.getPrice().getAmount().doubleValue() <= maxPrice).toList();
    }

    @Override
    public void deleteById(Product.ProductId id) {
        productStore.remove(id);
    }

    @Override
    public boolean existsById(Product.ProductId id) {
        return productStore.containsKey(id);
    }

    @Override
    public long count() {
        return productStore.size();

    }

    @Override
    public long countInStockProducts() {
        return productStore.values().stream().filter(Product::isInStock).count();

    }
}
