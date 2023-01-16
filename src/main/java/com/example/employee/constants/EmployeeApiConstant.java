package com.example.employee.constants;

import static com.example.employee.constants.ApiConstants.PRIVATE_API_ENDPOINT;

public interface EmployeeApiConstant {
    String EMPLOYEE_UPDATE = PRIVATE_API_ENDPOINT + "/update";
    String EMPLOYEE_SAVE = PRIVATE_API_ENDPOINT + "/save";
    String EMPLOYEE_FIND_ALL_EMP = PRIVATE_API_ENDPOINT + "/find-all-employee";
    String EMPLOYEE_GET_BY_ID = PRIVATE_API_ENDPOINT + "/employee-by-id";
    String EMPLOYEE_DELETE_BY_ID = PRIVATE_API_ENDPOINT + "/employee-delete-by-id";
}
