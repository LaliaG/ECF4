package com.example.ECF4.service;

import com.example.ECF4.dao.JobOfferRepository;
import com.example.ECF4.entities.JobOffer;
import com.example.ECF4.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobOffers")
public class JobOfferRestService {
    @Autowired
    private JobOfferRepository jobOfferRepository;

    @GetMapping("/{id}")
    public JobOffer getJobOffer(@PathVariable Long id) {
        return jobOfferRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("JobOffer not found"));
    }

    @PostMapping
    public JobOffer addJobOffer(@RequestBody JobOffer jobOffer) {
        return jobOfferRepository.save(jobOffer);
    }

    @PutMapping("/{id}")
    public JobOffer updateJobOffer(@PathVariable Long id, @RequestBody JobOffer updatedJobOffer) {
        JobOffer jobOffer = jobOfferRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("JobOffer not found"));
        jobOffer.setTitle(updatedJobOffer.getTitle());
        jobOffer.setDescription(updatedJobOffer.getDescription());
        return jobOfferRepository.save(jobOffer);
    }

    @DeleteMapping("/{id}")
    public void deleteJobOffer(@PathVariable Long id) {
        jobOfferRepository.deleteById(id);
    }

    @GetMapping
    public List<JobOffer> getAllJobOffers() {
        return jobOfferRepository.findAll();
    }
}
