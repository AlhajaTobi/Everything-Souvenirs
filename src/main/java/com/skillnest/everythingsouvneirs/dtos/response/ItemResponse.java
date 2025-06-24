package com.skillnest.everythingsouvneirs.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemResponse {
    private String id;
    private String productName;
    private int quantity;
    private String cartId;
    private String quoteId;
}
