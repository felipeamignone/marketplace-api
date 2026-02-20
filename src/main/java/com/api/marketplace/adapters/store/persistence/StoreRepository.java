package com.api.marketplace.adapters.store.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreRepository extends JpaRepository<StoreJpaEntity, UUID> {
}
