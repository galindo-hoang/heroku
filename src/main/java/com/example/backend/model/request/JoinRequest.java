package com.example.backend.model.request;

import lombok.Data;

@Data
public class JoinRequest {
    String url;
    String code;
    String email;
}
