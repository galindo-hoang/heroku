package com.example.backend.service.impl;

import com.example.backend.common.model.EmailDto;
import com.example.backend.exception.EmailException;
import com.example.backend.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}") private String sender;
    @Override
    public String sendEmailInviteToRoom(EmailDto emailDto) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setText(emailDto.getText());
            mailMessage.setTo(emailDto.getGmail());
            mailMessage.setSubject("CHECKING");
            mailSender.send(mailMessage);
            return "Send Invite "+emailDto.getGmail()+" Successfully...";
        } catch (Exception e) {
            throw new EmailException(e.getMessage());
        }
    }

    @Override
    public String sendEmailToValidationAccount(String email) {
        return null;
    }
}
