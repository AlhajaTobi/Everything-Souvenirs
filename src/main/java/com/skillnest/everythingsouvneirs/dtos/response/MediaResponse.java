package com.skillnest.everythingsouvneirs.dtos.response;

import com.skillnest.everythingsouvneirs.data.enums.MediaType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MediaResponse {
    private String id;
    private String url;
    private MediaType type;
}
