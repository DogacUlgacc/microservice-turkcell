package com.turkcell.product_service.application.ports;

import com.turkcell.product_service.application.dtos.request.CreateProductRequest;
import com.turkcell.product_service.application.dtos.response.ProductListResponse;
import com.turkcell.product_service.application.dtos.response.ProductResponse;
import com.turkcell.product_service.application.dtos.request.UpdateProductRequest;

import java.util.List;
import java.util.UUID;

public interface ProductServicePort  {
    ProductResponse createProduct(CreateProductRequest request);
    ProductResponse getProductById(UUID id);
    List<ProductListResponse> getAllProducts();
    ProductResponse updateProduct(UUID id, UpdateProductRequest request);
    void deleteProduct(UUID id);
}
