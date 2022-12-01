package com.example.backend.model.dto;

import com.example.backend.common.model.AccountStatus;
import lombok.Data;

@Data
public class AuthenticationDto {
    private AccountStatus accountStatus;
    private String email;
    //    having account
    private AccountDto accountDto;
    private JsonWebToken jsonWebToken;
}
