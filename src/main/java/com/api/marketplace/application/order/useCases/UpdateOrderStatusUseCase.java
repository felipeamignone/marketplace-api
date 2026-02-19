package com.api.marketplace.application.order.useCases;

import com.api.marketplace.domain.order.gateway.OrderRepositoryGateway;
import com.api.marketplace.domain.order.model.Order;
import com.api.marketplace.domain.order.model.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateOrderStatusUseCase {
    private final OrderRepositoryGateway orderRepositoryGateway;

    public UpdateOrderStatusUseCase(OrderRepositoryGateway orderRepositoryGateway) {
        this.orderRepositoryGateway = orderRepositoryGateway;
    }

    public void execute (UUID orderId, OrderStatus newStatus) {
        Order foundedOrder = orderRepositoryGateway.findById(orderId);
        foundedOrder.updateStatus(newStatus);

        this.orderRepositoryGateway.save(foundedOrder);
    }
}
