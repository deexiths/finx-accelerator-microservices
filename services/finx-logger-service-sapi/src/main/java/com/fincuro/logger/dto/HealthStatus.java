package com.fincuro.logger.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = false, chain = true)
public class HealthStatus {

    private String status;
    private String activeProfile;
}
