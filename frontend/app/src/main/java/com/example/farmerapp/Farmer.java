package com.example.farmerapp;


public class Farmer {
    public String farmerId;
    public String itemName;
    public String pricePerKg;
    public String kgAvailable;
    public String description;
    public String phoneNumber;

    public Farmer() {
        // Default constructor required for Firebase
    }

    public Farmer(String farmerId, String itemName, String pricePerKg, String kgAvailable, String description, String phoneNumber) {
        this.farmerId = farmerId;
        this.itemName = itemName;
        this.pricePerKg = pricePerKg;
        this.kgAvailable = kgAvailable;
        this.description = description;
        this.phoneNumber = phoneNumber;
    }
}
