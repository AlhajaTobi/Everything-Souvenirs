package com.skillnest.everythingsouvneirs.data.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceRange {

    @NotNull(message = "Minimum price is required")
    @Positive(message = "Minimum price must be positive")
    private Integer min;

    @NotNull(message = "Maximum price is required")
    @Positive(message = "Maximum price must be positive")
    private Integer max;

    private String currency = "NGN";

    public PriceRange(Integer min, Integer max) {
        this.min = min;
        this.max = max;
        this.currency = "NGN";
    }
}
