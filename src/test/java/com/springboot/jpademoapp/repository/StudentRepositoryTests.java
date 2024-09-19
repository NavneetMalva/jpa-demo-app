package com.springboot.jpademoapp.repository;


import com.springboot.jpademoapp.entity.Course;
import com.springboot.jpademoapp.entity.Passport;
import com.springboot.jpademoapp.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentRepositoryTests {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EntityManager em;

    private final Logger logger = LoggerFactory.getLogger(StudentRepositoryTests.class);

    @Test
    @Transactional //withour this, studen.getPassport will give error because of fetch type LAZY.
    public void retrieveStudentPassport(){
        Student student = em.find(Student.class, 20001L);
        logger.info("student -> {}", student);
        logger.info("passport -> {}", student.getPassport());

    }

    @Test
    public void retrievePassportAndStudent(){
        Passport passport = em.find(Passport.class, 40001L);
        logger.info("passport -> {}", passport);
        logger.info("student -> {}", passport.getStudent()); //Eventhough the fetch type is LAZY, this will work because this is child side.

    }

    @Test
    @Transactional // By default, LAZY fetching for ManyToMany
    public void retrieveStudentAndCourse(){
        Student student = em.find(Student.class, 20001L);
        logger.info("retrieveStudentAndCourse student  -> {}", student);
        logger.info("retrieveStudentAndCourse courses  -> {}", student.getCourses());
    }

    @Test
    @Transactional // By default, LAZY fetching for ManyToMany
    public void retrieveCourseAndStudent(){
        Course course = em.find(Course.class, 10001L);
        logger.info("retrieveCourseAndStudent course -> {}", course);
        logger.info("retrieveCourseAndStudent student -> {}", course.getStudents());
    }

}
