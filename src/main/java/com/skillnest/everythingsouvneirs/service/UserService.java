package com.skillnest.everythingsouvneirs.service;

import com.skillnest.everythingsouvneirs.dtos.request.*;
import com.skillnest.everythingsouvneirs.dtos.response.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    OTPResponse sendVerificationOTP(CreateUserRequest request);

    CreatedUserResponse register(RegisterUserRequest request);

    UploadResponse uploadFile(MultipartFile file) throws IOException;

    LoginResponse login(LoginRequest loginRequest);

    UpdateUserProfileResponse updateProfile(UpdateUserProfileRequest updateUserProfileRequest);

    ResetPasswordResponse resetPassword(ChangePasswordRequest changePasswordRequest);

    ResetPasswordResponse sendResetOtp(ResetPasswordRequest resetPasswordRequest);

    FoundResponse findUserById(String id);

    LoginResponse handleGoogleLogin(String email, String name);
}
