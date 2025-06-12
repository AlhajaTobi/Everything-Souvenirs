package com.skillnest.everythingsouvneirs.dtos.response;

import com.skillnest.everythingsouvneirs.data.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private User user;
    private String token;
    private String message;
}
