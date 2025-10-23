package com.turkcell.product_service.application.dtos.request;

import com.turkcell.product_service.domain.valueobjects.Currency;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;


public record CreateProductRequest(
        @NotBlank String name,
        @NotBlank String description,
        @Positive BigDecimal price,
        @NotNull String currency,
        @Min(0) int stockQuantity
) {}
