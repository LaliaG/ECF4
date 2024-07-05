package com.example.ECF4.service;

import com.example.ECF4.dao.JobOfferRepository;
import com.example.ECF4.entities.JobOffer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobOfferService implements IService<JobOffer> {
    private final JobOfferRepository jobOfferRepository;

    public JobOfferService(JobOfferRepository jobOfferRepository) {
        this.jobOfferRepository = jobOfferRepository;
    }

    @Override
    public List<JobOffer> getAll() {
        return jobOfferRepository.findAll();
    }

    @Override
    public JobOffer getById(Long id) {
        return jobOfferRepository.findById(id).orElse(null);
    }

    @Override
    public JobOffer add(JobOffer element) {
        return jobOfferRepository.save(element);
    }

    @Override
    public void delete(Long id) {
        jobOfferRepository.deleteById(id);
    }

    @Override
    public JobOffer update(JobOffer element) {
        return jobOfferRepository.save(element);
    }
}
