package com.fincuro.payload.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String timestamp;
    private Integer code;
    private String message;
}
