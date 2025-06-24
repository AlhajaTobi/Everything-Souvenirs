package com.skillnest.everythingsouvneirs.dtos.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderResponse {
    private String orderId;
    private String customerId;
    private String customerName;
    private LocalDateTime orderDate;
    private String status;
    private List<ItemResponse> items;
}
