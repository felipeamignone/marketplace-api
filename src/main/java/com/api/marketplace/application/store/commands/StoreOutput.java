package com.api.marketplace.application.store.commands;

import java.util.UUID;

public record StoreOutput(
        UUID id,
        String name,
        String cnpj
) {
}
