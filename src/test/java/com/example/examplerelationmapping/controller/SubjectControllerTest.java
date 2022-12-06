package com.example.examplerelationmapping.controller;

import com.example.examplerelationmapping.model.Subject;
import com.example.examplerelationmapping.model.Teacher;
import com.example.examplerelationmapping.service.ServiceSubjectImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class SubjectControllerTest {

    @Autowired
    private  MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean(ServiceSubjectImpl.class)
    private ServiceSubjectImpl serviceSubjectImpl;

    @Test
    @DisplayName("Unit test for subjectController http://localhost:8080/subject/subjectById/1 -> 200")
    public void should_return_subject_by_id_status200() throws Exception{
        Teacher teacher = new Teacher(1L, "Maciej", null);
        Mockito.when(serviceSubjectImpl.findById(1L)).thenReturn(Optional.of(new Subject(1L, "Informatyka", teacher)));

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/subject/subjectById/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("id", "1L"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        String jsonAsString = resultActions.andReturn().getResponse().getContentAsString();
        Subject response = objectMapper.readValue(jsonAsString, Subject.class);

        assertEquals(1L, response.getId());
        assertEquals("Informatyka", response.getName());
        assertEquals(1L, response.getTeacher().getId());
        assertEquals("Maciej", response.getTeacher().getName());
    }
}
