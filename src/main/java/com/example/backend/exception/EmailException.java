package com.example.backend.exception;

import com.example.backend.common.exception.TechnicalException;

public class EmailException extends TechnicalException {
    public EmailException(String message) {
        super(message);
    }
}
