package com.api.marketplace.application.useCases.createOrder;


import java.util.List;
import java.util.UUID;

public record CreateOrderInput(
        UUID storeId,
        List<OrderItemInput> items
) {}
