package com.api.marketplace.adapters.store.controllers;

import com.api.marketplace.application.store.commands.CreateStoreInput;
import com.api.marketplace.application.store.commands.StoreOutput;
import com.api.marketplace.application.store.useCases.CreateStoreUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/stores")
public class StoreController {
    private final CreateStoreUseCase createStoreUseCase;

    public StoreController(CreateStoreUseCase createStoreUseCase) {
        this.createStoreUseCase = createStoreUseCase;
    }

    @PostMapping
    public ResponseEntity<StoreResponse> create(@RequestBody CreateStoreRequest body) {
        CreateStoreInput input = new CreateStoreInput(body.name(), body.cnpj());
        StoreOutput result = createStoreUseCase.execute(input);
        StoreResponse response = StoreMapper.toResponse(result);
        return ResponseEntity.created().body(response);
    }
}
