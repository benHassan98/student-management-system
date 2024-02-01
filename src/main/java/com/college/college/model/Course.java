package com.college.college.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    private String id;

    @Column(name = "teacher_id")
    private Long teacherId;
    @Column(name = "description" , nullable = false)
    private String description;

    @ElementCollection
    @CollectionTable(name = "students_courses" ,joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "student_id")
    private List<Long> studentList = new ArrayList<>();

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }


    public List<Long> getStudentList() {
        return List.copyOf(studentList);
    }

}
