package com.api.marketplace.application.useCases.createOrder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CreateOrderOutput(
        UUID externalId,
        UUID storeId,
        BigDecimal totalPrice,
        List<OrderItemOutput> items
) {
}
