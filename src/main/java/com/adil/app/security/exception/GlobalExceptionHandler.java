package com.adil.app.security.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleRuntimeException(RuntimeException ex, WebRequest request) {
        return new ErrorResponse("Invalid username or password");
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ErrorResponse {
        private String message;
    }
}
