package com.example.examplerelationmapping.repository;

import com.example.examplerelationmapping.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findByName(String name);
    @Query(value = "SELECT t from Subject t WHERE t.name LIKE '%ciej%' ")
    List<Subject> findSubjectWhereLikeString();
}
