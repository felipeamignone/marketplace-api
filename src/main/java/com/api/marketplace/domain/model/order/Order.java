package com.api.marketplace.domain.model.order;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Order {
    private final Integer id;
    private final UUID externalId;
    private OrderStatus status;
    private final List<OrderItem> items;

    // external Aggregate
    private final UUID storeId;

    public Order(Integer id, UUID externalId, UUID storeId, OrderStatus status, List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }

        this.id = id;
        this.externalId = externalId;
        this.storeId = storeId;
        this.status = status == null ? OrderStatus.CREATED : status;
        this.items = List.copyOf(items);
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

    public void updateToPaid() {
        if(!this.status.equals(OrderStatus.CREATED)){
            throw new IllegalArgumentException("Cannot change status to 'Paid' from current status");
        }

        this.status = OrderStatus.PAID;
    }

    public void updateToShipped() {
        if(!this.status.equals(OrderStatus.PAID)){
            throw new IllegalArgumentException("Cannot change status to 'Shipped' from current status");
        }

        this.status = OrderStatus.SHIPPED;
    }

    public void updateToCompleted() {
        if(!this.status.equals(OrderStatus.SHIPPED)){
            throw new IllegalArgumentException("Cannot change status to 'Shipped' from current status");
        }

        this.status = OrderStatus.COMPLETED;
    }

    public void updateToCanceled(){
        this.status = OrderStatus.CANCELED;
    }

    public BigDecimal calculateTotalPrice() {
        return this.items.stream()
                .map(OrderItem::calculateTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
