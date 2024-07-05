package com.example.ECF4.controller;

import com.example.ECF4.entities.Candidate;
import com.example.ECF4.entities.Interview;
import com.example.ECF4.entities.JobOffer;
import com.example.ECF4.service.AuthServiceImpl;
import com.example.ECF4.service.JobOfferService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class JobOfferController {
    private final JobOfferService jobOfferService;
    private final AuthServiceImpl authService;

    public JobOfferController(JobOfferService jobOfferService, AuthServiceImpl authService) {
        this.jobOfferService = jobOfferService;
        this.authService = authService;
    }

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        if (authService.isLogged()) {
            model.addAttribute("login", authService.isLogged());
            model.addAttribute("posts", jobOfferService.getAll());
            model.addAttribute("Candidate", (Candidate) session.getAttribute("candidate"));
            model.addAttribute("interview", new Interview());
            return "home";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/jobOfferForm")
    public String jobOfferForm(Model model) {
        if (authService.isLogged()) {
            model.addAttribute("jobOffer", new JobOffer());
            model.addAttribute("login", authService.isLogged());
            return "jobOffer-form";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/post/add")
    public String addJobOffer(@ModelAttribute("jobOffer") JobOffer jobOffer, HttpSession session) {
        if (authService.isLogged()) {
            jobOffer.setCandidate((Candidate) session.getAttribute("candidate"));
            jobOffer.setCreatedAt(LocalDateTime.now());
            JobOfferService.add(jobOffer);
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/post/edit/{id}")
    public String editPost(@PathVariable Long id, Model model) {
        if (authService.isLogged()) {
            model.addAttribute("post", postService.getById(id));
            return "post-form";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/post/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        if (authService.isLogged()) {
            postService.delete(id);
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }
}
