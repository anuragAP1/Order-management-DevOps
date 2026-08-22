package com.piyush.ordermanagement.inventory_service.model;

public class InventoryItem {
    private Long id;
    private String itemName;
    private int availableQuantity;

    public InventoryItem() {}

    public InventoryItem(Long id, String itemName, int availableQuantity) {
        this.id = id;
        this.itemName = itemName;
        this.availableQuantity = availableQuantity;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public int getAvailableQuantity() { return availableQuantity; }
    public void setAvailableQuantity(int quantity) { this.availableQuantity = quantity; }

}
