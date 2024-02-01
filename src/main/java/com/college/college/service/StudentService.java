package com.college.college.service;

import com.college.college.model.Student;

import java.util.Optional;

public interface StudentService {

    public Student createStudent(Student student);
    public Optional<Student> findStudentById(Long id);
    public Student updateStudent(Student student);
    public void deleteStudentById(Long id);


}
