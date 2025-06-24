package com.skillnest.everythingsouvneirs.service;

import com.skillnest.everythingsouvneirs.dtos.request.CreateOrderRequest;
import com.skillnest.everythingsouvneirs.dtos.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(CreateOrderRequest request);

    OrderResponse getOrderById(String id);

    List<OrderResponse> getAllOrders();
}
