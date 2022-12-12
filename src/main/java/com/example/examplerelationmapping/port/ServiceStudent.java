package com.example.examplerelationmapping.port;

import com.example.examplerelationmapping.model.Student;

import java.util.List;
import java.util.Optional;

public interface ServiceStudent {
    List<Student> findAll();
    Optional<Student> findById(Long id);
    Student createStudent(Student student);
    void deleteStudent(Long teacherId, Long studentId);
    void updateStudent(Long teacherId, Student studentRequest);
}
