package com.skillnest.everythingsouvneirs.mapper;

import com.skillnest.everythingsouvneirs.data.model.Item;
import com.skillnest.everythingsouvneirs.data.model.Product;
import com.skillnest.everythingsouvneirs.data.model.Quote;
import com.skillnest.everythingsouvneirs.dtos.request.CreateQuoteRequest;
import com.skillnest.everythingsouvneirs.dtos.response.ItemResponse;
import com.skillnest.everythingsouvneirs.dtos.response.QuoteResponse;

import java.util.List;
import java.util.stream.Collectors;

public class QuoteMapper {

    public static Quote mapToQuote(CreateQuoteRequest request) {
        Quote quote = new Quote();
        quote.setStatus(request.getStatus());

        List<Item> items = request.getItems().stream().map(itemReq -> {
            Item item = new Item();
            Product product = new Product();
            product.setId(itemReq.getProductId());
            item.setProduct(product);
            item.setQuantity(itemReq.getQuantity());
            return item;
        }).collect(Collectors.toList());

        quote.setItems(items);
        return quote;
    }

    public static QuoteResponse mapToQuoteResponse(Quote quote) {
        return QuoteResponse.builder()
                .id(quote.getId())
                .status(quote.getStatus())
                .createdAt(quote.getCreatedAt())
                .items(
                        quote.getItems().stream().map(item -> ItemResponse.builder()
                                .id(item.getId())
                                .productName(item.getProduct().getName())
                                .quantity(item.getQuantity())
                                .build()).collect(Collectors.toList())
                )
                .build();
    }
}
