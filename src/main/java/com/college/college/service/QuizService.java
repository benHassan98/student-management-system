package com.college.college.service;

import com.college.college.model.Quiz;

import java.util.Optional;

public interface QuizService {

    public Quiz createQuiz(Quiz quiz);
    public Optional<Quiz> findQuizById(Long id);
    public Quiz updateQuiz(Quiz quiz);
    public void deleteQuizById(Long id);
    
    
}
