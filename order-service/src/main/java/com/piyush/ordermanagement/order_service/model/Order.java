package com.piyush.ordermanagement.order_service.model;

public class Order {
    private Long id;
    private String itemName;
    private int quantity;
    private String status; // e.g. "PENDING", "SHIPPED", "DELIVERED"

    public Order() {}

    public Order(Long id, String itemName, int quantity, String status) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}