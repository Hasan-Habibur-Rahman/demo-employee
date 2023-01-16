package com.example.employee.dto;

import com.example.employee.entity.ResponseMessage;
import com.example.employee.entity.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto<T> implements Serializable {

    private ResponseStatus status;

    private ResponseMessage message;

    @JsonProperty("data")
    private T payload;

}