package com.skillnest.everythingsouvneirs.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Document
public class Order {
    @Id
    private String id = UUID.randomUUID().toString();

    @DBRef
    private Customer customer;

    private LocalDateTime orderDate;
    private String status;

    @DBRef
    private List<Item> items;
}

