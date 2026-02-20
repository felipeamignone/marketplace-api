package com.api.marketplace.application.store.useCases;

import com.api.marketplace.application.store.commands.CreateStoreInput;
import com.api.marketplace.application.store.commands.StoreOutput;
import com.api.marketplace.domain.store.gateway.StoreRepositoryGateway;
import com.api.marketplace.domain.store.model.Store;
import org.springframework.stereotype.Service;

@Service
public class CreateStoreUseCase {
    private final StoreRepositoryGateway storeRepositoryGateway;

    public CreateStoreUseCase(StoreRepositoryGateway storeRepositoryGateway) {
        this.storeRepositoryGateway = storeRepositoryGateway;
    }

    public StoreOutput execute(CreateStoreInput input) {
        Store store = new Store(null, input.cnpj(), input.name());
        Store saved = storeRepositoryGateway.save(store);
        return new StoreOutput(
                saved.getId(),
                saved.getName(),
                saved.getCnpj()
        );
    }
}
