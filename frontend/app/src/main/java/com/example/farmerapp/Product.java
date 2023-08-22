package com.example.farmerapp;




public class Product {
    private String itemName;
    private double pricePerKg;
    private double kgAvailable;
    private Category category;
    private String location;

    public Product() {
        // Required empty constructor for Firestore
    }

    public Product(String itemName, double pricePerKg, double kgAvailable, Category category, String location) {
        this.itemName = itemName;
        this.pricePerKg = pricePerKg;
        this.kgAvailable = kgAvailable;
        this.category = category;
        this.location = location;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPricePerKg(double pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    public void setKgAvailable(double kgAvailable) {
        this.kgAvailable = kgAvailable;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPricePerKg() {
        return pricePerKg;
    }

    public double getKgAvailable() {
        return kgAvailable;
    }

    public Category getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }
}

