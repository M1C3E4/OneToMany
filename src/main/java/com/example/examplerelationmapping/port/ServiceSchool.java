package com.example.examplerelationmapping.port;

import com.example.examplerelationmapping.model.School;

import java.util.List;
import java.util.Optional;

public interface ServiceSchool {
    List<School> findAll();
    Optional<School> findById(Long id);
    School createSchool(School school);
}
