package com.Authentication.webAPI.Inventory.controller;


import com.Authentication.webAPI.Inventory.entity.Inventory;
import com.Authentication.webAPI.Inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor

public class webController {


    private final InventoryService service;

    // Get all inventory data
    @GetMapping("/")
    public ResponseEntity<List<Inventory>> getAllData() {
        try {
            List<Inventory> inventoryList = service.findAll();
            return ResponseEntity.ok(inventoryList);
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    // Get inventory data by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Inventory>> getOneData(@PathVariable("id") long id) {
        try {
            Optional<Inventory> inventory = service.findOneById(id);
            if (inventory.isPresent()) {
                return ResponseEntity.ok(inventory);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Add new inventory data
    @PostMapping("/")
    public ResponseEntity<String> addData(@RequestBody Inventory inventory) {
        try {
            service.addProducts(inventory);
            return ResponseEntity.ok("Data updated:"+inventory.toString());
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Update inventory data by ID

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") long id, @RequestBody Inventory inventory) {
        try {
              boolean exist =service.updateProduct(inventory, id);
            if (exist) {
                return ResponseEntity.ok("Data updated");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data not found");
            }
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Delete inventory data by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            boolean isDeleted = service.deleteProductByProductName(id);
            if (isDeleted) {
                return ResponseEntity.ok("Data deleted");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data not found");
            }
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
