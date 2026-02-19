package com.api.marketplace.adapters.order.persistence.order;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_items")
public class OrderItemJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private Integer quantity;

    private BigDecimal unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderJpaEntity order;

    protected OrderItemJpaEntity () {}

    public OrderItemJpaEntity (String name, Integer quantity, BigDecimal unitPrice) {
        this.name = name;
        this.quantity = quantity;
        this. unitPrice = unitPrice;
    }

    public void setOrder(OrderJpaEntity order) {
        this.order = order;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public OrderJpaEntity getOrder() {
        return order;
    }
}
