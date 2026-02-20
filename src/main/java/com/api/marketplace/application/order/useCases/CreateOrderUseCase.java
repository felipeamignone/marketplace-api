package com.api.marketplace.application.order.useCases;

import com.api.marketplace.application.order.commands.CreateOrderInput;
import com.api.marketplace.application.order.commands.OrderOutput;
import com.api.marketplace.application.order.commands.OrderItemOutput;
import com.api.marketplace.domain.order.gateway.OrderRepositoryGateway;
import com.api.marketplace.domain.order.model.Order;
import com.api.marketplace.domain.order.model.OrderItem;
import com.api.marketplace.domain.webhook.gateway.WebhookEventSenderGateway;
import com.api.marketplace.domain.webhook.gateway.WebhookRepositoryGateway;
import com.api.marketplace.domain.webhook.model.WebhookEvent;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
public class CreateOrderUseCase {
    private final OrderRepositoryGateway orderRepository;
    private final WebhookRepositoryGateway webhookRepositoryGateway;
    private final WebhookEventSenderGateway webhookEventSender;

    public CreateOrderUseCase(OrderRepositoryGateway orderRepository,
                             WebhookRepositoryGateway webhookRepositoryGateway,
                             WebhookEventSenderGateway webhookEventSender) {
        this.orderRepository = orderRepository;
        this.webhookRepositoryGateway = webhookRepositoryGateway;
        this.webhookEventSender = webhookEventSender;
    }

    public OrderOutput execute(CreateOrderInput input) {
        List<OrderItem> items = input.items().stream().map(item -> new OrderItem(
                item.name(),
                new BigDecimal("10.99"), //deve buscar pelo produto cadastrado no banco.
                item.quantity()
        )).toList();

        Order newOrder = new Order(null, input.storeId(), null, items);
        Order savedOrder = orderRepository.save(newOrder);

        WebhookEvent payload = new WebhookEvent(
                "order.created",
                savedOrder.getId(),
                savedOrder.getStoreId(),
                Instant.now().toString()
        );
        webhookRepositoryGateway.findByStoreId(savedOrder.getStoreId()).forEach(webhook ->
                webhookEventSender.send(webhook.getCallbackUrl(), payload)
        );

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
