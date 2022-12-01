package com.example.backend.controller;

import com.example.backend.common.controller.BaseController;
import com.example.backend.common.utils.JwtTokenUtil;
import com.example.backend.mapper.AccountMapper;
import com.example.backend.mapper.RoomMapper;
import com.example.backend.model.dto.AccountDto;
import com.example.backend.model.dto.AuthenticationDto;
import com.example.backend.model.dto.JsonWebToken;
import com.example.backend.model.dto.RoomDto;
import com.example.backend.model.request.ValidateRequest;
import com.example.backend.service.AccountService;
import com.example.backend.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("account")
@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountController extends BaseController {
    private final AccountService accountService;
    private final AccountMapper accountMapper;
    private final RoomMapper roomMapper;
    private final RoomService roomService;

    @GetMapping("auth/loginSocial")
    public ResponseEntity<AuthenticationDto> authentication(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        System.out.println(oAuth2AuthenticationToken);
        return ResponseEntity.status(HttpStatus.OK).body(accountService.loginSocial(oAuth2AuthenticationToken));
    }

    @PostMapping("auth/register")
    public ResponseEntity<AuthenticationDto> register(@RequestBody AccountDto accountDto) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(accountService.register(accountDto));
    }
    @PostMapping("auth/validate/otp")
    public ResponseEntity<Boolean> validateAccount(@RequestBody ValidateRequest validateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.validateAccount(validateRequest));
    }

    @PostMapping("auth/loginTraditional")
    public ResponseEntity<AuthenticationDto> login(@RequestBody AccountDto accountDto) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.loginTraditional(accountDto));
    }

    @PostMapping("refreshToken")
    public ResponseEntity<JsonWebToken> refreshToken(@RequestBody JsonWebToken jsonWebToken) {
        log.error("======= " + jsonWebToken.getRefreshToken());
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(accountService.refreshToken(jsonWebToken.getRefreshToken()));
    }

    @PostMapping("update")
    public ResponseEntity<AccountDto> updateAccount(@ModelAttribute("value") AccountDto accountDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(accountMapper.entityToDto(accountService.update(accountDto)));
    }

    @GetMapping("listRoomCreated")
    public ResponseEntity<List<RoomDto>> getListRoomCreated(String email) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body((List<RoomDto>) roomService.getListRoomCreated(email).stream().map(roomMapper::entityToDto));
    }

    @GetMapping("listRoomJoined")
    public ResponseEntity<List<RoomDto>> getListRoomJoined(String email) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body((List<RoomDto>) roomService.fetchRoomsJoined(email).stream().map(roomMapper::entityToDto));
    }
}
