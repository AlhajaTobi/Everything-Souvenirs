package com.skillnest.everythingsouvneirs.dtos.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class QuoteResponse {
    private String id;
    private String status;
    private LocalDateTime createdAt;
    private List<ItemResponse> items;
}
