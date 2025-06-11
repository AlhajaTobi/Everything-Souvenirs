package com.skillnest.everythingsouvneirs.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UploadResponse {
    private String message;
    private String cloudinaryUrl;
}
