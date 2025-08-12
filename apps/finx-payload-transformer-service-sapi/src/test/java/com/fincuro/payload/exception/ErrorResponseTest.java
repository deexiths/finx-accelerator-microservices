package com.fincuro.payload.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


class ErrorResponseTest {
    @Test
    void errorResponseTests() {
        String timestamp = LocalDateTime.now().toString();
        String message = "";
        Integer success = 200;
        ErrorResponse response = new ErrorResponse(timestamp, success, "");
        Assertions.assertAll(
                () -> assertThat(response.getTimestamp()).isEqualTo(timestamp),
                () -> assertThat(response.getCode()).isEqualTo(success),
                () -> assertThat(response.getMessage()).isEqualTo(message)
        );

    }

}
