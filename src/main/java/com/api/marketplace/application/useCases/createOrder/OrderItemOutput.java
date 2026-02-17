package com.api.marketplace.application.useCases.createOrder;

import java.math.BigDecimal;

public record OrderItemOutput(
        int quantity,
        BigDecimal unitPrice,
        BigDecimal totalPrice,
        String name
) {
}
