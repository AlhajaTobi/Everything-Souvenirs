package com.skillnest.everythingsouvneirs.data.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Document
public class Cart {
    @Id
    private String id = UUID.randomUUID().toString();

    @DBRef
    private Customer customer;

    @DBRef
    private List<Item> items;
}
