package com.college.college.service;

import com.college.college.model.Quiz;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    @Override
    public Quiz createQuiz(Quiz quiz) {
        EntityManager entityManager = Persistence
                .createEntityManagerFactory("college")
                .createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.persist(quiz);

        entityManager.getTransaction().commit();

        entityManager.close();

        return quiz;
    }

    @Override
    public Optional<Quiz> findQuizById(Long id) {
        return Optional.ofNullable(
                Persistence
                        .createEntityManagerFactory("college")
                        .createEntityManager()
                        .find(Quiz.class, id)
        );
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        EntityManager entityManager = Persistence
                .createEntityManagerFactory("college")
                .createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.merge(quiz);

        entityManager.getTransaction().commit();

        entityManager.close();

        return quiz;
    }

    @Override
    public void deleteQuizById(Long id) {

        EntityManager entityManager = Persistence
                .createEntityManagerFactory("college")
                .createEntityManager();

        entityManager.getTransaction().begin();

        Quiz quiz = entityManager.find(Quiz.class, id);

        if(Objects.nonNull(quiz)){
            entityManager.remove(quiz);
        }

        entityManager.getTransaction().commit();

        entityManager.close();
    }
    
}
