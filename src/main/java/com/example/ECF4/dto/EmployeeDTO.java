package com.example.ECF4.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    private String firstname;
    private String lastname;
    private String email;
    private Long departmentId;
    private Long jobPositionId;
}
