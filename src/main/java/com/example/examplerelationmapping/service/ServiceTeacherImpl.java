package com.example.examplerelationmapping.service;

import com.example.examplerelationmapping.model.Teacher;
import com.example.examplerelationmapping.port.ServiceTeacher;
import com.example.examplerelationmapping.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceTeacherImpl implements ServiceTeacher {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }
}
