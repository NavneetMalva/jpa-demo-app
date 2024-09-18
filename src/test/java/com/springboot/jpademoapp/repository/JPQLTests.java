package com.springboot.jpademoapp.repository;


import com.springboot.jpademoapp.entity.Course;
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

}
