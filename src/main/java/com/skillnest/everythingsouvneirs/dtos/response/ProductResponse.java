package com.skillnest.everythingsouvneirs.dtos.response;

import com.skillnest.everythingsouvneirs.data.model.Media;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private List<Media> mediaList;
}
