package com.api.marketplace.adapters.store.controllers;

import java.util.UUID;

public record StoreResponse(
        UUID id,
        String name,
        String cnpj
) {
}
