package com.example.examplerelationmapping.controller;

import com.example.examplerelationmapping.model.Teacher;
import com.example.examplerelationmapping.service.ServiceTeacherImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private final ServiceTeacherImpl serviceTeacherImpl;
    public TeacherController(ServiceTeacherImpl serviceTeacherImpl) {
        this.serviceTeacherImpl = serviceTeacherImpl;
    }
    @GetMapping("/getAllTeachers")
    List<Teacher> getTeachers(){
        return serviceTeacherImpl.findAll();
    }
    @PostMapping("/addTeacher")
    Teacher addTeacher(@RequestBody Teacher teacher){
        return serviceTeacherImpl.createTeacher(teacher);
    }

}
