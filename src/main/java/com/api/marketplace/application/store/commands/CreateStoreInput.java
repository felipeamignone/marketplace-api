package com.api.marketplace.application.store.commands;

public record CreateStoreInput(
        String name,
        String cnpj
) {
}
