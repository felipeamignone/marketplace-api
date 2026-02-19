package com.api.marketplace.adapters.order.controllers;

import com.api.marketplace.application.order.commands.OrderItemOutput;
import com.api.marketplace.application.order.commands.OrderOutput;

import java.util.List;

public class OrderMapper {
    static OrderItemResponse itemOutputToResponse(OrderItemOutput useCaseItemOutput) {
        return new OrderItemResponse(
                useCaseItemOutput.name(),
                useCaseItemOutput.quantity(),
                useCaseItemOutput.unitPrice()
        );
    }

    static List<OrderItemResponse> itemOutputListToResponse (List<OrderItemOutput> useCaseListItemOutput){
        return useCaseListItemOutput.stream()
                .map(OrderMapper::itemOutputToResponse)
                .toList();
    }

    static  OrderResponse orderOutputToResponse (OrderOutput useCaseOrderOutput){
        List<OrderItemResponse> itemResponseList = OrderMapper.itemOutputListToResponse(useCaseOrderOutput.items());

        return new OrderResponse(
                useCaseOrderOutput.id(),
                useCaseOrderOutput.storeId(),
                useCaseOrderOutput.status(),
                itemResponseList,
                useCaseOrderOutput.totalPrice()
        );
    }
}
