package com.example.backend.exception;

import com.example.backend.common.exception.BusinessException;

public class ResourceInvalidException extends BusinessException {
    public ResourceInvalidException(String message) {
        super(message);
    }
}
