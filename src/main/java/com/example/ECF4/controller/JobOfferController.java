package com.example.ECF4.controller;

import com.example.ECF4.entities.Candidate;
import com.example.ECF4.entities.Interview;
import com.example.ECF4.entities.JobOffer;
import com.example.ECF4.service.AuthServiceImpl;
import com.example.ECF4.service.JobOfferService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/jobOffers")
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
            model.addAttribute("jobOffers", jobOfferService.getAll());
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

    @PostMapping("/add")
    public String addJobOffer(@ModelAttribute("jobOffer") JobOffer jobOffer, HttpSession session) {
        if (!authService.isLogged()) {
            return "redirect:/login";
        }
        Candidate candidate = (Candidate) session.getAttribute("candidate");
        if (candidate != null) {
            jobOffer.setEmployeeHr(candidate.getCandidatename());
        } else {
            jobOffer.setEmployeeHr("Unknown");
        }
        jobOffer.setCreatedAt(LocalDateTime.now());
        jobOfferService.save(jobOffer);
        return "redirect:/jobOffer/";
    }
    @GetMapping("/post/edit/{id}")
    public String editJobOffer(@PathVariable Long id, Model model) {
        if (authService.isLogged()) {
            model.addAttribute("jobOffer", jobOfferService.getById(id));
            return "jobOffer-form";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/edit")
    public String updateJobOffer(@ModelAttribute("jobOffer") JobOffer jobOffer) {
        if (!authService.isLogged()) {
            return "redirect:/login";
        }
        jobOfferService.save(jobOffer);
        return "redirect:/jobOffer/";
    }

    @GetMapping("/post/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        if (authService.isLogged()) {
            jobOfferService.delete(id);
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Long id, Model model) {
        if (!authService.isLogged()) {
            return "redirect:/login";
        }
        model.addAttribute("jobOffer", jobOfferService.getById(id));
        model.addAttribute("login", true);
        return "jobOfferDetails";
    }

    @GetMapping("/jobOffers")
    public String getAllJobOffers(Model model) {
        List<JobOffer> jobOffers = jobOfferService.getAll();
        model.addAttribute("jobOffers", jobOffers);
        return "jobOffers";
    }
}
