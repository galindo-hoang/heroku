package com.example.backend.service;

import com.example.backend.common.model.EmailDto;

public interface EmailService {
    String sendEmailInviteToRoom(EmailDto emailDto);
    String sendEmailToValidationAccount(String email);
}
