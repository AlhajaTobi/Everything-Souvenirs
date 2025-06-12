package com.skillnest.everythingsouvneirs.dtos.response;

import com.skillnest.everythingsouvneirs.data.model.User;
import lombok.Data;

@Data
public class CreatedUserResponse {
    private User user;
    private String message;
    private String jwtToken;

}
