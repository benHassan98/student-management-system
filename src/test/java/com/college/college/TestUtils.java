package com.college.college;

import com.college.college.model.Course;
import com.college.college.model.Quiz;
import com.college.college.model.Student;
import com.college.college.model.Teacher;
import com.college.college.service.CourseServiceImpl;
import com.college.college.service.QuizServiceImpl;
import com.college.college.service.StudentServiceImpl;
import com.college.college.service.TeacherServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class TestUtils {

    @Autowired
    public StudentServiceImpl studentService;
    @Autowired
    public TeacherServiceImpl teacherService;
    @Autowired
    public CourseServiceImpl courseService;
    @Autowired
    public QuizServiceImpl quizService;


    public Student createRandomStudent(){

        Student student = new Student();

        student.setFirstname("first");
        student.setLastname("last");
        student.setEmail("email");
        student.setGpa(2.5F);

        return studentService.createStudent(student);

    }

    public Teacher createRandomTeacher(){

        Teacher teacher = new Teacher();

        teacher.setFirstname("first");
        teacher.setLastname("last");
        teacher.setEmail("email");

        return teacherService.createTeacher(teacher);
    }

    public Course createRandomCourse(){

        Teacher teacher = this.createRandomTeacher();

        Course course = new Course();

        course.setId("HD-hbd"+ ThreadLocalRandom.current().nextInt(10, 1000+1));
        course.setDescription("ay hbd");
        course.setTeacherId(teacher.getId());

        return courseService.createCourse(course);
    }

    public Course createCourse(Long teacherId){

        Course course = new Course();

        course.setId("HD-hbd"+ ThreadLocalRandom.current().nextInt(10, 1000+1));
        course.setDescription("ay hbd");
        course.setTeacherId(teacherId);

        return courseService.createCourse(course);
    }

    public Quiz createRandomQuiz(){

        Teacher teacher = this.createRandomTeacher();
        Student student = this.createRandomStudent();
        Course course = this.createCourse(teacher.getId());

        Quiz quiz = new Quiz();

        quiz.setTeacherId(teacher.getId());
        quiz.setStudentId(student.getId());
        quiz.setCourseId(course.getId());
        quiz.setScore(5F);

        return quizService.createQuiz(quiz);
    }

    public void deleteAll(){


        EntityManager entityManager = Persistence
                .createEntityManagerFactory("college")
                .createEntityManager();

        entityManager.getTransaction().begin();

        entityManager
                .createNativeQuery("DELETE FROM students WHERE id > 0")
                .executeUpdate();

        entityManager
                .createNativeQuery("DELETE FROM teachers WHERE id > 0")
                .executeUpdate();

        entityManager
                .createNativeQuery("DELETE FROM courses WHERE id > 0")
                .executeUpdate();

        entityManager
                .createNativeQuery("DELETE FROM students_courses WHERE student_id > 0")
                .executeUpdate();

        entityManager
                .createNativeQuery("DELETE FROM quizzes WHERE id > 0")
                .executeUpdate();


        entityManager.getTransaction().commit();

        entityManager.close();


    }






}
