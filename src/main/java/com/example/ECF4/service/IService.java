package com.example.ECF4.service;

import com.example.ECF4.dto.InterviewDTO;
import com.example.ECF4.entities.Interview;
import com.example.ECF4.entities.JobOffer;

import java.util.List;

public interface IService<T> {
    public List<T> getAll();
    public T getById(Long id);
    //public T add(T element);

    void save(JobOffer element);

    Interview add(Interview interview);

    public void delete(Long id);

    void saveInterview(InterviewDTO interviewDTO);

    void deleteInterview(Long id);

    Interview update(Interview interview);


    // public T update(T element);
}
