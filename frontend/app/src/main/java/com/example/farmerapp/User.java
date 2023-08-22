package com.example.farmerapp;

public class User {
    private String email;
    private String username;
    private boolean isFarmer;

    public User() {
        // Default constructor required for Firebase
    }

    public User(String email, String username, boolean isFarmer) {
        this.email = email;
        this.username = username;
        this.isFarmer = isFarmer;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFarmer(boolean farmer) {
        isFarmer = farmer;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public boolean isFarmer() {
        return isFarmer;
    }
}

