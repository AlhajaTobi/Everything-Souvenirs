package com.skillnest.everythingsouvneirs.dtos.request;

import com.skillnest.everythingsouvneirs.data.enums.MediaType;
import lombok.Data;

@Data
public class MediaRequest {
    private String url;
    private MediaType type;
}
