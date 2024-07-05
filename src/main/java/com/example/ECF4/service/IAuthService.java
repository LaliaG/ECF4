package com.example.ECF4.service;

import com.example.ECF4.entities.Candidate;

public interface IAuthService {
    boolean login(String candidatename, String password);
    boolean isLogged();
    Candidate register(Candidate candidate);
    void deconnexion();
}
