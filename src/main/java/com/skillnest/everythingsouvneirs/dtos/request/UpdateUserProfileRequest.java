package com.skillnest.everythingsouvneirs.dtos.request;

import com.skillnest.everythingsouvneirs.data.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateUserProfileRequest {
    private String fullName;
    private String email;
    private String username;
    private String location;
    private String phoneNumber;
    private String profilePicturePath;
    private boolean isActive;
    private Role roles;
}
