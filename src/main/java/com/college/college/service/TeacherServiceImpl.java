package com.college.college.service;

import com.college.college.model.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService{
    @Override
    public Teacher createTeacher(Teacher teacher) {
        EntityManager entityManager = Persistence
                .createEntityManagerFactory("college")
                .createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(teacher);

        entityManager.getTransaction().commit();

        entityManager.close();

        return teacher;
    }

    @Override
    public Optional<Teacher> findTeacherById(Long id) {
        return Optional.ofNullable(
                Persistence
                        .createEntityManagerFactory("college")
                        .createEntityManager()
                        .find(Teacher.class, id)
        );
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) {
        EntityManager entityManager = Persistence
                .createEntityManagerFactory("college")
                .createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.merge(teacher);

        entityManager.getTransaction().commit();

        entityManager.close();

        return teacher;
    }

    @Override
    public void deleteTeacherById(Long id) {

        EntityManager entityManager = Persistence
                .createEntityManagerFactory("college")
                .createEntityManager();

        entityManager.getTransaction().begin();

        Teacher teacher = entityManager.find(Teacher.class, id);

        if(Objects.nonNull(teacher)){
            entityManager.remove(teacher);
        }

        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
