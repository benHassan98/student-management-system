package com.college.college.service;

import com.college.college.model.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{
    @Override
    public Course createCourse(Course course) {
        EntityManager entityManager = Persistence
                .createEntityManagerFactory("college")
                .createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(course);

        entityManager.getTransaction().commit();

        entityManager.close();

        return course;
    }

    @Override
    public void enrollStudent(String courseId, String studentId) {

        EntityManager entityManager = Persistence
                .createEntityManagerFactory("college")
                .createEntityManager();

        entityManager.getTransaction().begin();

        entityManager
                .createNativeQuery("INSERT INTO students_courses VALUES (:courseId, :studentId)")
                .setParameter("courseId", courseId)
                .setParameter("studentId", studentId)
                .executeUpdate();

        entityManager.getTransaction().commit();

        entityManager.close();

    }

    @Override
    public Optional<Course> findCourseById(String id) {
        return Optional.ofNullable(
                Persistence
                        .createEntityManagerFactory("college")
                        .createEntityManager()
                        .find(Course.class, id)
        );
    }

    @Override
    public Course updateCourse(Course course) {
        EntityManager entityManager = Persistence
                .createEntityManagerFactory("college")
                .createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.merge(course);

        entityManager.getTransaction().commit();

        entityManager.close();

        return course;
    }

    @Override
    public void deleteCourseById(String id) {

        EntityManager entityManager = Persistence
                .createEntityManagerFactory("college")
                .createEntityManager();

        entityManager.getTransaction().begin();

        Course course = entityManager.find(Course.class, id);

        if(Objects.nonNull(course)){
            entityManager.remove(course);
        }

        entityManager.getTransaction().commit();

        entityManager.close();
    }
}
