package com.api.marketplace.domain.model.order;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItem {
    private final Integer id;
    private final UUID externalId;
    private final String name;
    private final BigDecimal unitPrice;
    private int quantity;

    public OrderItem (Integer id, UUID externalId, String name, BigDecimal unitPrice, int quantity) {
        this.id = id;
        this.externalId = externalId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void changeQuantity(int newQuantity) throws IllegalArgumentException {
        if(this.quantity <= 0){
            throw new IllegalArgumentException("Item should have at least one quantity");
        }
        this.quantity = newQuantity;
    }
}
