package com.skillnest.everythingsouvneirs.data.model;

import java.util.List;
import java.util.ArrayList;

public class ProductSpecifications {
    private List<String> materials = new ArrayList<>();
    private List<String> sizes = new ArrayList<>();
    private List<String> colors = new ArrayList<>();
    private List<String> customizationOptions = new ArrayList<>();

    // Constructors
    public ProductSpecifications() {}

    // Getters and Setters
    public List<String> getMaterials() { return materials; }
    public void setMaterials(List<String> materials) { this.materials = materials; }

    public List<String> getSizes() { return sizes; }
    public void setSizes(List<String> sizes) { this.sizes = sizes; }

    public List<String> getColors() { return colors; }
    public void setColors(List<String> colors) { this.colors = colors; }

    public List<String> getCustomizationOptions() { return customizationOptions; }
    public void setCustomizationOptions(List<String> customizationOptions) { this.customizationOptions = customizationOptions; }
}

