package com.example.examplerelationmapping.port;

import com.example.examplerelationmapping.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface ServiceTeacher {
    List<Teacher> findAll();
    Optional<Teacher> findById(Long id);
    Teacher createTeacher(Teacher teacher);
}
