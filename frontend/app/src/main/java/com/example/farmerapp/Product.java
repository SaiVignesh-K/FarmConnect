//package com.example.farmerapp;
//
//
//
//
//public class Product {
//    private String itemName;
//    private double pricePerKg;
//    private double kgAvailable;
//    private Category category;
//    private String location;
//
//    private String farmerid;
//
//    public Product() {
//        // Required empty constructor for Firestore
//    }
//
//    public Product(String itemName, double pricePerKg, double kgAvailable, Category category, String location,String farmerid) {
//        this.itemName = itemName;
//        this.pricePerKg = pricePerKg;
//        this.kgAvailable = kgAvailable;
//        this.category = category;
//        this.location = location;
//        this.farmerid=farmerid;
//    }
//
//    public String getFarmerid() {
//        return farmerid;
//    }
//
//    public void setFarmerid(String farmerid) {
//        this.farmerid = farmerid;
//    }
//
//    public void setItemName(String itemName) {
//        this.itemName = itemName;
//    }
//
//    public void setPricePerKg(double pricePerKg) {
//        this.pricePerKg = pricePerKg;
//    }
//
//    public void setKgAvailable(double kgAvailable) {
//        this.kgAvailable = kgAvailable;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public String getItemName() {
//        return itemName;
//    }
//
//    public double getPricePerKg() {
//        return pricePerKg;
//    }
//
//    public double getKgAvailable() {
//        return kgAvailable;
//    }
//
//    public Category getCategory() {
//        return category;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//}
//
package com.example.farmerapp;

public class Product {
    private String itemName;
    private double pricePerKg;
    private double quantityAvailable;
    private String category;
    private String location;
    private String farmerId; // Add farmerId to the class

    public Product(String itemName, double pricePerKg, double quantityAvailable, String category, String location, String farmerId) {
        this.itemName = itemName;
        this.pricePerKg = pricePerKg;
        this.quantityAvailable = quantityAvailable;
        this.category = category;
        this.location = location;
        this.farmerId = farmerId; // Initialize farmerId
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
// Other getters and setters
}

