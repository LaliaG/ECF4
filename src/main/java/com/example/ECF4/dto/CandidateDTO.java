package com.example.ECF4.dto;

import com.example.ECF4.entities.Candidate;
import lombok.Data;

@Data
public class CandidateDTO {
    private Long id;
    private String candidatename;
    private String email;
    private String resume;
    private String password;
    private boolean active; // Assumed property for active status

    public static CandidateDTO fromEntity(Candidate candidate) {
        CandidateDTO dto = new CandidateDTO();
        dto.setId(candidate.getId());
        dto.setCandidatename(candidate.getCandidatename());
        dto.setEmail(candidate.getEmail());
        dto.setResume(candidate.getResume());
        dto.setPassword(candidate.getPassword());
        dto.setActive(candidate.isActive()); // Assumed property for active status
        return dto;
    }

}
