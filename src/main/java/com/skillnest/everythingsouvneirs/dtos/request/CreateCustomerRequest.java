package com.skillnest.everythingsouvneirs.dtos.request;

import lombok.Data;

@Data
public class CreateCustomerRequest {
    private String userId;
    private String username;
    private String phoneNumber;
    private String location;
}
