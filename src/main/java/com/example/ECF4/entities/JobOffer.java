package com.example.ECF4.entities;

import com.example.ECF4.Enum.JobOfferStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name= "job_offer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = "^[A-Za-z].{0,49}$", message = "Invalid field")
    private String title;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @Pattern(regexp = "^[A-Za-z\\d\\s].{0,49}$", message = "Invalid field")
    private String employeeHr;
    //private String departement;
    //private String position;
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private JobOfferStatus status = JobOfferStatus.PUBLISHED; // Default status is PUBLISHED

    public Boolean getActive() {
        return this.status == JobOfferStatus.PUBLISHED;
    }


   // @ManyToOne
   // @JoinColumn(name = "candidate_id")
   // private Candidate candidate;
   // OU private String candidateId;

    @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Interview> interviews;
}
