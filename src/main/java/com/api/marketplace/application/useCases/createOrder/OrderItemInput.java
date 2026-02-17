package com.api.marketplace.application.useCases.createOrder;

import java.math.BigDecimal;

public record OrderItemInput(
        int quantity,
        BigDecimal unitPrice,
        String name
) {
}
