package com.college.college.controller;

import com.college.college.model.Quiz;
import com.college.college.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findQuizById(@PathVariable Long id){

        return quizService
                .findQuizById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping("/create")
    public ResponseEntity<?> createQuiz(@RequestBody Quiz quiz){

        return ResponseEntity.ok(quizService.createQuiz(quiz));

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz){

        return ResponseEntity.ok(quizService.updateQuiz(quiz));

    }

    @DeleteMapping("/delete/{id}")
    public void deleteQuizById(@PathVariable Long id){
        quizService.deleteQuizById(id);

    }


}
