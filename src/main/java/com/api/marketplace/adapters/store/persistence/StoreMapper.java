package com.api.marketplace.adapters.store.persistence;

import com.api.marketplace.domain.store.model.Store;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {

    public Store toDomain(StoreJpaEntity jpaEntity) {
        return new Store(
                jpaEntity.getId(),
                jpaEntity.getCnpj(),
                jpaEntity.getName()
        );
    }

    public StoreJpaEntity toJpa(Store domain) {
        return new StoreJpaEntity(
                domain.getId(),
                domain.getName(),
                domain.getCnpj()
        );
    }
}
