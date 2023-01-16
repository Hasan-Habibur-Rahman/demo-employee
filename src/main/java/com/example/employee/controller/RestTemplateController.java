package com.example.employee.controller;

import com.example.employee.dto.EmployeeDtoRequest;
import com.example.employee.dto.ResponseDto;
import com.example.employee.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTemplateController {

    private RestTemplateService restTemplateService=new RestTemplateService();

    @RequestMapping(value = "/template/products")
    public ResponseDto getProductList(@RequestParam Long id) {
        return restTemplateService.getById(id);
    }

    @RequestMapping(value = "/save/employee")
    public ResponseDto saveEmployee(@RequestBody EmployeeDtoRequest empDto) {
        return restTemplateService.saveEmployee(empDto);
    }

    @RequestMapping(value = "/update/employee")
    public ResponseDto updateEmployee(@RequestBody EmployeeDtoRequest employeeDtoRequest) {
        return restTemplateService.updateEmployee(employeeDtoRequest);
    }

    @RequestMapping(value = "/employee/list")
    public ResponseDto getEmployeeList(@RequestParam(name = "page", defaultValue = "0") int page,
                                       @RequestParam(name = "limit", defaultValue = "10") int size,
                                       @Nullable @RequestParam(value = "searchText", required = false) String searchText) {
        return restTemplateService.getEmployeeList(page, size, searchText);
    }

    @RequestMapping(value = "/employee/delete")
    public ResponseDto deleteById(@RequestParam Long id) {
        return restTemplateService.deleteById(id);
    }
}
//    public TapStatusResponse validateIPN(String transactionId) {
//        TapStatusResponse response = new TapStatusResponse();
//        TapTokenResponse tokenResponse = getToken();
//        String bearerToken = "";
//        if (!tokenResponse.getAccess_token().equals("")) {
//            bearerToken = tokenResponse.getAccess_token();
//        }
//        try {
//            RestTemplate  restTemplate = new RestTemplate();
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-Type", "application/json");
//            headers.add("Authorization", "Bearer "+bearerToken);
//            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(validateUrl)
//                    .queryParam("transactionId", transactionId);
//
//            HttpEntity<TapStatusResponse> requestEntity = new HttpEntity<>(headers);
//
//            ResponseEntity<TapStatusResponse> responseEntity = restTemplate.exchange(
//                    uriBuilder.toUriString(),
//                    HttpMethod.GET,
//                    requestEntity,
//                    TapStatusResponse.class
//            );
//            response = responseEntity.getBody();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return response;
//    }