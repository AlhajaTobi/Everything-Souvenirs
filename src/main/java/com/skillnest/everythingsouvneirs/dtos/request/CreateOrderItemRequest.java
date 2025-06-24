package com.skillnest.everythingsouvneirs.dtos.request;

import lombok.Data;

@Data
public class CreateOrderItemRequest {
    private String productId;
    private int quantity;
}
