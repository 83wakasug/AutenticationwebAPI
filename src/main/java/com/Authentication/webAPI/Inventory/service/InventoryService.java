package com.Authentication.webAPI.Inventory.service;


import com.Authentication.webAPI.Inventory.entity.Inventory;
import com.Authentication.webAPI.Inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor

public class InventoryService {

    private final InventoryRepository repository;
    public List<Inventory> findAll(){
        try {
            return repository.findAll();
        } catch (Exception e) {
            // Handle the exception or log it
            throw new RuntimeException("Failed to retrieve inventory data", e);
        }
    }

    public Optional<Inventory> findOne(String productName){
        try {
            return repository.findByProductName(productName);
        } catch (Exception e) {
            // Handle the exception or log it
            throw new RuntimeException("Failed to retrieve inventory data for product: " + productName, e);
        }

    }

    public Optional<Inventory> findOneById(long id){
        try {
            return repository.findById(id);
        } catch (Exception e) {
            // Handle the exception or log it
            throw new RuntimeException("Failed to retrieve inventory data for product: " + id, e);
        }

    }

    public void updateProduct(Inventory updatedInventory,long id){
        try {
            Optional<Inventory> invent =repository.findById(id);
            if (invent.isPresent()) {
                Inventory existingInventory = invent.get();
                existingInventory.setProductName(updatedInventory.getProductName());
                existingInventory.setPrice(updatedInventory.getPrice());
                existingInventory.setStock(updatedInventory.getStock());

                System.out.println(existingInventory.getProductName());
                // Assuming addProducts is used for updating, adjust the method accordingly
                addProducts(existingInventory);
            }
        } catch (Exception e) {
            // Handle the exception or log it
            throw new RuntimeException("Failed to update product: " + updatedInventory.getProductName(), e);
        }
    }

    public void addProducts(Inventory inventory){
        try {
            repository.save(inventory);
        } catch (Exception e) {
            // Handle the exception or log it
            throw new RuntimeException("Failed to add products to inventory", e);
        }

    }

    public void deleteProductByProductName(long id){
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            // Handle the exception or log it
            throw new RuntimeException("Failed to delete product: " + id, e);
        }
    }
}
