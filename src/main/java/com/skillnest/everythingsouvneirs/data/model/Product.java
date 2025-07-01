package com.skillnest.everythingsouvneirs.data.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Document(collection = "products")
@Data
public class Product {
    @Id
    private String id;

    @NotBlank(message = "Product title is required")
    @Size(min = 2, max = 200, message = "Title must be between 2 and 200 characters")
    private String title;

    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    private String description;

    @NotNull(message = "Price range is required")
    private PriceRange priceRange;

    private List<ProductImage> images = new ArrayList<>();

    @Field("is_popular")
    private boolean isPopular = false;

    @Field("is_active")
    private boolean isActive = true;

    private ProductSpecifications specifications;

    private Inventory inventory;

    private SEO seo;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


    // Helper method for formatted price range
    public String getFormattedPriceRange() {
        if (priceRange.getMin().equals(priceRange.getMax())) {
            return String.format("%s %,d", priceRange.getCurrency(), priceRange.getMin());
        }
        return String.format("%s %,d - %,d", priceRange.getCurrency(), priceRange.getMin(), priceRange.getMax());
    }

}
