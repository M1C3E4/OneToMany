package com.example.examplerelationmapping.controller;

import com.example.examplerelationmapping.model.School;
import com.example.examplerelationmapping.model.Subject;
import com.example.examplerelationmapping.model.Teacher;
import com.example.examplerelationmapping.service.ServiceSubjectImpl;
import com.fasterxml.jackson.core.type.TypeReference;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    @DisplayName("Unit test for subjectController http://localhost:8080/subject/subjectByName/{name} ->200" +
            " when subject about this name not exists returning null")
    public void should_return_subject_by_name() throws Exception {
        Subject subject = new Subject(1L, "Informatyka", null);
        Mockito.when(serviceSubjectImpl.findByName("Informatyka")).thenReturn(Optional.of(subject));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/subject/subjectByName/Informatyka")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", "Informatyka"))
                .andDo(print())
                .andExpect(status().isOk());

        String jsonAsString = resultActions.andReturn().getResponse().getContentAsString();
        Subject response = objectMapper.readValue(jsonAsString, Subject.class);

        assertEquals(1L, response.getId());
        assertEquals("Informatyka", response.getName());
        assertNull(null, response.getTeacher());
    }

    @Test
    @DisplayName("Unit test for subjectController http://localhost:8080/subject/getAllSubjects -> 200" +
            "when this subjects not exists returning status 404 Not Found")
    public void should_return_all_Subject() throws Exception {
        Subject subject = new Subject(1L, "Informatyka", null);
        List<Subject> subjectList = new ArrayList<>();
        subjectList.add(subject);
        Mockito.when(serviceSubjectImpl.findAll()).thenReturn(subjectList);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/subject/getAllSubjects"))
                .andDo(print())
                .andExpect(status().isOk());

        String jsonAsString = resultActions.andReturn().getResponse().getContentAsString();
        List<Subject> response = objectMapper.readValue(jsonAsString, new TypeReference<>() {});

        assertEquals(1L, response.get(0).getId());
        assertEquals("Informatyka", response.get(0).getName());
        assertNull(null, response.get(0).getTeacher());
    }

    @Test
    @DisplayName("Unit test for subjectController http://localhost:8080/subject/findSubjectWhereNameLikeString -> 200" +
            " when this string not exists returning empty list")
    public void find_subject_where_name_like_string() throws Exception {
        Subject subject = new Subject(1L, "Informatyka", null);
        List<Subject> subjectList = new ArrayList<>();
        subjectList.add(subject);
        Mockito.when(serviceSubjectImpl.findSubjectWhereLikeString()).thenReturn(subjectList);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/subject/findSubjectWhereNameLikeString")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        String jsonAsString = resultActions.andReturn().getResponse().getContentAsString();
        List<Subject> listSubject = objectMapper.readValue(jsonAsString, new TypeReference<>() {
        });
        assertEquals(subject.getId(), listSubject.get(0).getId());
        assertEquals(subject.getName(), listSubject.get(0).getName());
        assertEquals(subject.getTeacher(), listSubject.get(0).getTeacher());
    }

    @Test
    @DisplayName("Unit test for subjectController https://localhost:8080/subject/{subjectId}/teacher/{teacherId} -> 200" +
            "when this subject by id not exists return status 404 Not Found")
    void should_update_subject_about_the_present_existing_teacher() throws Exception {
        School school = new School(2L, "I LO im. Tadeusza Kościuszko w Łukowie");
        Teacher teacher = new Teacher(1L, "Maciej", new ArrayList<>(), school);
        Subject subject = new Subject(1L, "Informatyka", null);
        Mockito.when(serviceSubjectImpl.findById(1L)).thenReturn(Optional.of(subject));

        mockMvc.perform(MockMvcRequestBuilders.put("/subject/1/teacher/1")
                .content(asJsonString(new Subject(1L, "Informatyka", teacher)))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

                assertEquals(1L,subject.getId());
                assertEquals("Informatyka", subject.getName());
                assertEquals(1L, teacher.getId());
                assertEquals("Maciej", teacher.getName());
    }

    @Test
    @DisplayName("Unit test for subjectController http://localhost:8080/subject/subjectById/1 -> 200" +
            "when this subject by id not exists returning status 404 Not Found")
    public void should_return_subject_by_id_status200() throws Exception{
        School school = new School(2L, "I LO im. Tadeusza Kościuszko w Łukowie");
        Teacher teacher = new Teacher(1L, "Maciej", null, school);
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

    @Test
    @DisplayName("Unit test for subjectController http://localhost:8080/subject/deleteById/1 ->200" +
            "when this subject by id not exists returning status 500 Internal Server Error")
    public void should_deleting_subject_by_id_status200() throws Exception {
        Mockito.when(serviceSubjectImpl.findById(1L)).thenReturn(Optional.of(new Subject(1L, "Informatyka", null)));
        mockMvc.perform((MockMvcRequestBuilders.delete("/subject/deleteById/1")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Unit test for subjectController http://localhost:8080/subject/findDistinctTopByName/Informatyka ->200" +
            "when dataBase is empty returning empty list")
    void should_return_top_1_subject_by_name_distinct_status200() throws Exception {
        School school = new School(2L, "I LO im. Tadeusza Kościuszko w Łukowie");
        Teacher teacher = new Teacher(1L, "Maciej", new ArrayList<>(), school);
        Subject subject = new Subject(1L, "Informatyka", teacher);
        List<Subject> subjectList = new ArrayList<>();
        subjectList.add(subject);
        Mockito.when(serviceSubjectImpl.findDistinctTop1ByName("Informatyka")).thenReturn(subjectList);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/subject/findDistinctTopByName/Informatyka")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", "Informatyka"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());

        String jsonAsString = resultActions.andReturn().getResponse().getContentAsString();

        List<Subject> response = objectMapper.readValue(jsonAsString, new TypeReference<>() {});

        assertEquals(1L, response.get(0).getId());
        assertEquals("Informatyka", response.get(0).getName());
        assertEquals(1L, response.get(0).getTeacher().getId());
        assertEquals("Maciej", response.get(0).getTeacher().getName());
    }

    @Test
    @DisplayName("Unit test for subjectController http://localhost:8080/subject/addSubject -> 200" +
            "when this subject about this id exists this been override")
    public void should_add_new_subject() throws Exception {
        Teacher teacher = new Teacher();
        Subject subject = new Subject(1L, "Informatyka", teacher);
        Mockito.when(serviceSubjectImpl.createSubject(subject)).thenReturn(subject);
        mockMvc.perform(MockMvcRequestBuilders.post("/subject/addSubject")
                        .content(asJsonString(new Subject(1L, "Informatyka", teacher)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        Subject response = objectMapper.readValue(asJsonString(subject), Subject.class);
        assertEquals(1L , response.getId());
    }

    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
