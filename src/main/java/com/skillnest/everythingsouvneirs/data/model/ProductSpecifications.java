package com.skillnest.everythingsouvneirs.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductSpecifications {
    private List<String> materials = new ArrayList<>();
    private List<String> sizes = new ArrayList<>();
    private List<String> colors = new ArrayList<>();
    private List<String> customizationOptions = new ArrayList<>();
}
