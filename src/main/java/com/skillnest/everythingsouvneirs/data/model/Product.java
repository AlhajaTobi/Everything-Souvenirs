package com.skillnest.everythingsouvneirs.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Document
@Data
public class Product {
    @Id
    private String id = UUID.randomUUID().toString();

    private String name;
    private String description;

    @DBRef
    private List<Media> mediaList;
}
