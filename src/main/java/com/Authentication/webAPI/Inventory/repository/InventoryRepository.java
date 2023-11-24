package com.Authentication.webAPI.Inventory.repository;

import com.Authentication.webAPI.Inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

    @Repository
    public interface InventoryRepository extends JpaRepository<Inventory,Long> {

        Optional<Inventory> findByProductName(String productName);

        void deleteByProductName(String productName);

    }

