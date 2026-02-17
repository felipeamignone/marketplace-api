package com.api.marketplace.domain.gateway;

import com.api.marketplace.domain.model.order.Order;

import java.util.UUID;

public interface OrderRepositoryGateway {
    Order findByExternalId(UUID externalId);
    Order save(Order newOrder);
}
