package com.skillnest.everythingsouvneirs.data.model;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PriceRange {
    @NotNull(message = "Minimum price is required")
    @Positive(message = "Minimum price must be positive")
    private Integer min;

    @NotNull(message = "Maximum price is required")
    @Positive(message = "Maximum price must be positive")
    private Integer max;

    private String currency = "NGN";

    // Constructors
    public PriceRange() {}

    public PriceRange(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }

    public PriceRange(Integer min, Integer max, String currency) {
        this.min = min;
        this.max = max;
        this.currency = currency;
    }

    // Getters and Setters
    public Integer getMin() { return min; }
    public void setMin(Integer min) { this.min = min; }

    public Integer getMax() { return max; }
    public void setMax(Integer max) { this.max = max; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}

