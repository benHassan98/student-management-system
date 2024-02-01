package com.college.college.service;

import com.college.college.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Override
    public Student createStudent(Student student) {

        EntityManager entityManager = Persistence
                .createEntityManagerFactory("college")
                .createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(student);

        entityManager.getTransaction().commit();

        entityManager.close();

        return student;
    }

    @Override
    public Optional<Student> findStudentById(Long id) {

        return Optional.ofNullable(
                Persistence
                        .createEntityManagerFactory("college")
                        .createEntityManager()
                        .find(Student.class, id)
        );
    }

    @Override
    public Student updateStudent(Student student) {

        EntityManager entityManager = Persistence
                .createEntityManagerFactory("college")
                .createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.merge(student);

        entityManager.getTransaction().commit();

        entityManager.close();

        return student;
    }

    @Override
    public void deleteStudentById(Long id) {

        EntityManager entityManager = Persistence
                .createEntityManagerFactory("college")
                .createEntityManager();

        entityManager.getTransaction().begin();

        Student student = entityManager.find(Student.class, id);

        if(Objects.nonNull(student)){
            entityManager.remove(student);
        }

        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
