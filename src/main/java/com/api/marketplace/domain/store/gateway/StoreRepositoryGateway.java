package com.api.marketplace.domain.store.gateway;

import com.api.marketplace.domain.store.model.Store;

import java.util.UUID;

public interface StoreRepositoryGateway {
    Store save(Store newStore);
    Store findById(UUID id);
}
