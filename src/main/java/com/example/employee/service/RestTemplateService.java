package com.example.employee.service;

import com.example.employee.constants.ApiConstants;
import com.example.employee.constants.EmployeeApiConstant;
import com.example.employee.dto.EmployeeDto;
import com.example.employee.dto.EmployeeDtoRequest;
import com.example.employee.dto.ResponseDto;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {


    RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer ");
        return headers;
    }
    public ResponseDto getById(Long id) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(ApiConstants.BASE_URI)
                .path(EmployeeApiConstant.EMPLOYEE_GET_BY_ID)
                .queryParam("id", id);
        URI uri = builder.build().toUri();
        HttpEntity<EmployeeDto> requestEntity = new HttpEntity<EmployeeDto>(getHttpHeaders());
        ResponseEntity<ResponseDto> response = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, ResponseDto.class);
        return response.getBody();
    }

    public ResponseDto saveEmployee(EmployeeDtoRequest empDto) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(ApiConstants.BASE_URI)
                .path(EmployeeApiConstant.EMPLOYEE_SAVE);
        URI uri = builder.build().toUri();
        HttpEntity<EmployeeDtoRequest> requestEntity = new HttpEntity<EmployeeDtoRequest>(empDto, getHttpHeaders());
        ResponseEntity<ResponseDto> response = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, ResponseDto.class);
        return response.getBody();
    }

    public ResponseDto updateEmployee(EmployeeDtoRequest empDto) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(ApiConstants.BASE_URI)
                .path(EmployeeApiConstant.EMPLOYEE_UPDATE);
//                .queryParam("id", id);
        URI uri = builder.build().toUri();

        HttpEntity<EmployeeDtoRequest> requestEntity = new HttpEntity<EmployeeDtoRequest>(empDto, getHttpHeaders());
        ResponseEntity<ResponseDto> response = restTemplate.exchange(uri, HttpMethod.PUT, requestEntity, ResponseDto.class);
        return response.getBody();
    }

    public ResponseDto getEmployeeList(int page, int size, String searchText) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(ApiConstants.BASE_URI)
                .path(EmployeeApiConstant.EMPLOYEE_FIND_ALL_EMP)
                .queryParam("page", page)
                .queryParam("size", size);
        URI uri = builder.build().toUri();

        HttpEntity<EmployeeDto> requestEntity = new HttpEntity<EmployeeDto>(getHttpHeaders());

        ResponseEntity<ResponseDto> response = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, ResponseDto.class);
        return response.getBody();
    }

    public ResponseDto deleteById(Long id) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(ApiConstants.BASE_URI)
                .path(EmployeeApiConstant.EMPLOYEE_DELETE_BY_ID)
                .queryParam("id", id);
        URI uri = builder.build().toUri();
        HttpEntity<EmployeeDto> requestEntity = new HttpEntity<EmployeeDto>(getHttpHeaders());
        ResponseEntity<ResponseDto> response = restTemplate.exchange(uri, HttpMethod.DELETE, requestEntity, ResponseDto.class);
        return response.getBody();
    }

}
