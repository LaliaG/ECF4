package com.example.ECF4.service;

import com.example.ECF4.Enum.JobOfferStatus;
import com.example.ECF4.dao.JobOfferRepository;
import com.example.ECF4.dto.InterviewDTO;
import com.example.ECF4.dto.JobOfferDTO;
import com.example.ECF4.entities.Interview;
import com.example.ECF4.entities.JobOffer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobOfferService implements IService<JobOffer> {
    private final JobOfferRepository jobOfferRepository;

    public JobOfferService(JobOfferRepository jobOfferRepository) {
        this.jobOfferRepository = jobOfferRepository;
    }

    public List<JobOfferDTO> getAllJobOffers() {
        List<JobOffer> jobOffers = jobOfferRepository.findAll();
        return jobOffers.stream()
                .map(JobOfferDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<JobOffer> getAll() {
        return jobOfferRepository.findAll();
    }

    public List<JobOffer> getActive() {
        return jobOfferRepository.findAll()
                .stream()
                .filter(jobOffer -> jobOffer.getStatus() == JobOfferStatus.PUBLISHED)
                .toList();
    }


    @Override
    public JobOffer getById(Long id) {
        return jobOfferRepository.findById(id).orElse(null);
    }


    @Override
    public void save(JobOffer element) {
        jobOfferRepository.save(element);
    }

    @Override
    public Interview add(Interview interview) {
        return null;
    }

    public void publishJobOffer(Long id) {
        JobOffer jobOffer = getById(id);
        if (jobOffer != null) {
            jobOffer.setStatus(JobOfferStatus.PUBLISHED);
            jobOfferRepository.save(jobOffer);
        }
    }

    public void deleteJobOffer(Long id) {
        JobOffer jobOffer = getById(id);
        if (jobOffer != null) {
            jobOffer.setStatus(JobOfferStatus.DELETED);
            jobOfferRepository.save(jobOffer);
        }
    }

    @Override
    public void delete(Long id) {
        jobOfferRepository.deleteById(id);
    }

    @Override
    public void saveInterview(InterviewDTO interviewDTO) {

    }

    @Override
    public void deleteInterview(Long id) {

    }

    @Override
    public Interview update(Interview interview) {
        return null;
    }


}
