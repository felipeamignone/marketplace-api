package com.api.marketplace.application.webhook.useCases;

import com.api.marketplace.application.webhook.commands.CreateWebhookInput;
import com.api.marketplace.application.webhook.commands.WebhookOutput;
import com.api.marketplace.domain.store.gateway.StoreRepositoryGateway;
import com.api.marketplace.domain.webhook.gateway.WebhookRepositoryGateway;
import com.api.marketplace.domain.webhook.model.Webhook;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CreateWebhookUseCase {
    private final WebhookRepositoryGateway webhookRepositoryGateway;
    private final StoreRepositoryGateway storeRepositoryGateway;

    public CreateWebhookUseCase(WebhookRepositoryGateway webhookRepositoryGateway,
                                StoreRepositoryGateway storeRepositoryGateway) {
        this.webhookRepositoryGateway = webhookRepositoryGateway;
        this.storeRepositoryGateway = storeRepositoryGateway;
    }

    public WebhookOutput execute(CreateWebhookInput input) {
        for (UUID storeId : input.storeIds()) {
            storeRepositoryGateway.findById(storeId);
        }

        Webhook webhook = new Webhook(null, input.callbackUrl());
        for (UUID storeId : input.storeIds()) {
            webhook.addStoreId(storeId);
        }
        Webhook saved = webhookRepositoryGateway.save(webhook);
        return new WebhookOutput(
                saved.getId(),
                saved.getCallbackUrl(),
                List.copyOf(saved.getStoreIds())
        );
    }
}
