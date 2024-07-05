package com.example.ECF4.service;

import com.example.ECF4.dao.InterviewRepository;
import com.example.ECF4.entities.Interview;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewService implements IService<Interview>{
    private final InterviewRepository interviewRepository;

    public InterviewService(InterviewRepository interviewRepository) {
        this.interviewRepository = interviewRepository;
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
    public Interview add(Interview element) {
        return interviewRepository.save(element);
    }

    @Override
    public void delete(Long id) {
        interviewRepository.deleteById(id);
    }

    @Override
    public Interview update(Interview element) {
        return interviewRepository.save(element);
    }
}
