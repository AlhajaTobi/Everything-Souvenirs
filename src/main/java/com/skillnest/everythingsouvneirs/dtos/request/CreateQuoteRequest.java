package com.skillnest.everythingsouvneirs.dtos.request;

import com.skillnest.everythingsouvneirs.data.enums.QuoteStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateQuoteRequest {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
    private String phone;

    @Size(max = 200, message = "Company name must not exceed 200 characters")
    private String company;

    @NotBlank(message = "Product is required")
    private String product;

    private String quantity;

    @Size(max = 1000, message = "Message must not exceed 1000 characters")
    private String message;

    private QuoteStatus status = QuoteStatus.PENDING;
}
