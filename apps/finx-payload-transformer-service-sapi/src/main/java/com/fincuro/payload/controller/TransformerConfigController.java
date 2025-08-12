package com.fincuro.payload.controller;

import com.fincuro.payload.dto.request.RequestDto;
import com.fincuro.payload.dto.request.RequestStringDto;
import com.fincuro.payload.service.TransformerConfigService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.fincuro.payload.service.util.ConstantUtils.ACCEPT;
import static com.fincuro.payload.service.util.ConstantUtils.APPLICATION_JSON;

@RestController
@RequestMapping(value = "transformer")
public class TransformerConfigController {

    private static final Logger logger = LoggerFactory.getLogger(TransformerConfigController.class);
    TransformerConfigService service;

    @Autowired
    public TransformerConfigController(TransformerConfigService service) {
        this.service = service;
    }

    /**
     * controller method-transformerPayload
     *
     * @param requestDto The RequestDto object containing the payload data for transformation.
     * @return A ResponseEntity containing a String representing the transformed payload
     */

    @Operation(summary = "Create a payloadRequest", tags = "transform")
    @PostMapping(value = "")
    public ResponseEntity<String> requestTransformerPayload(@Valid @RequestBody RequestDto requestDto) {

        logger.info("TransformerConfigControllers.transformerPayload RequestDto={}", requestDto);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(ACCEPT, APPLICATION_JSON);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(service.transformPayload(requestDto));
    }
    @Operation(summary = "Create a payloadRequest", tags = "transform")
    @PostMapping(value = "/string")
    public ResponseEntity<Object> requestStringPayload(@Valid @RequestBody RequestStringDto requestStringDto){

        logger.info("TransformerConfigController.transformerPayload RequestStringDto={}",requestStringDto);

        HttpHeaders responseHeadersString = new HttpHeaders();
        responseHeadersString.set(ACCEPT, APPLICATION_JSON);
        return ResponseEntity.ok()
                .headers(responseHeadersString)
                .body(service.transformPayloads(requestStringDto));

    }

}
