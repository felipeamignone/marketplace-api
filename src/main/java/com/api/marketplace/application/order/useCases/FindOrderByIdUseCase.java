package com.api.marketplace.application.order.useCases;

import com.api.marketplace.application.order.commands.OrderItemOutput;
import com.api.marketplace.application.order.commands.OrderOutput;
import com.api.marketplace.domain.order.gateway.OrderRepositoryGateway;
import com.api.marketplace.domain.order.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FindOrderByIdUseCase {
    private final OrderRepositoryGateway repository;

    public FindOrderByIdUseCase(OrderRepositoryGateway repository) {
        this.repository = repository;
    }

    public OrderOutput execute (UUID orderId) {
        Order order = repository.findById(orderId);

        List<OrderItemOutput> itemOutputList = order.getItems().stream()
                .map(item -> new OrderItemOutput(
                        item.quantity(),
                        item.unitPrice(),
                        item.calculateTotalPrice(),
                        item.name()
                )).toList();

        return new OrderOutput(
                order.getId(),
                order.getStoreId(),
                order.getStatus(),
                order.calculateTotalPrice(),
                itemOutputList
        );
    }
}
