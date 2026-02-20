package com.api.marketplace.adapters.store.persistence;

import com.api.marketplace.domain.store.gateway.StoreRepositoryGateway;
import com.api.marketplace.domain.store.model.Store;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StoreRepositoryAdapter implements StoreRepositoryGateway {
    private final StoreRepository repository;
    private final StoreMapper mapper;

    public StoreRepositoryAdapter(StoreRepository repository, StoreMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Store save(Store newStore) {
        StoreJpaEntity entity = mapper.toJpa(newStore);
        StoreJpaEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Store findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDomain)
                .orElse(null);
    }
}
