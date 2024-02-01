package com.college.college.controller;

import com.college.college.model.Course;
import com.college.college.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {


    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCourseById(@PathVariable String id){

        return courseService
                .findCourseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping("/create")
    public ResponseEntity<?> createCourse(@RequestBody Course course){

        return ResponseEntity.ok(courseService.createCourse(course));

    }

    @PostMapping("/{courseId}/enroll/{studentId}")
    public void enrollStudent(@PathVariable String courseId, @PathVariable String studentId){

        courseService.enrollStudent(courseId, studentId);

    }


    @PutMapping("/update")
    public ResponseEntity<?> updateCourse(@RequestBody Course course){

        return ResponseEntity.ok(courseService.updateCourse(course));

    }

    @DeleteMapping("/delete/{id}")
    public void deleteCourseById(@PathVariable String id){
        courseService.deleteCourseById(id);

    }
    
    
}
