package com.example.ECF4.entities;

import com.example.ECF4.Enum.InterviewFeedback;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private InterviewFeedback feedback;

    @NotNull
    @Pattern(regexp = "^[A-Za-z\\d\\s].{0,49}$", message = "Invalid field")
    private String nameRecruiter;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "job_offer_id")
    private JobOffer jobOffer;


}
