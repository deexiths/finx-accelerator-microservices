package com.fincuro.logger.controller;


import com.fincuro.logger.dto.HealthStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HealthCheckController {
    private final Environment environment;


    public HealthCheckController(Environment environment) {
        this.environment = environment;

    }

    @GetMapping(value = "/health/check", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HealthStatus> getBuildInfo() {
        log.info("Calling Health check");
        final HealthStatus healthStatus = new HealthStatus().setStatus("UP").setActiveProfile(String.join(",", environment.getActiveProfiles()));
        return ResponseEntity.ok(healthStatus);
    }

}
