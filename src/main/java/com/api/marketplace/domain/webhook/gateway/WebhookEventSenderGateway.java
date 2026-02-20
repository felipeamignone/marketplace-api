package com.api.marketplace.domain.webhook.gateway;

import com.api.marketplace.domain.webhook.model.WebhookEvent;

public interface WebhookEventSenderGateway {

    void send(String callbackUrl, WebhookEvent event);
}
