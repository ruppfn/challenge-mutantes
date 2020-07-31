package ar.com.frupp.challengemutantes.api.controller;

import ar.com.frupp.challengemutantes.api.model.RequestModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MutantController.class)
public class MutantControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private final String MUTANT_URI = "/mutant";
    private final String[] mutantDna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
    private final String[] nonMutantDna = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};
    private final String[] badRequestDna = {"Y", "A"};

    @Test
    @DisplayName("Mutant DNA -> OK")
    public void shouldBeOk() throws Exception {

        this.mockMvc.perform(
                post(MUTANT_URI)
                        .content(toJsonBody(mutantDna))
                        .contentType("Application/json"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Non Mutant DNA -> Forbidden")
    public void shouldBeForbidden() throws Exception {
        this.mockMvc.perform(
                post(MUTANT_URI)
                        .content(toJsonBody(nonMutantDna))
                        .contentType("Application/json"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Invalid Nitrogenous Base -> Bad Request")
    public void nonValidNitrogenousShouldBeBadRequest() throws Exception {
        this.mockMvc.perform(
                post(MUTANT_URI)
                        .content(toJsonBody(badRequestDna))
                        .contentType("Application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Empty body -> Bad Request")
    public void emptyBodyShouldBeBadRequest() throws Exception{
        this.mockMvc.perform(
                post(MUTANT_URI)
                        .contentType("Application/json"))
                .andExpect(status().isBadRequest());
    }

    private String toJsonBody(String[] dna) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        RequestModel model = new RequestModel(dna);
        String jsonBody = mapper.writeValueAsString(model);

        return jsonBody;
    }

}
