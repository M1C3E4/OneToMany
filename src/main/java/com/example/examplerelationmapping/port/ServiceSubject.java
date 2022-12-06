package com.example.examplerelationmapping.port;

import com.example.examplerelationmapping.model.Subject;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ServiceSubject {

    List<Subject> findAll();
    Optional<Subject> findById(Long id);
    Subject createSubject(Subject subject);
    Optional<Subject> findByName(String name);
    @Query(value = "SELECT t from Subject t WHERE t.name LIKE '%ciej%' ")
    List<Subject> findSubjectWhereLikeString();
    void removeById(Long id);
    List<Subject> findDistinctTop1ByName(String name);
}
