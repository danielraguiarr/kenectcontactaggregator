package com.kenectcontactaggregator.kenectcontactaggregator.handler;

import com.kenectcontactaggregator.kenectcontactaggregator.exception.ContactNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<String> handleContactNotFound(ContactNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getErrorMessage());
    }
}
