package com.college.college;


import com.college.college.model.Teacher;
import com.college.college.model.Teacher;
import com.college.college.service.TeacherServiceImpl;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TeacherTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TeacherServiceImpl teacherService;
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
    public void createTeacher() throws Exception {

        Teacher teacher = new Teacher();

        teacher.setFirstname("first");
        teacher.setLastname("last");
        teacher.setEmail("email");


        String json = new ObjectMapper().writeValueAsString(teacher);

        MvcResult mvcRes =  mockMvc
                .perform(
                        post("/teacher/create").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                                .content(json).accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        Teacher resTeacher = new ObjectMapper().readValue(mvcRes.getResponse().getContentAsString(), Teacher.class);


        assertEquals(resTeacher.getFirstname(), teacher.getFirstname());
        assertEquals(resTeacher.getLastname(), teacher.getLastname());
        assertEquals(resTeacher.getEmail(), teacher.getEmail());



    }


    @Test
    public void findTeacher() throws Exception{

        Teacher teacher = new Teacher();

        teacher.setFirstname("first");
        teacher.setLastname("last");
        teacher.setEmail("email");


        Long id = teacherService.createTeacher(teacher).getId();

        MvcResult mvcRes =  mockMvc
                .perform(
                        get("/teacher/"+id).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        Teacher resTeacher = new ObjectMapper().readValue(mvcRes.getResponse().getContentAsString(), Teacher.class);


        assertEquals(resTeacher.getFirstname(), teacher.getFirstname());
        assertEquals(resTeacher.getLastname(), teacher.getLastname());
        assertEquals(resTeacher.getEmail(), teacher.getEmail());





    }

    @Test
    public void updateTeacher() throws Exception{

        Teacher teacher = new Teacher();

        teacher.setFirstname("first");
        teacher.setLastname("last");
        teacher.setEmail("email");


        Teacher savedTeacher = teacherService.createTeacher(teacher);

        savedTeacher.setEmail("newEmail");

        String json = new ObjectMapper().writeValueAsString(savedTeacher);

        MvcResult mvcRes =  mockMvc
                .perform(
                        put("/teacher/update").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                                .content(json).accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        Teacher resTeacher = new ObjectMapper().readValue(mvcRes.getResponse().getContentAsString(), Teacher.class);


        assertEquals(resTeacher.getFirstname(), teacher.getFirstname());
        assertEquals(resTeacher.getLastname(), teacher.getLastname());
        assertEquals(resTeacher.getEmail(), "newEmail");


    }

    @Test
    public void deleteTeacher() throws Exception{

        Teacher teacher = new Teacher();

        teacher.setFirstname("first");
        teacher.setLastname("last");
        teacher.setEmail("email");


        Long id = teacherService.createTeacher(teacher).getId();


        mockMvc
                .perform(
                        delete("/teacher/delete/"+id).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                                .accept(MediaType.APPLICATION_JSON)
                );

        boolean res = teacherService.findTeacherById(id).isEmpty();

        assertTrue(res);
    }
    
}
