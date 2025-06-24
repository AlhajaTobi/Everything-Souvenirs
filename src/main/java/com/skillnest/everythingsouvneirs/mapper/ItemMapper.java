package com.skillnest.everythingsouvneirs.mapper;

import com.skillnest.everythingsouvneirs.data.model.Item;
import com.skillnest.everythingsouvneirs.dtos.response.ItemResponse;

public class ItemMapper {

    public static ItemResponse mapToItemResponse(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .productName(item.getProduct().getName())
                .quantity(item.getQuantity())
                .cartId(item.getCart() != null ? item.getCart().getId() : null)
                .quoteId(item.getQuote() != null ? item.getQuote().getId() : null)
                .build();
    }
}
