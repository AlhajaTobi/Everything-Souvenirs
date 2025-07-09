package com.skillnest.everythingsouvneirs.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage {
    private String url;
    private String alt;
    private boolean isPrimary;
}
