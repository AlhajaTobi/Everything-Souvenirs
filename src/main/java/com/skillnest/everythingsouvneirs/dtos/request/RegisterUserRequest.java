package com.skillnest.everythingsouvneirs.dtos.request;

import com.skillnest.everythingsouvneirs.data.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterUserRequest {
    private String email;
    private String otp;
    private Role role;
}
