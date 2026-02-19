package com.api.marketplace.adapters.order.controllers;

import com.api.marketplace.application.order.commands.CreateOrderInput;
import com.api.marketplace.application.order.commands.OrderOutput;
import com.api.marketplace.application.order.useCases.CreateOrderUseCase;
import com.api.marketplace.application.order.commands.OrderItemInput;
import com.api.marketplace.application.order.useCases.FindOrderByIdUseCase;
import com.api.marketplace.application.order.useCases.UpdateOrderStatusUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final CreateOrderUseCase createOrderUseCase;
    private final UpdateOrderStatusUseCase updateOrderStatusUseCase;
    private final FindOrderByIdUseCase findOrderByIdUseCase;

    public OrderController(
            CreateOrderUseCase createOrderUseCase,
            UpdateOrderStatusUseCase updateOrderStatusUseCase,
            FindOrderByIdUseCase findOrderByIdUseCase
    ) {
        this.createOrderUseCase = createOrderUseCase;
        this.updateOrderStatusUseCase = updateOrderStatusUseCase;
        this.findOrderByIdUseCase = findOrderByIdUseCase;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody CreateOrderRequest body) {
        List<OrderItemInput> items = body.items().stream()
                .map(item -> new OrderItemInput(
                        item.quantity(),
                        item.name()
                ))
                .toList();

        CreateOrderInput input = new CreateOrderInput(
                body.storeId(),
                items
        );

        OrderOutput result = createOrderUseCase.execute(input);

        OrderResponse response = OrderMapper.orderOutputToResponse(result);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{orderId}/status")
    public ResponseEntity<Boolean> updateStatus (@PathVariable UUID orderId, @RequestBody UpdateOrderStatusRequest body) {
        this.updateOrderStatusUseCase.execute(orderId, body.status());

        return ResponseEntity.ok(true);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> findById (@PathVariable UUID orderId) {
        OrderOutput result = this.findOrderByIdUseCase.execute(orderId);

        OrderResponse response = OrderMapper.orderOutputToResponse(result);

        return ResponseEntity.ok(response);
    }


}
