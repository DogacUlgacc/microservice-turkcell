package com.turkcell.product_service.application.dtos.response;
import com.turkcell.product_service.domain.valueobjects.Currency;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        Currency currency,
        int stockQuantity
) {
}
