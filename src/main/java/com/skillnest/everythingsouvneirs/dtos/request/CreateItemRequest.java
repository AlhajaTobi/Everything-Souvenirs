package com.skillnest.everythingsouvneirs.dtos.request;

import lombok.Data;

@Data
public class CreateItemRequest {
    private String productId;
    private int quantity;
    private String cartId;
    private String quoteId;
    private String orderId;
}
