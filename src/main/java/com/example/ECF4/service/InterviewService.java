package com.example.ECF4.service;

import com.example.ECF4.Enum.InterviewFeedback;
import com.example.ECF4.dao.InterviewRepository;
import com.example.ECF4.dto.InterviewDTO;
import com.example.ECF4.entities.Interview;
import com.example.ECF4.entities.JobOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public  class InterviewService implements IService<Interview> {

    private final InterviewRepository interviewRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public InterviewService(InterviewRepository interviewRepository,
                            @Qualifier("restTemplate") RestTemplate restTemplate) {
        this.interviewRepository = interviewRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Interview> getAll() {
        return interviewRepository.findAll();
    }

    @Override
    public Interview getById(Long id) {
        return interviewRepository.findById(id).orElse(null);
    }

    @Override
    public void save(JobOffer element) {

    }

    @Override
    public Interview add(Interview interview) {
        return interviewRepository.save(interview);
    }

    @Override
    public void delete(Long id) {
        interviewRepository.deleteById(id);
    }

    @Override
    public void saveInterview(InterviewDTO interviewDTO) {

    }

    @Override
    public void deleteInterview(Long id) {

    }

    @Override
    public Interview update(Interview interview) {
        return interviewRepository.save(interview);
    }

    public List<InterviewDTO> getAllInterviews() {
        List<Interview> interviews = interviewRepository.findAll();
        return interviews.stream()
                .map(InterviewDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public InterviewDTO getInterviewById(Long id) {
        Interview interview = interviewRepository.findById(id).orElse(null);
        if (interview != null) {
            return InterviewDTO.fromEntity(interview);
        }
        return null;
    }

    public void acceptInterview(Long id) {
        Interview interview = interviewRepository.findById(id).orElse(null);
        if (interview != null) {
            interview.setFeedback(InterviewFeedback.ACCEPTED);
            interviewRepository.save(interview);

            // Exemple d'utilisation de RestTemplate pour effectuer une action après l'acceptation
            String url = "http://example.com/api/interview/accept";
            HttpEntity<InterviewDTO> requestEntity = new HttpEntity<>(InterviewDTO.fromEntity(interview));
            ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class);
            // Vérifier la réponse si nécessaire
        }
    }

    public void rejectInterview(Long id) {
        Interview interview = interviewRepository.findById(id).orElse(null);
        if (interview != null) {
            interview.setFeedback(InterviewFeedback.REJECTED);
            interviewRepository.save(interview);

            // Exemple d'utilisation de RestTemplate pour effectuer une action après le rejet
            String url = "http://example.com/api/interview/reject";
            HttpEntity<InterviewDTO> requestEntity = new HttpEntity<>(InterviewDTO.fromEntity(interview));
            ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Void.class);
            // Vérifier la réponse si nécessaire
        }
    }
}