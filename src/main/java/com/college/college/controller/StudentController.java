package com.college.college.controller;

import com.college.college.model.Student;
import com.college.college.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findStudentById(@PathVariable Long id){

        return studentService
                .findStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping("/create")
    public ResponseEntity<?> createStudent(@RequestBody Student student){

        return ResponseEntity.ok(studentService.createStudent(student));

    }


    @PutMapping("/update")
    public ResponseEntity<?> updateStudent(@RequestBody Student student){

        return ResponseEntity.ok(studentService.updateStudent(student));

    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudentById(@PathVariable Long id){
        studentService.deleteStudentById(id);

    }


}
