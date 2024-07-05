package com.example.ECF4.service;

import com.example.ECF4.dao.CandidateRepository;
import com.example.ECF4.entities.Candidate;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService{
    private final CandidateRepository candidateRepository;
    @Autowired
    private HttpSession _httpSession;

    public AuthServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public boolean login(String candidatename, String password) {
        Candidate candidate = candidateRepository.findByCandidatename(candidatename);
        if(candidate.getPassword().equals(password)) {
            _httpSession.setAttribute("candidate", candidate);
            _httpSession.setAttribute("login", "OK");
            return true;
        }
        return false;
    }

    @Override
    public boolean isLogged() {
        try {
            String attrIsLogged = _httpSession.getAttribute("login").toString();
            return attrIsLogged.equals("OK");
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Candidate register(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Override
    public void deconnexion() {
        _httpSession.removeAttribute("user");
        _httpSession.removeAttribute("login");
    }
}
