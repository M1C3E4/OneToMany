package com.example.examplerelationmapping.service;

import com.example.examplerelationmapping.model.School;
import com.example.examplerelationmapping.port.ServiceSchool;
import com.example.examplerelationmapping.repository.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceSchoolImpl implements ServiceSchool {

    private final SchoolRepository schoolRepository;

    public ServiceSchoolImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public List<School> findAll() {
        return schoolRepository.findAll();
    }

    @Override
    public Optional<School> findById(Long id) {
        return schoolRepository.findById(id);
    }

    @Override
    public School createSchool(School school) {
        return schoolRepository.save(school);
    }
}
