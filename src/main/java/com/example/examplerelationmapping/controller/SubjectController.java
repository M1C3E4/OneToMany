package com.example.examplerelationmapping.controller;

import com.example.examplerelationmapping.model.Subject;
import com.example.examplerelationmapping.model.Teacher;
import com.example.examplerelationmapping.service.ServiceSubjectImpl;
import com.example.examplerelationmapping.service.ServiceTeacherImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private ServiceSubjectImpl serviceSubject;
    @Autowired
    private ServiceTeacherImpl serviceTeacher;

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
        Subject subject = serviceSubject.findById(subjectId).get();
        Teacher teacher = serviceTeacher.findById(teacherId).get();
        subject.setTeacher(teacher);
        return serviceSubject.createSubject(subject);
    }
}
