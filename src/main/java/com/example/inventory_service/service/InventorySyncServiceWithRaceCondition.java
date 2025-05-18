package com.example.inventory_service.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@AllArgsConstructor
public class InventorySyncServiceWithRaceCondition {

    private InventoryService inventoryService;

    @Scheduled(fixedRate = 1000)
    public void syncInventoryService1() {
        inventoryService.updateInventory("src-1-w-rc");
    }

    @Scheduled(fixedRate = 1000)
    public void syncInventoryService2() {
        inventoryService.updateInventory("src-2-w-rc");
    }
      
}
