package com.college.college;

import com.college.college.model.Course;
import com.college.college.model.Student;
import com.college.college.model.Teacher;
import com.college.college.service.CourseServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CourseServiceImpl courseService;
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
    public void createCourse() throws Exception {

        Course course = new Course();

        Teacher teacher = testUtils.createRandomTeacher();
        
        course.setId("HD-hbd");
        course.setTeacherId(teacher.getId());
        course.setDescription("ay hbd tany");

        String json = new ObjectMapper().writeValueAsString(course);

        MvcResult mvcRes =  mockMvc
                .perform(
                        post("/course/create").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                                .content(json).accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        Course resCourse = new ObjectMapper().readValue(mvcRes.getResponse().getContentAsString(), Course.class);


        assertEquals(resCourse.getDescription(), course.getDescription());
        assertEquals(resCourse.getTeacherId(), course.getTeacherId());

    }


    @Test
    public void findCourse() throws Exception{

        Course course = testUtils.createRandomCourse();


        MvcResult mvcRes =  mockMvc
                .perform(
                        get("/course/"+course.getId()).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        Course resCourse = new ObjectMapper().readValue(mvcRes.getResponse().getContentAsString(), Course.class);


        assertEquals(resCourse.getId(), course.getId());

    }

    @Test
    public void updateCourse() throws Exception{

        Course course = testUtils.createRandomCourse();

        course.setDescription("updated desc");

        String json = new ObjectMapper().writeValueAsString(course);

        MvcResult mvcRes =  mockMvc
                .perform(
                        put("/course/update").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                                .content(json).accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        Course resCourse = new ObjectMapper().readValue(mvcRes.getResponse().getContentAsString(), Course.class);


        assertEquals(resCourse.getId(), course.getId());
        assertEquals(resCourse.getDescription(), course.getDescription());


    }



    @Test
    public void enrollStudentInCourse() throws Exception{

        String courseId = testUtils.createRandomCourse().getId();
        Student student = testUtils.createRandomStudent();

        mockMvc
                .perform(
                        post("/course/"+courseId+"/enroll/"+student.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
                                .accept(MediaType.APPLICATION_JSON)
                );

        Course resCourse = courseService.findCourseById(courseId).get();

        assertEquals(resCourse.getStudentList().get(0), student.getId());

    }




    @Test
    public void deleteCourse() throws Exception{

        String id = testUtils.createRandomCourse().getId();


        mockMvc
                .perform(
                        delete("/course/delete/"+id).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                                .accept(MediaType.APPLICATION_JSON)
                );

        boolean res = courseService.findCourseById(id).isEmpty();

        assertTrue(res);
    }


}
