package com.example.ECF4;

import com.example.ECF4.dao.CandidateRepository;
import com.example.ECF4.entities.Candidate;
import com.example.ECF4.service.AuthServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AuthServiceImplTest {
    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private HttpSession httpSession;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLogin_Success() {
        Candidate candidate = new Candidate();
        candidate.setCandidatename("testCandidate");
        candidate.setPassword("testPass");

        when(candidateRepository.findByCandidatename("testCandidate")).thenReturn(candidate);

        boolean result = authService.login("testCandidate", "testPass");

        assertTrue(result);
        verify(httpSession).setAttribute("candidate", candidate);
        verify(httpSession).setAttribute("login", "OK");
    }

    @Test
    public void testLogin_Failure() {
        Candidate candidate = new Candidate();
        candidate.setCandidatename("testCandiddate");
        candidate.setPassword("wrongPass");

        when(candidateRepository.findByCandidatename("testCandidate")).thenReturn(candidate);

        boolean result = authService.login("testCandidate", "testPass");

        assertFalse(result);
        verify(httpSession, never()).setAttribute("candidate", candidate);
        verify(httpSession, never()).setAttribute("login", "OK");
    }

    @Test
    public void testIsLogged_True() {
        when(httpSession.getAttribute("login")).thenReturn("OK");

        boolean result = authService.isLogged();

        assertTrue(result);
    }

    @Test
    public void testIsLogged_False() {
        when(httpSession.getAttribute("login")).thenReturn(null);

        boolean result = authService.isLogged();

        assertFalse(result);
    }

    @Test
    public void testRegister() {
        Candidate candidate = new Candidate();
        when(candidateRepository.save(candidate)).thenReturn(candidate);

        Candidate result = authService.register(candidate);

        assertEquals(candidate, result);
    }

    @Test
    public void testDeconnexion() {
        authService.deconnexion();

        verify(httpSession).removeAttribute("user");
        verify(httpSession).removeAttribute("login");
    }
}
