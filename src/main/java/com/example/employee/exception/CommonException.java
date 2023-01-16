package com.example.employee.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommonException extends Exception {

    public CommonException(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }

    HttpStatus code;
    String message;
    Integer errorId;


}