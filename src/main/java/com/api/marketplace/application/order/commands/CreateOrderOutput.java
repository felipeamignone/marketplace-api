package com.api.marketplace.application.order.commands;

import com.api.marketplace.domain.order.model.OrderStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CreateOrderOutput(
        UUID id,
        UUID storeId,
        OrderStatus status,
        BigDecimal totalPrice,
        List<OrderItemOutput> items
) {
}
