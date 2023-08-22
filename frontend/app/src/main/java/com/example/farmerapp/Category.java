package com.example.farmerapp;

public enum Category {
    VEGETABLES("Vegetables"),
    FRUITS("Fruits"),
    DAIRY("Dairy"),
    POULTRY("Poultry"),
    SEEDS("Seeds");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Category fromDisplayName(String displayName) {
        for (Category category : Category.values()) {
            if (category.displayName.equals(displayName)) {
                return category;
            }
        }
        return null;
    }
}

