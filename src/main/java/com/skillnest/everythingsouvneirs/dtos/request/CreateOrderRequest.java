package com.skillnest.everythingsouvneirs.dtos.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private String customerId;
    private List<CreateOrderItemRequest> items;
}
