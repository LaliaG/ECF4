package com.example.ECF4.controller;

import com.example.ECF4.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/recruitment")
public class RecruitmentRestController {
    @Autowired
    private RestTemplate restTemplate;

    private static final String EMPLOYEE_SERVICE_URL = "http://localhost:8081/api/employees";

    @PostMapping("/hire")
    public ResponseEntity<Void> hireEmployee(@RequestBody EmployeeDTO employeeDTO) {
        ResponseEntity<Void> response = restTemplate.postForEntity(EMPLOYEE_SERVICE_URL, employeeDTO, Void.class);
        return new ResponseEntity<>(response.getStatusCode());
    }
}
