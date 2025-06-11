package com.skillnest.everythingsouvneirs.dtos.request;

import lombok.Data;

@Data
public class GoogleLoginRequest {
    private String token;
    private String role;

}
