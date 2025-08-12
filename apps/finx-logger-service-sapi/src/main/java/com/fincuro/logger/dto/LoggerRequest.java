package com.fincuro.logger.dto;

import lombok.Data;

@Data
public class LoggerRequest {
    private String logType;
    private String serviceName;
    private String message;
    private String correlationId;
    private StackTraceElement[] stackTrace;
}
