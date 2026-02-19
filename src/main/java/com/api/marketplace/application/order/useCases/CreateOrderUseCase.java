package com.api.marketplace.application.order.useCases;

import com.api.marketplace.application.order.commands.CreateOrderInput;
import com.api.marketplace.application.order.commands.OrderOutput;
import com.api.marketplace.application.order.commands.OrderItemOutput;
import com.api.marketplace.domain.order.gateway.OrderRepositoryGateway;
import com.api.marketplace.domain.order.model.Order;
import com.api.marketplace.domain.order.model.OrderItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CreateOrderUseCase {
    private final OrderRepositoryGateway orderRepository;

    public CreateOrderUseCase(OrderRepositoryGateway orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderOutput execute(CreateOrderInput input) {
        List<OrderItem> items = input.items().stream().map(item -> new OrderItem(
                item.name(),
                new BigDecimal("10.99"), //deve buscar pelo produto cadastrado no banco.
                item.quantity()
        )).toList();

        Order newOrder = new Order(null, null, null, items);
        Order savedOrder = orderRepository.save(newOrder);

        List<OrderItemOutput> itemOutputList = savedOrder.getItems().stream()
                .map(item -> new OrderItemOutput(
                        item.quantity(),
                        item.unitPrice(),
                        item.calculateTotalPrice(),
                        item.name()
                )).toList();

        return new OrderOutput(
                savedOrder.getId(),
                savedOrder.getStoreId(),
                savedOrder.getStatus(),
                savedOrder.calculateTotalPrice(),
                itemOutputList
        );
    }
}
