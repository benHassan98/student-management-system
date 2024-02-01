package com.college.college;

import com.college.college.model.Course;
import com.college.college.model.Quiz;
import com.college.college.model.Student;
import com.college.college.service.QuizServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class QuizTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private QuizServiceImpl quizService;
    @Autowired
    private TestUtils testUtils;

    @BeforeEach
    public void beforeEach(){

        testUtils.deleteAll();

    }

    @AfterEach
    public void afterEach(){

        testUtils.deleteAll();

    }


    @Test
    public void createQuiz() throws Exception {

        Quiz quiz = new Quiz();

        Course course = testUtils.createRandomCourse();
        Student student = testUtils.createRandomStudent();


        quiz.setTeacherId(course.getTeacherId());
        quiz.setCourseId(course.getId());
        quiz.setStudentId(student.getId());
        quiz.setScore(5F);

        String json = new ObjectMapper().writeValueAsString(quiz);

        MvcResult mvcRes =  mockMvc
                .perform(
                        post("/quiz/create").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                                .content(json).accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        Quiz resQuiz = new ObjectMapper().readValue(mvcRes.getResponse().getContentAsString(), Quiz.class);


        assertEquals(resQuiz.getTeacherId(), quiz.getTeacherId());
        assertEquals(resQuiz.getCourseId(), quiz.getCourseId());
        assertEquals(resQuiz.getStudentId(), quiz.getStudentId());
        assertEquals(resQuiz.getScore(), quiz.getScore());

    }


    @Test
    public void findQuiz() throws Exception{

        Quiz quiz = testUtils.createRandomQuiz();


        MvcResult mvcRes =  mockMvc
                .perform(
                        get("/quiz/"+quiz.getId()).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        Quiz resQuiz = new ObjectMapper().readValue(mvcRes.getResponse().getContentAsString(), Quiz.class);


        assertEquals(resQuiz.getId(), quiz.getId());

    }

    @Test
    public void updateQuiz() throws Exception{

        Quiz quiz = testUtils.createRandomQuiz();

        quiz.setScore(6F);

        String json = new ObjectMapper().writeValueAsString(quiz);

        MvcResult mvcRes =  mockMvc
                .perform(
                        put("/quiz/update").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                                .content(json).accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        Quiz resQuiz = new ObjectMapper().readValue(mvcRes.getResponse().getContentAsString(), Quiz.class);


        assertEquals(resQuiz.getId(), quiz.getId());
        assertEquals(resQuiz.getScore(), quiz.getScore());


    }

    @Test
    public void deleteQuiz() throws Exception{

        Long id = testUtils.createRandomQuiz().getId();


        mockMvc
                .perform(
                        delete("/quiz/delete/"+id).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                                .accept(MediaType.APPLICATION_JSON)
                );

        boolean res = quizService.findQuizById(id).isEmpty();

        assertTrue(res);
    }
    
    
    
}
