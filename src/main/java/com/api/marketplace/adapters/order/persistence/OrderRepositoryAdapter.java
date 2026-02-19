package com.api.marketplace.adapters.order.persistence;

import com.api.marketplace.domain.order.gateway.OrderRepositoryGateway;
import com.api.marketplace.domain.order.model.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderRepositoryAdapter implements OrderRepositoryGateway {
    private final OrderRepository repository;
    private final OrderMapper mapper;

    public OrderRepositoryAdapter(OrderRepository repository, OrderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Order findById(UUID id) {
        OrderJpaEntity jpaEntity = repository.findById(id)
                .orElseThrow();

        return mapper.toDomain(jpaEntity);
    }

    @Override
    public Order save(Order newOrder) {
        OrderJpaEntity jpaEntity = mapper.toJpa(newOrder);
        OrderJpaEntity savedEntity = repository.save(jpaEntity);
        return mapper.toDomain(savedEntity);
    }
}
