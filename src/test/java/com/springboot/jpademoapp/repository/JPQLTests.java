package com.springboot.jpademoapp.repository;


import com.springboot.jpademoapp.entity.Course;
import com.springboot.jpademoapp.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JPQLTests {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    EntityManager em;

    @Test
    public void findAll() {
        List resultList = em.createQuery("select c from Course c").getResultList();
        logger.info("select c from Course c -> {}", resultList);
    }

    @Test
    public void findById() {
        Long id = 1L; // Pass the desired id here
       TypedQuery<Course> result=  em.createQuery("select c from Course c where c.id = :id", Course.class);
       result.setParameter("id", id);
        List<Course> resultList = result.getResultList();
       logger.info("select c from Course c with id {} -> {}", id,resultList);
    }

    @Test
    public void findById_NativeQuery() {
        Query query = em.createNativeQuery("SELECT * FROM COURSE where id = :id", Course.class);
        query.setParameter("id", 2L);;
        List resultList = query.getResultList();
        logger.info("select c from Course c with id -> {}" ,resultList);
    }

    // select * from course where course.id not in (select course_id from student_course)
    @Test
    public void coursesWithoutStudents() {
        TypedQuery<Course> query = em.createQuery("select c from Course c where c.students is empty", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("all courses without any students -> {}" ,resultList);
    }

    @Test
    public void coursesWhereStudents() {
        TypedQuery<Course> query = em.createQuery("select c from Course c where size( c.students)>=2 ", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("all courses with more than 2 students -> {}" ,resultList);
    }

    @Test
    public void passportPattern() {
        TypedQuery<Student> query = em.createQuery("select s from Student s where s.passport.number like '%1234%'  ", Student.class);
        List<Student> resultList = query.getResultList();
        logger.info("student with passport pattern -> {}" ,resultList);
    }

    @Test
    public void join(){
        Query query = em.createQuery("select c,s from Course c JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("result size -> {}", resultList.size());
        for(Object[] result: resultList){
            logger.info("{} {}", result[0], result[1]);
        }
    }

    @Test
    public void leftJoin(){
        Query query = em.createQuery("select c,s from Course c LEFT JOIN c.students s");
        List<Object[]> resultList = query.getResultList();
        logger.info("result size -> {}", resultList.size());
        for(Object[] result: resultList){
            logger.info("{} {}", result[0], result[1]);
        }
    }


}
