package com.college.college;

import com.college.college.model.Student;
import com.college.college.service.StudentServiceImpl;
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


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private StudentServiceImpl studentService;
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
    public void createStudent() throws Exception {

        Student student = new Student();

        student.setFirstname("first");
        student.setLastname("last");
        student.setEmail("email");
        student.setGpa(2.5F);

        String json = new ObjectMapper().writeValueAsString(student);

        MvcResult mvcRes =  mockMvc
                .perform(
                        post("/student/create").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn();

        Student resStudent = new ObjectMapper().readValue(mvcRes.getResponse().getContentAsString(), Student.class);


        assertEquals(resStudent.getFirstname(), student.getFirstname());
        assertEquals(resStudent.getLastname(), student.getLastname());
        assertEquals(resStudent.getEmail(), student.getEmail());
        assertEquals(resStudent.getGpa(), student.getGpa());


    }


    @Test
    public void findStudent() throws Exception{

        Student student = new Student();

        student.setFirstname("first");
        student.setLastname("last");
        student.setEmail("email");
        student.setGpa(2.5F);

        Long id = studentService.createStudent(student).getId();


        MvcResult mvcRes =  mockMvc
                .perform(
                        get("/student/"+id).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        Student resStudent = new ObjectMapper().readValue(mvcRes.getResponse().getContentAsString(), Student.class);


        assertEquals(resStudent.getFirstname(), student.getFirstname());
        assertEquals(resStudent.getLastname(), student.getLastname());
        assertEquals(resStudent.getEmail(), student.getEmail());
        assertEquals(resStudent.getGpa(), student.getGpa());




    }

    @Test
    public void updateStudent() throws Exception{

        Student student = new Student();

        student.setFirstname("first");
        student.setLastname("last");
        student.setEmail("email");
        student.setGpa(2.5F);

        Student savedStudent = studentService.createStudent(student);

        savedStudent.setEmail("newEmail");

        String json = new ObjectMapper().writeValueAsString(savedStudent);

        MvcResult mvcRes =  mockMvc
                .perform(
                        put("/student/update").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                                .content(json).accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        Student resStudent = new ObjectMapper().readValue(mvcRes.getResponse().getContentAsString(), Student.class);


        assertEquals(resStudent.getFirstname(), student.getFirstname());
        assertEquals(resStudent.getLastname(), student.getLastname());
        assertEquals(resStudent.getEmail(), "newEmail");
        assertEquals(resStudent.getGpa(), student.getGpa());

    }


    @Test
    public void deleteStudent() throws Exception{

        Student student = new Student();

        student.setFirstname("first");
        student.setLastname("last");
        student.setEmail("email");
        student.setGpa(2.5F);

        Long id = studentService.createStudent(student).getId();


        mockMvc
                .perform(
                        delete("/student/delete/"+id).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                                .accept(MediaType.APPLICATION_JSON)
                );

        boolean res = studentService.findStudentById(id).isEmpty();

        assertTrue(res);
    }







}
