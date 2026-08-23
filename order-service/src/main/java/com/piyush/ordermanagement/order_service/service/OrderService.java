package com.piyush.ordermanagement.order_service.service;

import com.piyush.ordermanagement.order_service.dto.InventoryItemDto;
import com.piyush.ordermanagement.order_service.exception.InsufficientStockException;
import com.piyush.ordermanagement.order_service.exception.ItemNotFoundException;
import com.piyush.ordermanagement.order_service.model.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderService {



    private final Map<Long, Order> orderStore = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();
    private final RestTemplate restTemplate;

    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orderStore.values());
    }

    public Optional<Order> getOrderById(Long id) {
        return Optional.ofNullable(orderStore.get(id));
    }

    public Order createOrder(Order order) {
        String inventoryUrl = "http://localhost:8081/api/inventory/name/" + order.getItemName();

        InventoryItemDto item;
        try {
            item = restTemplate.getForObject(inventoryUrl, InventoryItemDto.class);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new ItemNotFoundException("Item not found in inventory: " + order.getItemName());
        }

        if (item.getAvailableQuantity() < order.getQuantity()) {
            throw new InsufficientStockException("Insufficient stock for item: " + order.getItemName());
        }

        long id = idCounter.incrementAndGet();
        order.setId(id);
        orderStore.put(id, order);

        notifyOrderCreated(order);

        return order;
    }

    private void notifyOrderCreated(Order order) {
        String notificationUrl = "http://localhost:8082/api/notifications";
        String message = "Order #" + order.getId() + " confirmed for " + order.getItemName() + " x" + order.getQuantity();

        Map<String, String> body = Map.of("message", message);

        try {
            restTemplate.postForObject(notificationUrl, body, Void.class);
        } catch (Exception ex) {
            System.err.println("Failed to send notification: " + ex.getMessage());
        }
    }

    public boolean deleteOrder(Long id) {
        return orderStore.remove(id) != null;
    }
}