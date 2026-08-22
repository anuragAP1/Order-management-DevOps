package com.piyush.ordermanagement.inventory_service.controller;


import com.piyush.ordermanagement.inventory_service.model.InventoryItem;
import com.piyush.ordermanagement.inventory_service.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<InventoryItem> getAllItems() {
        return inventoryService.getAllItems();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<InventoryItem> getItemById(@PathVariable Long id) {
        return inventoryService.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{itemName}")
    public ResponseEntity<InventoryItem> getItemByName(@PathVariable String itemName) {
        return inventoryService.getItemByName(itemName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InventoryItem> createItem(@RequestBody InventoryItem inventoryItem) {
        InventoryItem created = inventoryService.createItem(inventoryItem);
        URI location = URI.create("/api/inventory/id/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        boolean deleted = inventoryService.deleteItem(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


}
