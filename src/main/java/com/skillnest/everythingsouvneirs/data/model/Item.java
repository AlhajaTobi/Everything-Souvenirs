package com.skillnest.everythingsouvneirs.data.model;

import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;


@Data
@Document
public class Item {
    @Id
    private String id = UUID.randomUUID().toString();

    @DBRef
    private Product product;
    private int quantity;

    @DBRef
    private Cart cart;

    @DBRef
    private Order order;

    @DBRef
    private Quote quote;
}
