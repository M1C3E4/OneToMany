package com.example.examplerelationmapping.controller;

import com.example.examplerelationmapping.model.Teacher;
import com.example.examplerelationmapping.service.ServiceTeacherImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private ServiceTeacherImpl serviceTeacherImpl;
    @GetMapping("/getAllTeachers")
    List<Teacher> getTeachers(){
        return serviceTeacherImpl.findAll();
    }
    @PostMapping("/addTeacher")
    Teacher addTeacher(@RequestBody Teacher teacher){
        return serviceTeacherImpl.createTeacher(teacher);
    }

}
