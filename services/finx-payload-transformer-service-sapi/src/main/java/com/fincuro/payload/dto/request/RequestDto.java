package com.fincuro.payload.dto.request;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {
    @NotNull(message="inputJson should not be blank")
    private JsonNode inputJson;

    @NotNull(message="mapper should not be blank")
    private JsonNode mapper;
}