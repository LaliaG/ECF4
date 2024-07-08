package com.example.ECF4;

import com.example.ECF4.controller.RecruitmentRestController;
import com.example.ECF4.dto.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;

public class RecruitmentRestControllerTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private RecruitmentRestController recruitmentRestController;

    public RecruitmentRestControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHireEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        // Set employee details

        when(restTemplate.postForEntity("http://localhost:8081/api/employees", employeeDTO, Void.class))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));

        ResponseEntity<Void> response = recruitmentRestController.hireEmployee(employeeDTO);
        assert(response.getStatusCode() == HttpStatus.OK);
    }
}
