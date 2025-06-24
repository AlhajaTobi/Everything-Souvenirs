package com.skillnest.everythingsouvneirs.dtos.request;

import com.skillnest.everythingsouvneirs.data.model.Media;
import lombok.Data;

import java.util.List;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private List<Media> mediaList;
}
