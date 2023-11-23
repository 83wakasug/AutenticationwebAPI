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

    @GetMapping("/SelectAll")
    public ResponseEntity<List<Inventory>> getAllData() {
        try {
            List<Inventory> inventoryList = service.findAll();
            return ResponseEntity.ok(inventoryList);
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/SelectOneByName/{productName}")
    public ResponseEntity<Optional<Inventory>> getOneData(@PathVariable("productName") String productName) {
        try {
            Optional<Inventory> inventory = service.findOne(productName);
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

    @GetMapping("/SelectOneById/{id}")
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


    @PostMapping("/add")
    public ResponseEntity<String> addData(@RequestBody Inventory inventory) {
        try {
            service.addProducts(inventory);
            return ResponseEntity.ok("Data updated:"+inventory.toString());
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") long id, @RequestBody Inventory inventory) {
        try {
            service.updateProduct(inventory,id);
            return ResponseEntity.ok("Data updated");
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            service.deleteProductByProductName(id);
            return ResponseEntity.ok("Data deleted");
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
