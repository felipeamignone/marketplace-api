package com.api.marketplace.application.order.commands;

public record OrderItemInput(
        int quantity,
        String name
) {
}
