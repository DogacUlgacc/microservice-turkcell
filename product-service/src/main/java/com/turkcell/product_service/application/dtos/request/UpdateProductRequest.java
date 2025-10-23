package com.turkcell.product_service.application.dtos.request;

import com.turkcell.product_service.domain.valueobjects.Currency;

import java.math.BigDecimal;

public record UpdateProductRequest(String name, String description, BigDecimal price, String currency, int stockQuantity) {
}
