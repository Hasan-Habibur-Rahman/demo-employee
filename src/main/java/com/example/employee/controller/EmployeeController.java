package com.example.employee.controller;

import com.example.employee.dto.EmployeeDtoRequest;
import com.example.employee.dto.ResponseDto;
import com.example.employee.exception.CommonException;
import com.example.employee.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping(value = "/employee/v1/api")
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping(value = "/save")
    public  ResponseEntity<ResponseDto> addEmployee(@RequestBody EmployeeDtoRequest empDto)throws CommonException  {
        log.info(String.format("Request received for %s  ", "/save"),empDto);
        ResponseEntity<ResponseDto> response=employeeService.save(empDto);
        log.info(String.format("Request received for %s  ", "/save"),response);
        return response;
    }

    @GetMapping(value = "/find-all-employee")
    public  ResponseEntity<ResponseDto> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "limit", defaultValue = "10") int size,
                                                @Nullable @RequestParam(value = "searchText", required = false) String searchText) throws CommonException  {
        log.info(String.format("Request received for %s  ", "/find-all-employee"));
        ResponseEntity<ResponseDto> response=employeeService.findAll(PageRequest.of(page, size), searchText);
        log.info(String.format("Request received for %s  ", "/find-all-employee"),response);
        return response;
    }

    @GetMapping(value = "/employee-by-id")
    public  ResponseEntity<ResponseDto> findById(@RequestParam Long id) throws CommonException {
        log.info(String.format("Request received for %s  ", "/employee-by-id"));
        ResponseEntity<ResponseDto> response=employeeService.findById(id);
        log.info(String.format("Request received for %s  ", "/employee-by-id"));
        return response;
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ResponseDto> update(@RequestBody EmployeeDtoRequest empDto ) throws CommonException {
        log.info(String.format("Request received for %s  ", "/update"));
        ResponseEntity<ResponseDto> response=employeeService.update(empDto);
        log.info(String.format("Request received for %s  ", "/update"),response);
        return response;
    }

    @DeleteMapping(value = "/delete-by-id")
    public  ResponseEntity<ResponseDto> delete(@Param(value = "id") Long id)throws CommonException {
        log.info(String.format("Request received for %s  ", "/delete-by-id"));
        ResponseEntity<ResponseDto> response = employeeService.deleteById(id);
        log.info(String.format("Request received for %s  ", "/delete-by-id"),response);
        return response;
    }
}
