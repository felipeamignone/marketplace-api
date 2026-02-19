package com.api.marketplace.application.order.commands;

import java.math.BigDecimal;

public record OrderItemOutput(
        int quantity,
        BigDecimal unitPrice,
        BigDecimal totalPrice,
        String name
) {
}
