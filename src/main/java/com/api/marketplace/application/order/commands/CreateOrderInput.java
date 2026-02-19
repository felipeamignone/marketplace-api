package com.api.marketplace.application.order.commands;


import java.util.List;
import java.util.UUID;

public record CreateOrderInput(
        UUID storeId,
        List<OrderItemInput> items
) {}
