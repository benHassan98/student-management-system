package com.college.college.controller;

import com.college.college.model.Teacher;
import com.college.college.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findTeacherById(@PathVariable Long id){

        return teacherService
                .findTeacherById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping("/create")
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher){

        return ResponseEntity.ok(teacherService.createTeacher(teacher));

    }


    @PutMapping("/update")
    public ResponseEntity<?> updateTeacher(@RequestBody Teacher teacher){

        return ResponseEntity.ok(teacherService.updateTeacher(teacher));

    }

    @DeleteMapping("/delete/{id}")
    public void deleteTeacherById(@PathVariable Long id){
        teacherService.deleteTeacherById(id);

    }
    
    
    
}
