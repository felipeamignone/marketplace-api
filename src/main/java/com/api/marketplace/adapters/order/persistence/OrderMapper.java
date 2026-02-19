package com.api.marketplace.adapters.order.persistence;

import com.api.marketplace.domain.order.model.Order;
import com.api.marketplace.domain.order.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    public OrderJpaEntity toJpa(Order order){
        OrderJpaEntity jpa = new OrderJpaEntity(
                order.getId(),
                order.getStatus(),
                order.getStoreId()
        );

        order.getItems().forEach(item ->
                jpa.addItem(
                    new OrderItemJpaEntity(
                            item.name(),
                            item.quantity(),
                            item.unitPrice()
                    )
                )
        );

        return jpa;
    }

    public Order toDomain(OrderJpaEntity jpa) {
        List<OrderItem> items = jpa.getItems().stream()
                .map(item -> new OrderItem(
                        item.getName(),
                        item.getUnitPrice(),
                        item.getQuantity()
                ))
                .toList();
        return new Order(
                jpa.getId(),
                jpa.getStoreId(),
                jpa.getStatus(),
                items
        );
    }
}
