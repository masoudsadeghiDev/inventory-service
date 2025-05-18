package com.example.inventory_service.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

// @Service
// @AllArgsConstructor
public class InventorySyncService {

    private InventoryService inventoryService;
    
    // @Scheduled(fixedDelay = 1000)
    // @SchedulerLock(name = "InventorySyncTask", lockAtLeastFor = "1s", lockAtMostFor = "2s")
    public void syncInventoryService1() {
        inventoryService.updateInventory("src-1");
    }

    // @Scheduled(fixedDelay = 1000)
    // @SchedulerLock(name = "InventorySyncTask", lockAtLeastFor = "1s", lockAtMostFor = "2s")
    public void syncInventoryService2() {
        inventoryService.updateInventory("src-2");
    }
    
}
