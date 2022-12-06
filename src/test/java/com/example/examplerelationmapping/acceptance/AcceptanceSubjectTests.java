package com.example.examplerelationmapping.acceptance;

import com.example.examplerelationmapping.model.Subject;
import com.example.examplerelationmapping.model.Teacher;
import com.example.examplerelationmapping.service.ServiceTeacherImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AcceptanceSubjectTests {

    @Test
    void contextLoad() {
    }

    @Autowired
    private ServiceTeacherImpl serviceTeacher;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("https://localhost:8080/subject/{subjectId}/teacher/{teacherId}")
    void should_update_subject_about_the_present_existing_teacher() throws Exception {
        Teacher teacher1 = serviceTeacher.findById(1L).orElse(null);
        mockMvc.perform(MockMvcRequestBuilders.put("/subject/5/teacher/1")
                        .content(asJsonString(new Subject(5L, "mama", teacher1)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(5L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("mama"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacher.id").value(teacher1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacher.name").value(teacher1.getName()));
    }

    @Test
    @DisplayName("http://localhost:8080/subject/findDistinctTop1ByName -> 200")
    public void should_return_list_subject_distinct_top_by_name() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/subject/findDistinctTopByName/Informatyka")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        Teacher teacher = serviceTeacher.findById(1L).orElse(null);
        Subject expected = new Subject(1L, "Informatyka", teacher);
        String jsonAsString = resultActions.andReturn().getResponse().getContentAsString();
        List<Subject> subjects = objectMapper.readValue(jsonAsString, new TypeReference<>() {});
        assertEquals(expected.getId(), subjects.get(0).getId());
        assertEquals(expected.getName(), subjects.get(0).getName());
        assertEquals(expected.getTeacher(), teacher);
    }

    @Test
    @DisplayName("https://localhost:8080/subject/deleteById/{id} -> 200")
    public void should_delete_subject_by_id() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/subject/deleteById/3"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("http://localhost:8080/subject/findSubjectWhereNameLikeString -> 200")
    public void findSubjectWhereNameLikeString() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/subject/findSubjectWhereNameLikeString")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        Teacher teacher = serviceTeacher.findById(1L).orElse(null);
        Teacher teacher1 = serviceTeacher.findById(4L).orElse(null);
        Subject expected = new Subject(1L, "Informatyka", teacher);
        Subject expected1 = new Subject(2L, "Matematyka", teacher1);
        String jsonAsString = resultActions.andReturn().getResponse().getContentAsString();
        List<Subject> subject = objectMapper.readValue(jsonAsString, new TypeReference<>() {
        });
        assertEquals(expected.getId(), subject.get(0).getId());
        assertEquals(expected.getName(), subject.get(0).getName());
        assertEquals(expected.getTeacher(), teacher);
        assertEquals(expected1.getId(), subject.get(1).getId());
        assertEquals(expected1.getName(), subject.get(1).getName());
        assertEquals(expected1.getTeacher(), teacher1);
    }

    @Test
    @DisplayName("http://localhost:8080/subject/subjectByName/{name} ->200")
    public void should_return_subject_by_name() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/subject/subjectByName/Informatyka")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", "Informatyka"))
                .andDo(print())
                .andExpect(status().isOk());
        Teacher teacher = serviceTeacher.findById(1L).orElse(null);
        Subject expected = new Subject(1L, "Informatyka", teacher);
        String jsonAsString = resultActions.andReturn().getResponse().getContentAsString();
        Subject subject = objectMapper.readValue(jsonAsString, Subject.class);
        assertEquals(expected.getId(), subject.getId());
        assertEquals(expected.getName(), subject.getName());
        assertEquals(expected.getTeacher(), teacher);
    }

    @Test
    @DisplayName("http://localhost:8080/subject/subjectById/{id} ->200")
    public void should_return_subject_by_id() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/subject/subjectById/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("id", "1L"))
                .andDo(print())
                .andExpect(status().isOk());
        Teacher teacher = serviceTeacher.findById(1L).orElse(null);
        Subject expected = new Subject(1L, "Informatyka", teacher);
        String jsonAsString = resultActions.andReturn().getResponse().getContentAsString();
        Subject subject = objectMapper.readValue(jsonAsString, Subject.class);
        assertEquals(expected.getId(), subject.getId());
        assertEquals(expected.getName(), subject.getName());
        assertEquals(expected.getTeacher(), teacher);
    }

    @Test
    @DisplayName("http://localhost:8080/subject/getAllSubjects -> 200")
    public void should_return_all_Subject() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("subject/getAllSubjects"))
                .andDo(print())
                .andExpect(status().isOk());
        Teacher teacher = new Teacher(1L, "Maciej", new ArrayList<>());
        Subject subject = new Subject(1L, "Informatyka", teacher);
        String jsonAsString = resultActions.andReturn().getResponse().getContentAsString();
        List<Subject> subject1 = objectMapper.readValue(jsonAsString, new TypeReference<>() {
        });
        assertEquals(subject.getId(), subject1.get(0).getId());
        assertEquals(subject.getName(), subject1.get(0).getName());
    }

    @Test
    @DisplayName("http://localhost:8080/subject/addSubject -> 200")
    public void should_add_new_subject() throws Exception {
        Teacher teacher = new Teacher();
        mockMvc.perform(MockMvcRequestBuilders.post("/subject/addSubject")
                        .content(asJsonString(new Subject(2L, "Matematyka", teacher)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
