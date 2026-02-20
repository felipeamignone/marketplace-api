package com.api.marketplace.domain.webhook.model;

import java.util.UUID;

public record WebhookEvent(
        String event,
        UUID orderId,
        UUID storeId,
        String timestamp
) {
}
