package com.example.inventory_service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.inventory_service.entity.InventoryItem;
import com.example.inventory_service.repository.InventoryRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class InventorySchedulerTest {
    
    @Autowired
    private InventoryRepository inventoryRepository;

    @BeforeEach
    public void setup() {
        inventoryRepository.deleteAll();
        InventoryItem item = new InventoryItem();
        item.setProductId("item-1");
        item.setQuantity(0);
        inventoryRepository.save(item);
    }


    @Test
    public void testInventoryAfterScheduledRuns() throws InterruptedException {
        
        // Wait long enough for both schedulers to complete 3 runs each
        // If each has 1s, and runs 3 times, we wait ~3s
        Thread.sleep(3100); // 3.1 seconds to allow for both schedulers to finish
        InventoryItem item = inventoryRepository.findById("item-1").orElseThrow();
        log.info("Final quantity: " + item.getQuantity());
        Assertions.assertEquals(3, item.getQuantity(), "Inventory should be updated 3 times");
    }
}
