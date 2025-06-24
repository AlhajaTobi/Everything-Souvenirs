package com.skillnest.everythingsouvneirs.mapper;

import com.skillnest.everythingsouvneirs.data.model.Cart;
import com.skillnest.everythingsouvneirs.data.model.Item;
import com.skillnest.everythingsouvneirs.dtos.response.CartResponse;
import com.skillnest.everythingsouvneirs.dtos.response.ItemResponse;

import java.util.stream.Collectors;

public class CartMapper {

    public static CartResponse mapToCartResponse(Cart cart) {
        return CartResponse.builder()
                .cartId(cart.getId())
                .customerId(cart.getCustomer().getId())
                .items(
                        cart.getItems().stream()
                                .map(CartMapper::mapToItemResponse)
                                .collect(Collectors.toList())
                )
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
