package com.kenectcontactaggregator.kenectcontactaggregator.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class ContactNotFoundException extends RuntimeException {
    private final String errorMessage;
}
