package com.example.examplerelationmapping.controller;

import com.example.examplerelationmapping.model.School;
import com.example.examplerelationmapping.model.Teacher;
import com.example.examplerelationmapping.service.ServiceSchoolImpl;
import com.example.examplerelationmapping.service.ServiceTeacherImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/school")
public class SchoolController {

    private final ServiceSchoolImpl serviceSchoolImpl;
    private final ServiceTeacherImpl serviceTeacherImpl;

    public SchoolController(ServiceSchoolImpl serviceSchoolImpl, ServiceTeacherImpl serviceTeacherImpl) {
        this.serviceSchoolImpl = serviceSchoolImpl;
        this.serviceTeacherImpl = serviceTeacherImpl;
    }

    @GetMapping("/allSchool")
    List<School> findAllSchool(){
       return serviceSchoolImpl.findAll();
    }

    @GetMapping("/findById/{id}")
    Optional<School> findById(@PathVariable Long id){
        return serviceSchoolImpl.findById(id);
    }

    @PostMapping("/addSchool")
    School createSchool(@RequestBody School school){
        return serviceSchoolImpl.createSchool(school);
    }

    @PutMapping("/{teacherId}/school/{schoolId}")
    School assignTeachersToSubject(
            @PathVariable Long teacherId,
            @PathVariable Long schoolId
    ){
        School school = serviceSchoolImpl.findById(schoolId).orElse(null);
        Teacher teacher = serviceTeacherImpl.findById(teacherId).orElse(null);
        teacher.setSchool(school);
        return serviceSchoolImpl.createSchool(school);
    }

}
