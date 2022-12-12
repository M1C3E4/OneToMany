package com.example.examplerelationmapping.controller;

import com.example.examplerelationmapping.model.Student;
import com.example.examplerelationmapping.service.ServiceStudentImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    private final ServiceStudentImpl serviceStudentImpl;

    public StudentController(ServiceStudentImpl serviceStudentImpl) {
        this.serviceStudentImpl = serviceStudentImpl;
    }

    @DeleteMapping("/teacher/{teacher_id}/student/{student_id}")
    public void deleteTeacher(@PathVariable(name = "teacher_id") Long teacher_id,
                              @PathVariable(name = "student_id") Long student_id){
        serviceStudentImpl.deleteStudent(teacher_id, student_id);
    }

    @PutMapping("/teacher/{teacher_id}/student")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "teacher_id") Long teacher_id,
                                        @Valid @RequestBody Student studentRequest){
        serviceStudentImpl.updateStudent(teacher_id, studentRequest);
        return new ResponseEntity<>(studentRequest, HttpStatus.OK);
    }
}
