package com.example.ECF4.dto;

import com.example.ECF4.entities.JobOffer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobOfferDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private boolean active;

    public static JobOfferDTO fromEntity(JobOffer jobOffer) {
        JobOfferDTO dto = new JobOfferDTO();
        dto.setId(jobOffer.getId());
        dto.setTitle(jobOffer.getTitle());
        dto.setDescription(jobOffer.getDescription());
        dto.setCreatedAt(jobOffer.getCreatedAt());
        dto.setActive(jobOffer.isActive()); // Assumed property for active status
        return dto;
    }

}
