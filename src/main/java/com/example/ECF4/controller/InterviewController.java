package com.example.ECF4.controller;


import com.example.ECF4.Enum.InterviewFeedback;
import com.example.ECF4.dto.InterviewDTO;
import com.example.ECF4.service.AuthServiceImpl;
import com.example.ECF4.service.InterviewService;
import com.example.ECF4.service.JobOfferService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/interview")
public class InterviewController {

    private final InterviewService interviewService;
    private final JobOfferService jobOfferService;
    private final AuthServiceImpl authService;

    public InterviewController(InterviewService interviewService, JobOfferService jobOfferService,
                               AuthServiceImpl authService) {
        this.interviewService = interviewService;
        this.jobOfferService = jobOfferService;
        this.authService = authService;
    }

    @GetMapping("/all")
    public String getAllInterviews(Model model, HttpSession session) {
        if (!authService.isLogged()) {
            return "redirect:/login";
        }
        model.addAttribute("interviews", interviewService.getAllInterviews());
        return "interviewList";
    }

    @GetMapping("/{id}")
    public String getInterviewById(@PathVariable Long id, Model model, HttpSession session) {
        if (!authService.isLogged()) {
            return "redirect:/login";
        }
        InterviewDTO interview = interviewService.getInterviewById(id);
        if (interview == null) {
            return "redirect:/interview/all";
        }
        model.addAttribute("interview", interview);
        return "interviewDetails";
    }

    @GetMapping("/add")
    public String showAddInterviewForm(Model model, HttpSession session) {
        if (!authService.isLogged()) {
            return "redirect:/login";
        }
        model.addAttribute("candidates", authService.getAll());
        model.addAttribute("jobOffers", jobOfferService.getActive());
        model.addAttribute("interview", new InterviewDTO());
        return "addInterview";
    }

    @PostMapping("/add")
    public String addInterview(@ModelAttribute InterviewDTO interviewDTO, HttpSession session) {
        if (!authService.isLogged()) {
            return "redirect:/login";
        }
        interviewDTO.setFeedback(InterviewFeedback.valueOf("accepted"));
        interviewService.saveInterview(interviewDTO);
        return "redirect:/interview/all";
    }

    @PostMapping("/delete/{id}")
    public String deleteInterview(@PathVariable Long id, HttpSession session) {
        if (!authService.isLogged()) {
            return "redirect:/login";
        }
        interviewService.deleteInterview(id);
        return "redirect:/interview/all";
    }

    @PostMapping("/accept/{id}")
    public String acceptInterview(@PathVariable Long id, HttpSession session) {
        if (!authService.isLogged()) {
            return "redirect:/login";
        }
        interviewService.acceptInterview(id);
        return "redirect:/interview/all";
    }

    @PostMapping("/reject/{id}")
    public String rejectInterview(@PathVariable Long id, HttpSession session) {
        if (!authService.isLogged()) {
            return "redirect:/login";
        }
        interviewService.rejectInterview(id);
        return "redirect:/interview/all";
    }

    @GetMapping("/edit/{id}")
    public String showEditInterviewForm(@PathVariable Long id, Model model, HttpSession session) {
        if (!authService.isLogged()) {
            return "redirect:/login";
        }
        InterviewDTO interviewDTO = interviewService.getInterviewById(id);
        if (interviewDTO == null) {
            return "redirect:/interview/all";
        }
        model.addAttribute("candidates", authService.getAll());
        model.addAttribute("jobOffers", jobOfferService.getActive());
        model.addAttribute("interview", interviewDTO);
        return "editInterview";
    }

    @PostMapping("/edit")
    public String editInterview(@ModelAttribute InterviewDTO interviewDTO, HttpSession session) {
        if (!authService.isLogged()) {
            return "redirect:/login";
        }
        interviewService.saveInterview(interviewDTO);
        return "redirect:/interview/all";
    }
}
