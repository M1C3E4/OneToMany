package com.example.examplerelationmapping.port;

import com.example.examplerelationmapping.model.Subject;

import java.util.List;
import java.util.Optional;

public interface ServiceSubject {
    List<Subject> findAll();
    Optional<Subject> findById(Long id);
    Subject createSubject(Subject subject);
}
