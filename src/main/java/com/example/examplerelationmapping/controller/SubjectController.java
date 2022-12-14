package com.example.examplerelationmapping.controller;

import com.example.examplerelationmapping.model.Subject;
import com.example.examplerelationmapping.model.Teacher;
import com.example.examplerelationmapping.service.ServiceSubjectImpl;
import com.example.examplerelationmapping.service.ServiceTeacherImpl;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private final ServiceSubjectImpl serviceSubject;
    private final ServiceTeacherImpl serviceTeacher;

    public SubjectController(ServiceSubjectImpl serviceSubject, ServiceTeacherImpl serviceTeacher) {
        this.serviceSubject = serviceSubject;
        this.serviceTeacher = serviceTeacher;
    }

    @GetMapping("/findDistinctTopByName/{name}")
    List<Subject> findDistinctTop1ByName(@PathVariable String name){return serviceSubject.findDistinctTop1ByName(name);}

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Long id){serviceSubject.removeById(id);}

    @GetMapping("/findSubjectWhereNameLikeString")
    List<Subject> findSubjectWhereNameLikeString(){
        return serviceSubject.findSubjectWhereLikeString();
    }

    @GetMapping("/subjectById/{id}")
    Optional<Subject> getFindById(@PathVariable Long id){
        return serviceSubject.findById(id);
    }

    @GetMapping("/subjectByName/{name}")
    Optional<Subject> getSubjectByName(@PathVariable String name){
        return serviceSubject.findByName(name);
    }

    @GetMapping("/getAllSubjects")
    List<Subject> getSubjects(){
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
