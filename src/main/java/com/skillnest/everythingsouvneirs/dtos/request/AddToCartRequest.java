package com.skillnest.everythingsouvneirs.dtos.request;

import lombok.Data;

@Data
public class AddToCartRequest {
    private String customerId;
    private String productId;
    private int quantity;
}
