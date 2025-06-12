package com.skillnest.everythingsouvneirs.dtos.request;

import com.skillnest.everythingsouvneirs.data.enums.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginRequest {
    private String email;
    private String password;
    private Role role;
}
