package com.example.examplerelationmapping.controller;

import com.example.examplerelationmapping.model.Subject;
import com.example.examplerelationmapping.model.Teacher;
import com.example.examplerelationmapping.service.ServiceSubjectImpl;
import com.example.examplerelationmapping.service.ServiceTeacherImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private ServiceSubjectImpl serviceSubject;
    @Autowired
    private ServiceTeacherImpl serviceTeacher;

    @GetMapping("/subjectById/{id}")
    Optional<Subject> getFindById(@PathVariable Long id){
        return serviceSubject.findById(id);
    }

    @GetMapping("/getAllSubjects")
    List<Subject> getSubject(){
        return serviceSubject.findAll();
    }

    @PostMapping("/addSubject")
    Subject addSubject (@RequestBody Subject subject){
        return serviceSubject.createSubject(subject);
    }

    @PutMapping("/{subjectId}/teacher/{teacherId}")
    Subject assignTeachersToSubject(
            @PathVariable Long subjectId,
            @PathVariable Long teacherId
    ){
        Subject subject = serviceSubject.findById(subjectId).orElse(null);
        Teacher teacher = serviceTeacher.findById(teacherId).orElse(null);
        subject.setTeacher(teacher);
        return serviceSubject.createSubject(subject);
    }
}
