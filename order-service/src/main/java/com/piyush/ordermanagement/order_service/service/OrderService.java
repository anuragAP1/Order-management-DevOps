package com.piyush.ordermanagement.order_service.service;

import com.piyush.ordermanagement.order_service.model.Order;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderService {

    private final Map<Long, Order> orderStore = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public List<Order> getAllOrders() {
        return new ArrayList<>(orderStore.values());
    }

    public Optional<Order> getOrderById(Long id) {
        return Optional.ofNullable(orderStore.get(id));
    }

    public Order createOrder(Order order) {
        long id = idCounter.incrementAndGet();
        order.setId(id);
        orderStore.put(id, order);
        return order;
    }

    public boolean deleteOrder(Long id) {
        return orderStore.remove(id) != null;
    }
}