package com.api.marketplace.domain.exceptions;

import java.util.UUID;

public class WebhookNotFoundToStoreIdException extends RuntimeException {

    public WebhookNotFoundToStoreIdException(UUID storeId) {
        super("Webhook not found to store id: " + storeId);
    }
}
