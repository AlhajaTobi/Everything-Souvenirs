package com.skillnest.everythingsouvneirs.data.model;

import com.skillnest.everythingsouvneirs.data.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class PendingUser {
    @Id
    private Long id;
    private String email;
    private String password;
    private String otp;
    private LocalDateTime expiryTime;
    private Role role;
}
