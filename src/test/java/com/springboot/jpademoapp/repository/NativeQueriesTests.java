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
public class NativeQueriesTests {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    EntityManager em;

    @Test
    public void retriveAllCourses() {
        Query query = em.createNativeQuery("SELECT * FROM COURSE", Course.class);
        List resultList = query.getResultList();
        logger.info("SELECT * FROM COURSE  -> {}", resultList);
    }

    @Test
    public void findById_with_parameter() {
        Query query = em.createNativeQuery("SELECT * FROM COURSE where id = ?", Course.class);
        query.setParameter(1, 10001L);
        List resultList = query.getResultList();
        logger.info("SELECT * FROM COURSE  where id = ? -> {}", resultList);
    }

    @Test
    public void findById_with_NamedParameter() {
        Query query = em.createNativeQuery("SELECT * FROM COURSE where id = :id", Course.class);
        query.setParameter("id", 2L);;
        List resultList = query.getResultList();
        logger.info("select c from Course c with id -> {}" ,resultList);
    }

}
