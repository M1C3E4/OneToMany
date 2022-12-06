package com.example.examplerelationmapping.repository;

import com.example.examplerelationmapping.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
