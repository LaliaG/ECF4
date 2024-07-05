package com.example.ECF4.dto;

import com.example.ECF4.Enum.InterviewFeedback;
import com.example.ECF4.entities.Interview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewDTO {

    private Long id;
    private LocalDateTime date;
    private InterviewFeedback feedback;
    private String nameRecruiter;
    private Long candidateId;
    private Long jobOfferId;

    public static InterviewDTO fromEntity(Interview interview) {
        return InterviewDTO.builder()
                .id(interview.getId())
                .date(interview.getDate())
                .feedback(interview.getFeedback())
                .nameRecruiter(interview.getNameRecruiter())
                .candidateId(interview.getCandidate().getId())
                .jobOfferId(interview.getJobOffer().getId())
                .build();
    }

    public Interview toEntity() {
        return Interview.builder()
                .id(id)
                .date(date)
                .feedback(feedback)
                .nameRecruiter(nameRecruiter)
                .build();
    }



}
