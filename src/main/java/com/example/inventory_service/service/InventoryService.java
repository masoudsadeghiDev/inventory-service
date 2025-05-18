package com.example.inventory_service.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.inventory_service.entity.InventoryItem;
import com.example.inventory_service.repository.InventoryRepository;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository repository;

    @SneakyThrows
    public void updateInventory(String source) {
        InventoryItem item = repository.findById("item-1").orElse(new InventoryItem());
        // Simulate sync logic
        item.setProductId("item-1");
        item.setQuantity(item.getQuantity() + 1); // simulate update
        Thread.sleep(Duration.ofMillis(100));
        repository.save(item);
        log.info("SYNC from " + source + " at " + LocalDateTime.now());
    }
    
}
