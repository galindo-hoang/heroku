package com.example.backend.model.request;

import lombok.Data;

@Data
public class ValidateRequest {
    String email;
    String otp;
}
