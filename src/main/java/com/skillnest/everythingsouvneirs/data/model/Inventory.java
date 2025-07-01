package com.skillnest.everythingsouvneirs.data.model;

import jakarta.validation.constraints.Min;

public class Inventory {
    @Min(value = 0, message = "Stock quantity cannot be negative")
    private Integer stockQuantity = 0;

    @Min(value = 1, message = "Minimum order must be at least 1")
    private Integer minimumOrder = 1;

    // Constructors
    public Inventory() {}

    public Inventory(Integer stockQuantity, Integer minimumOrder) {
        this.stockQuantity = stockQuantity;
        this.minimumOrder = minimumOrder;
    }

    // Getters and Setters
    public Integer getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }

    public Integer getMinimumOrder() { return minimumOrder; }
    public void setMinimumOrder(Integer minimumOrder) { this.minimumOrder = minimumOrder; }
}
