package org.example.relations.service;

import org.example.relations.entity.Order;
import org.example.relations.enums.OrderStatus;
import org.example.relations.exception.OrderAlreadyCancelledException;
import org.example.relations.exception.StockUpdateException;
import org.example.relations.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(
            rollbackFor = StockUpdateException.class,
            noRollbackFor = OrderAlreadyCancelledException.class
    )
    public Order cancelOrder(Long id, boolean stockUpdateFails)
            throws StockUpdateException {

        Order order = orderRepository.findById(id).orElse(null);

        if (order == null) {
            return null;
        }

        order.setCancel(
                order.getCancel() + 1
        );

        if (order.getStatus().equals("CANCELLED")) {

            orderRepository.save(order);

            throw new OrderAlreadyCancelledException(
                    "Order artıq ləğv edilib!"
            );
        }

        order.setStatus(OrderStatus.CANCELLED);

        orderRepository.save(order);

        if (stockUpdateFails) {
            throw new StockUpdateException(
                    "Stock yenilənərkən xəta baş verdi!"
            );
        }

        return order;
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}