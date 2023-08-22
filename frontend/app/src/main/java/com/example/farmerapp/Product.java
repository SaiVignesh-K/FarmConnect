package com.example.farmerapp;


public class Product {
    public String productId;
    public String itemName;
    public String pricePerKg;
    public String kgAvailable;
    public String description;
    public String farmerId;

    public Product() {
        // Default constructor required for Firebase
    }

    public Product(String productId, String itemName, String pricePerKg, String kgAvailable, String description, String farmerId) {
        this.productId = productId;
        this.itemName = itemName;
        this.pricePerKg = pricePerKg;
        this.kgAvailable = kgAvailable;
        this.description = description;
        this.farmerId = farmerId;
    }
}
