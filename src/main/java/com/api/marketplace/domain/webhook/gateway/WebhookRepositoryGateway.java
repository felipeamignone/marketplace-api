package com.api.marketplace.domain.webhook.gateway;

import com.api.marketplace.domain.webhook.model.Webhook;

import java.util.List;
import java.util.UUID;

public interface WebhookRepositoryGateway {
    Webhook save(Webhook newWebhook);

    List<Webhook> findByStoreId(UUID storeId);
}
