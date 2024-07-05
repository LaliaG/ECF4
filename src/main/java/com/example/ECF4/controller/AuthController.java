package com.example.ECF4.controller;

import com.example.ECF4.entities.Candidate;
import com.example.ECF4.service.AuthServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @GetMapping("/registration")
    public String inscription(Model model) {
        model.addAttribute("candidate", new Candidate());
        return "registration-form";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("candidate") Candidate candidate) {
        authService.register(candidate);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("candidate", new Candidate());
        model.addAttribute("login", authService.isLogged());
        return "connexion-form";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("candidate") Candidate candidate) {
        boolean connected = authService.login(candidate.getCandidatename(), candidate.getPassword());
        if (connected) {
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/deconnexion")
    public String deconnexion(Model model) {
        authService.deconnexion();
        return "redirect:/login";
    }
}
