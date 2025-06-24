package com.skillnest.everythingsouvneirs.mapper;

import com.skillnest.everythingsouvneirs.data.model.Item;
import com.skillnest.everythingsouvneirs.data.model.Order;
import com.skillnest.everythingsouvneirs.dtos.response.ItemResponse;
import com.skillnest.everythingsouvneirs.dtos.response.OrderResponse;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderResponse mapToOrderResponse(Order order) {
        return OrderResponse.builder()
                .orderId(order.getId())
                .customerId(order.getCustomer().getId())
                .customerName(order.getCustomer().getUsername())
                .orderDate(order.getOrderDate())
                .status(order.getStatus())
                .items(order.getItems().stream().map(OrderMapper::mapToItemResponse).collect(Collectors.toList()))
                .build();
    }

    private static ItemResponse mapToItemResponse(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .productName(item.getProduct().getName())
                .quantity(item.getQuantity())
                .build();
    }
}
