package org.example.relations.controller;

import org.example.relations.entity.Order;
import org.example.relations.exception.OrderAlreadyCancelledException;
import org.example.relations.exception.StockUpdateException;
import org.example.relations.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @PostMapping("/{id}/cancel")
    public String cancelOrder(
            @PathVariable Long id,
            @RequestParam(defaultValue = "false") boolean stockUpdateFails) {

        try {
            orderService.cancelOrder(id, stockUpdateFails);
            return "cancelled";

        } catch (OrderAlreadyCancelledException |
                 StockUpdateException e) {

            return e.getMessage();
        }
    }
}