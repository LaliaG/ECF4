package com.example.ECF4;

import com.example.ECF4.entities.Candidate;
import com.example.ECF4.service.AuthServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AuthStepDef {


    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private AuthServiceImpl authService;

    @Given("a user with username {string} and password {string} exists")
    public void aCandidateWithCandidatenameAndPasswordExists(String candidatename, String password) {
        Candidate candidate = new Candidate();
        candidate.setCandidatename(candidatename);
        candidate.setPassword(password);
        authService.register((Candidate) candidate);
    }


    @When("the user attempts to login with username {string} and password {string}")
    public void theCandidateAttemptsToLoginWithUsernameAndPassword(String candidatename, String password) throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(post("/login")
                .param("candidatename", candidatename)
                .param("password", password));

        
    }

    @Then("the user should be redirected to the homepage")
    public void theCandidateShouldBeRedirectedToTheHomepage() throws Exception {
        mockMvc.perform(post("/login"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        
    }

    @Then("the user should be redirected to the login page")
    public void theCandidateShouldBeRedirectedToTheLoginPage()  throws Exception{
        mockMvc.perform(post("/login"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

    }

}
