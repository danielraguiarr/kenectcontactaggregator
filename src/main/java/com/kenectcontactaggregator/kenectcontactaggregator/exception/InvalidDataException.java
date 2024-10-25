package com.kenectcontactaggregator.kenectcontactaggregator.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InvalidDataException extends RuntimeException {
    private final String fieldName;
    private final String errorMessage;
}
