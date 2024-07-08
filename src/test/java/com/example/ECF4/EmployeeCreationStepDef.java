package com.example.ECF4;

import com.example.ECF4.controller.CandidateRestController;
import com.example.ECF4.dao.CandidateRepository;
import com.example.ECF4.dto.EmployeeDTO;
import com.example.ECF4.entities.Candidate;
import com.example.ECF4.entities.JobOffer;
import com.example.ECF4.service.EmployeeCreationService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class EmployeeCreationStepDef {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private EmployeeCreationService employeeCreationService;

    @InjectMocks
    private CandidateRestController candidateRestController;

    private Candidate candidate;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Given("there is an application with candidate name {string} and email {string}")
    public void thereIsAnApplicationWithCandidateNameAndEmail(String name, String email) {
        candidate = new Candidate();
        candidate.setId(1L);
        candidate.setCandidatename(name);
        candidate.setEmail(email);
        JobOffer jobOffer = new JobOffer();
        jobOffer.setId(1L);
        jobOffer.setTitle("Developer");
        candidate.setJobOffer(jobOffer);

        Mockito.when(candidateRepository.findById(1L)).thenReturn(Optional.of(candidate));
    }

    @When("the application is validated")
    public void theApplicationIsValidated() throws Exception {
        mockMvc.perform(post("/candidates/validate/1"))
                .andExpect(status().isOk());
    }

    @Then("a new employee should be created with first name {string}, last name {string} and email {string}")
    public void aNewEmployeeShouldBeCreatedWithFirstnameLastnameAndEmailJohnDoeExampleCom (String firstName, String lastName, String email) {
        ArgumentCaptor<EmployeeDTO> employeeCaptor = ArgumentCaptor.forClass(EmployeeDTO.class);
        Mockito.verify(employeeCreationService).sendEmployeeData(employeeCaptor.capture());

        EmployeeDTO employeeDTO = employeeCaptor.getValue();
        assertEquals(firstName, employeeDTO.getFirstname());
        assertEquals(lastName, employeeDTO.getLastname());
        assertEquals(email, employeeDTO.getEmail());
    }


}
