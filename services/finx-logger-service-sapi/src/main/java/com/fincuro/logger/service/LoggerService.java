package com.fincuro.logger.service;

import com.fincuro.logger.dto.LoggerRequest;
import com.fincuro.logger.dto.LoggerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class LoggerService {

    private static final Logger logger = LoggerFactory.getLogger(LoggerService.class);

    /**
     * Logs the information from the provided LoggerRequest.
     *
     * @param loggerRequest The LoggerRequest object containing log information.
     * @return A LoggerResponse object indicating the status of the logging operation.
     */
    public LoggerResponse loggerMethod(LoggerRequest loggerRequest) {
        String logType = loggerRequest.getLogType();
        LoggerResponse response = new LoggerResponse();

        // Prepare log message components
        String serviceName = loggerRequest.getServiceName();
        String message = loggerRequest.getMessage();
        String correlationId = loggerRequest.getCorrelationId();
        StackTraceElement[] stackTrace = loggerRequest.getStackTrace();

        // Construct the log message
        StringBuilder logMessage = new StringBuilder();
        logMessage.append(String.format("serviceName=%s, Message=%s", serviceName, message));

        // Add correlationId if not null or empty
        if (correlationId != null && !correlationId.isEmpty()) {
            logMessage.append(String.format(", finx-client-request-correlationId=%s", correlationId));
        }

        // Add stackTrace if not null or empty
        if (stackTrace != null && stackTrace.length > 0) {
            logMessage.append(String.format(", stackTrace=%s", Arrays.asList(stackTrace)));
        }

        // Log based on logType
        switch (logType.toUpperCase()) {
            case "INFO":
                logger.info("ℹ️ {}", logMessage);
                break;
            case "DEBUG":
                logger.debug("🐞 {}", logMessage);
                break;
            case "WARN":
                logger.warn("⚠ {}" , logMessage);
                break;
            case "ERROR":
                logger.error("❌{}", logMessage);
                break;
            default:
                logger.info("ℹ️Unrecognized log type: {} , logMessage ={}", logType, logMessage);
                break;
        }

        response.setStatus(true);
        response.setMessage("Success");
        return response;
    }

}
