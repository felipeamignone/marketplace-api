package com.api.marketplace.application.order.useCases;

import com.api.marketplace.domain.order.gateway.OrderRepositoryGateway;
import com.api.marketplace.domain.order.model.Order;
import com.api.marketplace.domain.order.model.OrderStatus;
import com.api.marketplace.domain.webhook.gateway.WebhookEventSenderGateway;
import com.api.marketplace.domain.webhook.gateway.WebhookRepositoryGateway;
import com.api.marketplace.domain.webhook.model.WebhookEvent;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class UpdateOrderStatusUseCase {
    private final OrderRepositoryGateway orderRepositoryGateway;
    private final WebhookRepositoryGateway webhookRepositoryGateway;
    private final WebhookEventSenderGateway webhookEventSender;

    public UpdateOrderStatusUseCase(
        OrderRepositoryGateway orderRepositoryGateway,
        WebhookRepositoryGateway webhookRepositoryGateway,
        WebhookEventSenderGateway webhookEventSender
    ) {
        this.orderRepositoryGateway = orderRepositoryGateway;
        this.webhookRepositoryGateway = webhookRepositoryGateway;
        this.webhookEventSender = webhookEventSender;
    }

    public void execute(UUID orderId, OrderStatus newStatus) {
        Order foundedOrder = orderRepositoryGateway.findById(orderId);
        foundedOrder.updateStatus(newStatus);
        orderRepositoryGateway.save(foundedOrder);

        String eventName = foundedOrder.getChangeStatusEventNameFromStatus(newStatus);
        WebhookEvent payload = new WebhookEvent(
                eventName,
                foundedOrder.getId(),
                foundedOrder.getStoreId(),
                Instant.now().toString()
        );

        webhookRepositoryGateway.findByStoreId(foundedOrder.getStoreId()).forEach(webhook ->
                webhookEventSender.send(webhook.getCallbackUrl(), payload)
        );
    }
}
