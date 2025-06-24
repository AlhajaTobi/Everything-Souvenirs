package com.skillnest.everythingsouvneirs.data.repository;

import com.skillnest.everythingsouvneirs.data.model.OTP;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OTPRepository extends MongoRepository<OTP, String> {
    Optional<OTP> findByEmailAndOtp(String email, String otp);
}
