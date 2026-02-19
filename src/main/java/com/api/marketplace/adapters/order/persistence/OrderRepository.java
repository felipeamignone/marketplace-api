package com.api.marketplace.adapters.order.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderJpaEntity, UUID> {
}
