package com.api.marketplace.domain.model.order;

import java.math.BigDecimal;

public record OrderItem(String name, BigDecimal unitPrice, int quantity) {
    public OrderItem {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Item should have at least one quantity");
        }

        if (unitPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Unit price should be greater than zero");
        }

    }

    public BigDecimal calculateTotalPrice() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
