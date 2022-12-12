package com.example.examplerelationmapping.service;

import com.example.examplerelationmapping.model.Student;
import com.example.examplerelationmapping.port.ServiceStudent;
import com.example.examplerelationmapping.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceStudentImpl implements ServiceStudent {

    private final StudentRepository studentRepository;

    public ServiceStudentImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long teacherId, Long studentId) {
        studentRepository.findById(studentId).map(student -> {
            studentRepository.delete(student);
            return student;
        });
    }

    @Override
    public void updateStudent(Long teacherId, Student studentRequest) {
        studentRepository.findById(studentRequest.getId()).map(student -> {
            student.setId(studentRequest.getId());
            student.setName(studentRequest.getName());
            student.setSurName(studentRequest.getSurName());
            return studentRepository.save(student);
        });
    }
}
