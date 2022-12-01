package com.example.backend.exception;

import com.example.backend.common.exception.BusinessException;

public class PasswordException extends BusinessException {
    public PasswordException(String message) {
        super(message);
    }
}
