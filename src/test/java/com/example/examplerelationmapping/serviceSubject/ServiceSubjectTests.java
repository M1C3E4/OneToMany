package com.example.examplerelationmapping.serviceSubject;

import com.example.examplerelationmapping.model.Subject;
import com.example.examplerelationmapping.repository.SubjectRepository;
import com.example.examplerelationmapping.service.ServiceSubjectImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceSubjectTests {

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private ServiceSubjectImpl serviceSubjectImpl;

    @Test
    @DisplayName("")
    public void should_return_all_subject_status200() throws Exception {
    }
}
