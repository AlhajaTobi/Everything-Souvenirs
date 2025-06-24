package com.skillnest.everythingsouvneirs.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document
@Data
public class OTP {

    @Id
    private String id = UUID.randomUUID().toString();
    private String otp;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private boolean used = false;
}
