package com.example.ECF4.dao;

import com.example.ECF4.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Candidate findByCandidatename(String candidatename);
}
