package com.skillnest.everythingsouvneirs.data.model;

import lombok.Data;

@Data
public class ProductImage {
    private String url;
    private String alt;
    private boolean isPrimary;

    public ProductImage() {}

    public ProductImage(String url, String alt, boolean isPrimary) {
        this.url = url;
        this.alt = alt;
        this.isPrimary = isPrimary;
    }


    public boolean isPrimary() { return isPrimary; }
    public void setPrimary(boolean primary) { isPrimary = primary; }
}

