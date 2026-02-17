package com.api.marketplace.domain.model.order;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private final Integer id;
    private final UUID externalId;
    private OrderStatus status;
    private final List<OrderItem> items = new ArrayList<>();

    // external Aggregate
    private final UUID storeId;

    public Order(Integer id, UUID externalId, UUID storeId, OrderStatus status) {
        this.id = id;
        this.externalId = externalId;
        this.storeId = storeId;
        this.status = status == null ? OrderStatus.CREATED : status;
    }

    public Integer getId() {
        return id;
    }

    public UUID getStoreId() {
        return storeId;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setStatus(OrderStatus newStatus) throws IllegalArgumentException {
        if(newStatus == null){
            throw new IllegalArgumentException("Parameter cannot be null");
        }
        if(newStatus.equals(OrderStatus.PAID) && !this.status.equals(OrderStatus.CREATED)){
            throw new IllegalArgumentException("Cannot change status to 'Paid' from current status");
        }
        if(newStatus.equals(OrderStatus.SHIPPED) && !this.status.equals(OrderStatus.PAID)){
            throw new IllegalArgumentException("Cannot change status to 'Shipped' from current status");
        }
        if(newStatus.equals(OrderStatus.COMPLETED) && !this.status.equals(OrderStatus.SHIPPED)){
            throw new IllegalArgumentException("Cannot change status to 'Completed' from current status");
        }

        this.status = newStatus;
    }
}
