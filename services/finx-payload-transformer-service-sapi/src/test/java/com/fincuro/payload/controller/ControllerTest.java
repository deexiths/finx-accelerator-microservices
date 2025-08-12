package com.fincuro.payload.controller;

import com.fincuro.payload.dto.request.RequestDto;
import com.fincuro.payload.service.TransformerConfigService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ControllerTest {

    @InjectMocks
    TransformerConfigController controller;

    @Mock
    TransformerConfigService service;

    @Test
    void testTransformerPayload() {
        RequestDto requestDto = new RequestDto();
        when(service.transformPayload(requestDto)).thenReturn(response());
        ResponseEntity<String> stringResponseEntity = controller.requestTransformerPayload(requestDto);
        assertThat(stringResponseEntity).isNotNull();
        assertThat(stringResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }



    private String response() {
        return "{\n" +
                "  \"Name\" : \"Johe Doe\",\n" +
                "  \"Id\" : \"123\"\n" +
                "}";
    }
}
