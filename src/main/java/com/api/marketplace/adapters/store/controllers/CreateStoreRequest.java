package com.api.marketplace.adapters.store.controllers;

public record CreateStoreRequest(
        String name,
        String cnpj
) {
}
