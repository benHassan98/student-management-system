package com.college.college.service;

import com.college.college.model.Teacher;


import java.util.Optional;

public interface TeacherService {

    public Teacher createTeacher(Teacher teacher);
    public Optional<Teacher> findTeacherById(Long id);
    public Teacher updateTeacher(Teacher teacher);
    public void deleteTeacherById(Long id);
    
}
