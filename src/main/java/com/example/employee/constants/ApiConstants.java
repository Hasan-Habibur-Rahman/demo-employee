package com.example.employee.constants;

import org.springframework.http.MediaType;

public interface ApiConstants {

    String EXTERNAL_MEDIA_TYPE = MediaType.APPLICATION_JSON_VALUE;

    String BASE_URI="http://localhost:9090/employee";
    String PRIVATE_API_ENDPOINT = "/employee/v1/api";

}
