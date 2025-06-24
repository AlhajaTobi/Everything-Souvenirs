package com.skillnest.everythingsouvneirs.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OTPResponse {
    private String message;
    private String email;
    private String otp;
}
