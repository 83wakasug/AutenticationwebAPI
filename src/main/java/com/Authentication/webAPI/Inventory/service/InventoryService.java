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

    /**
     * Retrieve all inventory data.
     * @return List of Inventory objects if data exists, otherwise null.
     * @throws RuntimeException if an error occurs during data retrieval.
     */
    public List<Inventory> findAll(){
        try {
            List<Inventory> data = repository.findAll();
            if (data.isEmpty()) return null;

            return repository.findAll();
        } catch (Exception e) {
            // Handle the exception or log it
            throw new RuntimeException("Failed to retrieve inventory data", e);
        }
    }

    /**
     * Retrieve inventory data by product name.
     * @param productName The name of the product to retrieve.
     * @return Optional containing Inventory if found, otherwise empty.
     * @throws RuntimeException if an error occurs during data retrieval.
     */
    public Optional<Inventory> findOne(String productName){
        try {
            return repository.findByProductName(productName);
        } catch (Exception e) {
            // Handle the exception or log it
            throw new RuntimeException("Failed to retrieve inventory data for product: " + productName, e);
        }

    }

    /**
     * Retrieve inventory data by ID.
     * @param id The ID of the inventory data to retrieve.
     * @return Optional containing Inventory if found, otherwise empty.
     * @throws RuntimeException if an error occurs during data retrieval.
     */
    public Optional<Inventory> findOneById(long id){
        try {
            return repository.findById(id);
        } catch (Exception e) {
            // Handle the exception or log it
            throw new RuntimeException("Failed to retrieve inventory data for product: " + id, e);
        }

    }
    /**
     * Update product details in the inventory.
     * @param updatedInventory The updated Inventory object.
     * @param id The ID of the product to update.
     * @throws RuntimeException if an error occurs during the update process.
     */
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

    /**
     * Add products to the inventory.
     * @param inventory The Inventory object to add.
     * @throws RuntimeException if an error occurs during the addition process.
     */

    public void addProducts(Inventory inventory){
        try {
            repository.save(inventory);
        } catch (Exception e) {
            // Handle the exception or log it
            throw new RuntimeException("Failed to add products to inventory", e);
        }

    }

    /**
     * Delete product from the inventory by ID.
     * @param id The ID of the product to delete.
     * @throws RuntimeException if an error occurs during the deletion process.
     */
    public void deleteProductByProductName(long id){
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            // Handle the exception or log it
            throw new RuntimeException("Failed to delete product: " + id, e);
        }
    }
}
