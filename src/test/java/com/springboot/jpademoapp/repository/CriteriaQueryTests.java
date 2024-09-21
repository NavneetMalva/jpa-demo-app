package com.springboot.jpademoapp.repository;


import com.springboot.jpademoapp.entity.Course;
import com.springboot.jpademoapp.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CriteriaQueryTests {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager em;

    @Test
    public void all_courses() {
        // "Select c From Course c"

        // 1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define Predicates etc using Criteria Builder

        // 4. Add Predicates etc to the Criteria Query

        // 5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));

        List<Course> resultList = query.getResultList();

        logger.info(" Criteria Typed Query all courses -> {}", resultList);
    }

    @Test
    public void coursesWhereCond() {
        // "Select c From Course c where name like '%100 steps%' "

        // 1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define Predicates etc using Criteria Builder
        Predicate nameLike100 = cb.like(courseRoot.get("name"), "%100 steps%");

        // 4. Add Predicates etc to the Criteria Query
        cq.where(nameLike100);

        // 5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));

        List<Course> resultList = query.getResultList();

        logger.info(" Criteria Query to get course like 100 -> {}", resultList);
    }

    @Test
    public void coursesWithoutStudents() {
        // "Select c From Course c where c.students is empty"

        // 1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define Predicates etc using Criteria Builder
        Predicate studentIsEmpty = cb.isEmpty(courseRoot.get("students"));

        // 4. Add Predicates etc to the Criteria Query
        cq.where(studentIsEmpty);

        // 5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));

        List<Course> resultList = query.getResultList();

        logger.info(" Criteria Query to get course wihtout any students -> {}", resultList);
    }

    @Test
    public void join() {
        // "Select c From Course c join c.students s"

        // 1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define Predicates etc using Criteria Builder
        Join<Object, Object> studentsJoin = courseRoot.join("students");

        // 4. Add Predicates etc to the Criteria Query

        // 5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));

        List<Course> resultList = query.getResultList();

        logger.info("JOIN query -> {}", resultList);
    }

    @Test
    public void leftJoin() {
        // "Select c From Course c join c.students s"

        // 1. Use Criteria Builder to create a Criteria Query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are involved in the query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define Predicates etc using Criteria Builder
        Join<Object, Object> studentsJoin = courseRoot.join("students", JoinType.LEFT);

        // 4. Add Predicates etc to the Criteria Query

        // 5. Build the TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));

        List<Course> resultList = query.getResultList();

        logger.info(" LEFT JOIN Query -> {}", resultList);
    }

}
