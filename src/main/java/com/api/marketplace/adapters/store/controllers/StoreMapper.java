package com.api.marketplace.adapters.store.controllers;

import com.api.marketplace.application.store.commands.StoreOutput;

public class StoreMapper {

    static StoreResponse toResponse(StoreOutput output) {
        return new StoreResponse(
                output.id(),
                output.name(),
                output.cnpj()
        );
    }
}
