package com.fincuro.payload.service.transformer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class MessageTransformerTest {
    @InjectMocks
    MessageTransformer messageTransformer;

    @Test
    void testTransform() {
        String transform = messageTransformer.transform(jsonInput, joltSpec);
        assertThat(transform).isEqualTo(outPut);
    }

    @Test
    void testTransformWhenNull() {
        String transform = messageTransformer.transform(jsonInputNull, joltSpec);
        assertThat(transform).isNull();
    }

    String jsonInput = "{\n" + "  \"firstName\": \"vinaya\"\n" + "}";

    String joltSpec = "[\n" + "  {\n" + "    \"operation\": \"shift\",\n" + "    \"spec\": {\n" + "      \"firstName\": \"name\"\n" + "    }\n" + "  }\n" + "]";
    String outPut = "{\"name\":\"vinaya\"}";

    String jsonInputNull = "{}";
}
