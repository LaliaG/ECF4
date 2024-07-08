package com.example.ECF4.controller;

import com.example.ECF4.dao.CandidateRepository;
import com.example.ECF4.dto.EmployeeDTO;
import com.example.ECF4.entities.Candidate;
import com.example.ECF4.exception.ResourceNotFoundException;
import com.example.ECF4.service.EmployeeCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/candidates")
public class CandidateRestController {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private EmployeeCreationService employeeCreationService;

    @PostMapping("/validate/{id}")
    public void validateApplication(@PathVariable Long id) {
        Candidate candidate = candidateRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstname(candidate.getCandidatename());
        employeeDTO.setLastname(candidate.getCandidatename());
        employeeDTO.setEmail(candidate.getEmail());
        employeeDTO.setDepartmentId(1L);
        employeeDTO.setJobPositionId(1L);

        employeeCreationService.sendEmployeeData(employeeDTO);
    }
}
