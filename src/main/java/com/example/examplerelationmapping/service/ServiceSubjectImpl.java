package com.example.examplerelationmapping.service;

import com.example.examplerelationmapping.model.Subject;
import com.example.examplerelationmapping.port.ServiceSubject;
import com.example.examplerelationmapping.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceSubjectImpl implements ServiceSubject {

    private final SubjectRepository subjectRepository;

    public ServiceSubjectImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }
    @Override
    public Optional<Subject> findById(Long id) {
        return subjectRepository.findById(id);
    }
    @Override
    public Subject createSubject(Subject subject) {return subjectRepository.save(subject);}
    @Override
    public Optional<Subject> findByName(String name) {return subjectRepository.findByName(name);}
    @Override
    public List<Subject> findSubjectWhereLikeString() {return subjectRepository.findSubjectWhereLikeString();}
    @Override
    public void removeById(Long id) {subjectRepository.deleteById(id);}
    @Override
    public List<Subject> findDistinctTop1ByName(String name) {return subjectRepository.findDistinctTop1ByName(name);}
}
