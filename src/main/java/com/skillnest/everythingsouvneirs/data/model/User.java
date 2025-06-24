package com.skillnest.everythingsouvneirs.data.model;

import com.skillnest.everythingsouvneirs.data.enums.Role;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Document
public class User implements UserDetails {
    @Id
    private String id = UUID.randomUUID().toString();

    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private String location;
    private String profilePicturePath;
    private LocalDateTime registrationDate;
    private boolean isGoogleUser;
    private LocalDateTime updatedAt;
    private boolean isActive = true;
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

}
