package com.example.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto extends BaseDto {

    private String code;

    private String name;

    private String fatherName;

	private LocalDate dob;

	private LocalDate doj;

    private String nid;

    private String gender;

    //@Email
    private String email;

    private String mobileNo;
//
//    private boolean active;
//    private boolean deleted;




    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}