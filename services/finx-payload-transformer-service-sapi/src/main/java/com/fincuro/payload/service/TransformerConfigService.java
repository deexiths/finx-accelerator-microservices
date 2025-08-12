package com.fincuro.payload.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fincuro.payload.dto.request.RequestDto;
import com.fincuro.payload.dto.request.RequestStringDto;
import com.fincuro.payload.service.transformer.MessageTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.fincuro.payload.service.util.ConstantUtils.DB_ERROR_MESSAGE;
import static com.fincuro.payload.service.util.ConstantUtils.ERROR;

@Service
public class TransformerConfigService {

    private static final Logger logger = LoggerFactory.getLogger(TransformerConfigService.class);

    MessageTransformer messageTransformer;


    @Autowired
    public TransformerConfigService(MessageTransformer messageTransformer) {
        this.messageTransformer = messageTransformer;
    }

    /**
     * service method-transformConfig
     *
     * @param requestDto The RequestDto object containing the payload data for transformation.
     * @return A ResponseEntity containing a String representing the transformed payload
     */
    public String transformPayload(RequestDto requestDto) {
        try {
            JsonNode inputJson = requestDto.getInputJson();
            JsonNode mapper = requestDto.getMapper();

            ObjectMapper objectMapper = new ObjectMapper();
            String inputJsonString = objectMapper.writeValueAsString(inputJson);
            String mapperString = objectMapper.writeValueAsString(mapper);
            logger.info("inputJsonString                 :{}", inputJsonString);
            logger.error("mapperString:{}", mapperString);
            return messageTransformer.transform(inputJsonString,mapperString);
        } catch (Exception e) {
            String errorMessage = DB_ERROR_MESSAGE + e.getMessage();
            logger.error(ERROR, errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }



    public String transformPayloads(RequestStringDto requestStringDto) {
            try {
                return messageTransformer.transform(requestStringDto.getInputJson(), requestStringDto.getMapper());
            } catch (Exception e){
                String errorMessage = DB_ERROR_MESSAGE + e.getMessage();
                logger.error(ERROR, errorMessage);
                throw new RuntimeException(errorMessage);
            }
        }
    }