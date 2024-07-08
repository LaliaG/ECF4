package com.example.ECF4;

import com.example.ECF4.controller.AuthController;
import com.example.ECF4.entities.Candidate;
import com.example.ECF4.service.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AuthControllerTest {
    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthServiceImpl authService;

    @Mock
    private Model model;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    public void testRegistration() throws Exception {
        Candidate candidate = new Candidate();
        doNothing().when(authService).register(candidate);

        mockMvc.perform(post("/registration")
                        .flashAttr("candidate", candidate))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    public void testLogin() throws Exception {
        Candidate candidate = new Candidate();
        candidate.setCandidatename("testCandidate");
        candidate.setPassword("testPass");

        when(authService.login("testUser", "testPass")).thenReturn(true);

        mockMvc.perform(post("/login")
                        .flashAttr("candidate", candidate))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testLogin_Failure() throws Exception {
        Candidate candidate = new Candidate();
        candidate.setCandidatename("testCandidate");
        candidate.setPassword("testPass");

        when(authService.login("testCandidate", "testPass")).thenReturn(false);

        mockMvc.perform(post("/login")
                        .flashAttr("candidate", candidate))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }
}
