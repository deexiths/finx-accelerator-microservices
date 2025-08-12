package com.fincuro.payload.service.transformer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fincuro.payload.dto.request.RequestDto;
import com.fincuro.payload.service.TransformerConfigService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransformerConfigServiceTest {

    @InjectMocks
    TransformerConfigService transformerConfigService;

    @Mock
    MessageTransformer messageTransformer;

    @Test
    void testTransformPayload() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode inputJson = objectMapper.readTree("{\"test\": \"transferPayload\"}");
        JsonNode mapper = objectMapper.readTree("{\"test\": \"transferPayload\"}");
        
        RequestDto requestDto = new RequestDto();
        requestDto.setInputJson(inputJson);
        requestDto.setMapper(mapper);
        
        when(messageTransformer.transform("{\"test\":\"transferPayload\"}", "{\"test\":\"transferPayload\"}")).thenReturn(transformResponse());
        String response = transformerConfigService.transformPayload(requestDto);
        assertThat(response).isNotNull();
        assertEquals(response, transformResponse());
    }


    private String transformResponse() {
        return "{\n" +
                "    \"clientId\": \"1234\",\n" +
                "    \"accountHolderKey\": \"5678\",\n" +
                "    \"accountHolderType\": \"Individual\",\n" +
                "    \"accountType\": \"Savings\",\n" +
                "    \"amount\": \"10000\",\n" +
                "    \"tenure\": \"12\",\n" +
                "    \"startDate\": \"2023-02-21\",\n" +
                "    \"maturityAmount\": \"5500\",\n" +
                "    \"interestRate\": \"1.5\",\n" +
                "    \"currencyCode\": \"USD\",\n" +
                "    \"productTypeKey\": \"ABCD\",\n" +
                "    \"name\": \"My Savings Account\"\n" +
                "}";
    }
}
