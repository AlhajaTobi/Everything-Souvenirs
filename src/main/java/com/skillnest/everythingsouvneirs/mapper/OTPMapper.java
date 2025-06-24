package com.skillnest.everythingsouvneirs.mapper;

import com.skillnest.everythingsouvneirs.data.model.OTP;
import com.skillnest.everythingsouvneirs.dtos.response.OTPResponse;
import com.skillnest.userservice.util.OTPGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

public class OTPMapper {

    public static OTP mapToOTP(String email) {
        OTP otp = new OTP();
        otp.setOtp(OTPGenerator.generateOtp());
        otp.setEmail(email);
        otp.setCreatedAt(LocalDateTime.now());
        otp.setExpiresAt(LocalDateTime.now().plusMinutes(2));
        return otp;
    }
    public static OTPResponse mapToOTPResponse(String message, String email,String otp) {
        OTPResponse otpResponse = new OTPResponse();
        otpResponse.setMessage(message);
        otpResponse.setEmail(email);
        otpResponse.setOtp(otp);
        return otpResponse;
    }
}
