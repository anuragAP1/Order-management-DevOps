package com.piyush.ordermanagement.order_service.dto;

public class InventoryItemDto {
    private Long id;
    private String itemName;
    private int availableQuantity;

    public InventoryItemDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public int getAvailableQuantity() { return availableQuantity; }
    public void setAvailableQuantity(int availableQuantity) { this.availableQuantity = availableQuantity; }
}