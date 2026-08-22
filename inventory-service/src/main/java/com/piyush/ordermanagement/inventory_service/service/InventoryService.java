package com.piyush.ordermanagement.inventory_service.service;

import com.piyush.ordermanagement.inventory_service.model.InventoryItem;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class InventoryService {

    private final Map<Long, InventoryItem> inventoryItemMap = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public List<InventoryItem> getAllItems() {
        return new ArrayList<>(inventoryItemMap.values());
    }

    public Optional<InventoryItem> getItemById(Long id) {
        return Optional.ofNullable(inventoryItemMap.get(id));
    }

    public Optional<InventoryItem> getItemByName(String itemName) {
        return inventoryItemMap.values().stream().filter(item ->item .getItemName().toLowerCase().equals(itemName.toLowerCase())).findFirst();
    }

    public InventoryItem createItem (InventoryItem inventoryItem) {
        long id = idCounter.incrementAndGet();
        inventoryItem.setId(id);
        inventoryItemMap.put(id, inventoryItem);
        return inventoryItem;
    }

    public boolean deleteItem(Long id) {
        return inventoryItemMap.remove(id) != null;
    }
}

