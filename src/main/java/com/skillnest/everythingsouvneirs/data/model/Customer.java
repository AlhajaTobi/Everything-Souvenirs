package com.skillnest.everythingsouvneirs.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Document
public class Customer extends User{

    @DBRef
    private List<Order> orders;

    private Cart cart;



}
