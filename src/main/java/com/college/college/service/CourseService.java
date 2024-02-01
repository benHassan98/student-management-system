package com.college.college.service;

import com.college.college.model.Course;

import java.util.Optional;

public interface CourseService {

    public Course createCourse(Course course);
    public void enrollStudent(String courseId, String studentId);
    public Optional<Course> findCourseById(String id);
    public Course updateCourse(Course course);
    public void deleteCourseById(String id);
    
}
