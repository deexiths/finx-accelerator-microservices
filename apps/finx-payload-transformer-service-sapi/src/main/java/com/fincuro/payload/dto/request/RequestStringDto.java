package com.fincuro.payload.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestStringDto {
    @NotNull(message ="inputJson should not be blank")
    private String inputJson;

    @NotNull(message ="mapper should not be blank")
    private String mapper;

}
