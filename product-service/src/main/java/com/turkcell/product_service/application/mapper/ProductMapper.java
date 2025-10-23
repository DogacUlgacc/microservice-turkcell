package com.turkcell.product_service.application.mapper;

import com.turkcell.product_service.application.dtos.request.CreateProductRequest;
import com.turkcell.product_service.application.dtos.request.UpdateProductRequest;
import com.turkcell.product_service.application.dtos.response.ProductListResponse;
import com.turkcell.product_service.application.dtos.response.ProductResponse;
import com.turkcell.product_service.domain.entities.Product;
import com.turkcell.product_service.domain.valueobjects.Currency;
import com.turkcell.product_service.domain.valueobjects.Price;
import com.turkcell.product_service.domain.valueobjects.Stock;
import org.springframework.stereotype.Component;



@Component
public class ProductMapper {

    public Product toDomain(CreateProductRequest request) {
        return Product.create(
                request.name(),
                request.description(),
                new Price(request.price(), Currency.fromCode(request.currency())), // String -> Currency
                new Stock(request.stockQuantity())
        );
    }

    public Product toDomain(UpdateProductRequest request) {
        return Product.create(
                request.name(),
                request.description(),
                new Price(request.price(), Currency.fromCode(String.valueOf(request.currency()))),
                new Stock(request.stockQuantity())
        );
    }

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId().getValue(),
                product.getName(),
                product.getDescription(),
                product.getPrice().getAmount(),
                product.getPrice().getCurrency(),
                product.getStock().getQuantity()
        );
    }

    public ProductListResponse toListResponse(Product product) {
        return new ProductListResponse(
                product.getId().getValue(),
                product.getName(),
                product.getDescription(),
                product.getPrice().getAmount(),
                product.getPrice().getCurrency(),
                product.getStock().getQuantity()
        );
    }
}
