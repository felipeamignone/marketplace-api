package com.api.marketplace.domain.order.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Order {
    private final UUID id;
    private OrderStatus status;
    private final List<OrderItem> items;

    // external Aggregate
    private final UUID storeId;

    public Order(UUID id, UUID storeId, OrderStatus status, List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }

        this.id = id;
        this.storeId = storeId;
        this.status = status == null ? OrderStatus.CREATED : status;
        this.items = List.copyOf(items);
    }

    public UUID getId() {
        return id;
    }

    public UUID getStoreId() {
        return storeId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    private void updateToPaid() {
        if(!this.status.equals(OrderStatus.CREATED)){
            throw new IllegalArgumentException("Cannot change status to 'Paid' from current status");
        }

        this.status = OrderStatus.PAID;
    }

    private void updateToShipped() {
        if(!this.status.equals(OrderStatus.PAID)){
            throw new IllegalArgumentException("Cannot change status to 'Shipped' from current status");
        }

        this.status = OrderStatus.SHIPPED;
    }

    private void updateToCompleted() {
        if(!this.status.equals(OrderStatus.SHIPPED)){
            throw new IllegalArgumentException("Cannot change status to 'Shipped' from current status");
        }

        this.status = OrderStatus.COMPLETED;
    }

    private void updateToCanceled(){
        this.status = OrderStatus.CANCELED;
    }

    public void updateStatus(OrderStatus newStatus) {
        switch (newStatus){
            case OrderStatus.CANCELED -> this.updateToCanceled();
            case OrderStatus.COMPLETED -> this.updateToCompleted();
            case OrderStatus.SHIPPED -> this.updateToShipped();
            case OrderStatus.PAID -> this.updateToPaid();
            default -> throw new IllegalArgumentException("Invalid status");
        }
    }

    public BigDecimal calculateTotalPrice() {
        return this.items.stream()
                .map(OrderItem::calculateTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
