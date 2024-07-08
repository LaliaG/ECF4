package com.example.ECF4;

import com.example.ECF4.dao.CandidateRepository;
import com.example.ECF4.dto.EmployeeDTO;
import com.example.ECF4.entities.Candidate;
import com.example.ECF4.entities.JobOffer;
import com.example.ECF4.service.EmployeeCreationService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Optional;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CandidateRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CandidateRepository candidateRepository;

    @MockBean
    private EmployeeCreationService employeeCreationService;

    @Test
    public void testValidateCandidate() throws Exception {
        Candidate candidate = new Candidate();
        candidate.setId(1L);
        candidate.setCandidatename("John Doe");
        candidate.setEmail("john.doe@example.com");
        JobOffer jobOffer = new JobOffer();
        jobOffer.setId(1L);
        jobOffer.setTitle("Developer");
        candidate.setJobOffer(jobOffer);

        Mockito.when(candidateRepository.findById(1L)).thenReturn(Optional.of(candidate));

        mockMvc.perform((RequestBuilder) post("/candidates/validate/1"))
                .andExpect(status().isOk());

        ArgumentCaptor<EmployeeDTO> employeeCaptor = ArgumentCaptor.forClass(EmployeeDTO.class);
        Mockito.verify(employeeCreationService).sendEmployeeData(employeeCaptor.capture());

        EmployeeDTO employeeDTO = employeeCaptor.getValue();
        Assert.assertEquals("John", employeeDTO.getFirstname());
        Assert.assertEquals("Doe", employeeDTO.getLastname());
        Assert.assertEquals("john.doe@example.com", employeeDTO.getEmail());
        Assert.assertEquals(Long.valueOf(1L), employeeDTO.getDepartmentId());
        Assert.assertEquals(Long.valueOf(1L), employeeDTO.getJobPositionId());
    }
}
