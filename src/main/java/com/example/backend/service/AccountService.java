package com.example.backend.service;

import com.example.backend.model.dto.AccountDto;
import com.example.backend.model.dto.AuthenticationDto;
import com.example.backend.model.dto.JsonWebToken;
import com.example.backend.model.entity.AccountEntity;
import com.example.backend.model.request.ValidateRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public interface AccountService extends UserDetailsService {
//    AccountEntity accountValidate(ValidateRequest validateRequest);
    AuthenticationDto loginSocial(OAuth2AuthenticationToken oAuth2AuthenticationToken);
    AccountEntity update(AccountDto accountDto);
    AuthenticationDto register(AccountDto accountDto);
    Boolean validateAccount(ValidateRequest validateRequest);
    AuthenticationDto loginTraditional(AccountDto accountDto);
    JsonWebToken refreshToken(String refreshToken);
}
