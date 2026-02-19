package com.api.marketplace.adapters.order.persistence.order;

import com.api.marketplace.domain.order.model.OrderStatus;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class OrderJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private UUID storeId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<OrderItemJpaEntity> items = new ArrayList<>();

    protected OrderJpaEntity () {}


    public OrderJpaEntity(UUID id, OrderStatus status, UUID storeId) {
        this.id = id;
        this.status = status;
        this.storeId = storeId;
    }

    public void addItem(OrderItemJpaEntity item){
        item.setOrder(this);
        items.add(item);
    }

    public UUID getId() {
        return id;
    }

    public List<OrderItemJpaEntity> getItems() {
        return items;
    }

    public UUID getStoreId() {
        return storeId;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
