package com.fincuro.logger.controller;

import com.fincuro.logger.dto.LoggerRequest;
import com.fincuro.logger.dto.LoggerResponse;
import com.fincuro.logger.service.LoggerService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="logger")
public class LoggerController {

    LoggerService loggerService;

    @Autowired
    public LoggerController(LoggerService loggerService)
    {this.loggerService=loggerService;}


    /**
     * Handling incoming requests to log information based on the provided LoggerRequest.
     *
     * @param loggerRequest The LoggerRequest object containing log information.
     * @return A ResponseEntity containing a LoggerResponse object indicating the status of the logging operation.
     */
    @PostMapping(value = "")
    public ResponseEntity<LoggerResponse> loggerController(@RequestBody LoggerRequest loggerRequest) {
        LoggerResponse response = loggerService.loggerMethod(loggerRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

}
