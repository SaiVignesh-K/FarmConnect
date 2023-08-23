package com.example.farmerapp.Domain;

import com.example.farmerapp.Product;
import java.io.Serializable;

public class ProductDomain implements Serializable {
    private String title;
    private String pic;
    private double cost;
    private double available;
    private String farmer;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getAvailable() {
        return available;
    }

    public void setAvailable(double available) {
        this.available = available;
    }

    public String getFarmer() {
        return farmer;
    }

    public void setFarmer(String farmer) {
        this.farmer = farmer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public ProductDomain(String title, String pic, double cost, double available, String farmer) {
        this.title = title;
        this.pic = pic;
        this.cost = cost;
        this.available = available;
        this.farmer = farmer;
    }



}
