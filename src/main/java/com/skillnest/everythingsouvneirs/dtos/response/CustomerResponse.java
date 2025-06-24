package com.skillnest.everythingsouvneirs.dtos.response;

import com.skillnest.everythingsouvneirs.data.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {
    private User user;
    private String fullName;
    private String email;
    private String phoneNumber;
}
