package com.skillnest.everythingsouvneirs.data.model;

import com.skillnest.everythingsouvneirs.data.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Document
public class PendingUser {
    @Id
    private String id = UUID.randomUUID().toString();
    private String email;
    private String password;
    private String otp;
    private LocalDateTime expiryTime;
    private Role role;
}
