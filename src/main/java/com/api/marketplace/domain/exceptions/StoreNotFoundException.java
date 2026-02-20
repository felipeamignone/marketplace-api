package com.api.marketplace.domain.exceptions;

import java.util.UUID;

public class StoreNotFoundException extends RuntimeException {

    public StoreNotFoundException(UUID storeId) {
        super("Store not found with id: " + storeId);
    }
}
