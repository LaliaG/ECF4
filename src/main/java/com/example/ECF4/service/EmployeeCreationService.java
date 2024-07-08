package com.example.ECF4.service;

import com.example.ECF4.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeCreationService {

    @Value("${employee.service.url}")
    private String employeeServiceUrl;


    private final RestTemplate restTemplate;

    public EmployeeCreationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendEmployeeData(EmployeeDTO employeeDTO) {
        restTemplate.postForObject(employeeServiceUrl + "/employees", employeeDTO, EmployeeDTO.class);
    }
}
