package com.example.examplerelationmapping.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AcceptanceTestSubject {

    @Test
    void contextLoad() {
    }
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("http://localhost:8080/getAllSubjects -> 200")
    public void should_return_all_Subject() throws Exception{
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/getAllSubjects"))
                .andDo(print())
                .andExpect(status().isOk());
        Teacher teacher = new Teacher(1l, "Maciej", new ArrayList<>());
        Subject subject = new Subject(1l, "Informatyka", teacher);
        String jsonAsString = resultActions.andReturn().getResponse().getContentAsString();
        List<Subject> subject1 = objectMapper.readValue(jsonAsString, new TypeReference<List<Subject>>() {});
        assertEquals(subject.getId(), subject1.get(0).getId());
        assertEquals(subject.getName(), subject1.get(0).getName());



    }



}
