
package com.example.farmerapp;
import com.example.farmerapp.Domain.ProductDomain;
public class Product {
    private String itemName;
    private double pricePerKg;
    private double quantityAvailable;
    private String category;
    private String location;
    private String farmerId;

    public Product() {
    }

    public Product(String itemName, double pricePerKg, double quantityAvailable, String category, String location, String farmerId) {
        this.itemName = itemName;
        this.pricePerKg = pricePerKg;
        this.quantityAvailable = quantityAvailable;
        this.category = category;
        this.location = location;
        this.farmerId = farmerId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(double pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    public double getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(double quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

}

