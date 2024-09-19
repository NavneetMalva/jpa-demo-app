package com.springboot.jpademoapp.repository;


import com.springboot.jpademoapp.entity.Course;
import com.springboot.jpademoapp.entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class CourseRepositoryTests {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EntityManager em;

    private final Logger logger = Logger.getLogger(CourseRepositoryTests.class.getName());

    @Test
    public void findById(){
        Course course = courseRepository.findById(10001L);
        assertEquals("JPA Course", course.getName());

    }

    @Test
    @DirtiesContext
    public void deleteById(){
        courseRepository.deleteById(10001L);
        assertNull(courseRepository.findById(10001L));
    }

    @Test
    @DirtiesContext
    public void save(){
        // get a course
        Course course = courseRepository.findById(10001L);
        assertEquals("JPA Course", course.getName());

        // Update Course
        course.setName("JPA Course - Updated");
        courseRepository.save(course);

        // check the course
        Course course1 = courseRepository.findById(10001L);
        assertEquals("JPA Course - Updated", course1.getName());

    }

    @Test
    @Transactional // since for onetomany, fetch type is LAZY by default, transaction is need to get reviews from course.
    public void retrieveReviewFromCourse(){
        Course course = courseRepository.findById(10003L); // Only course details will fetch here.
        logger.info("retrieveReview from course..."+course.getReviews().toString()); // Now it will fetch the review details too.
    }

    @Test // ManyToOne, fetching is EAGER
    public void retieveCourseFromReview(){
        Review review = em.find(Review.class, 50001L);  // JOIN will happen here itself and fetch all the review and associated details of review too.
        logger.info("retieveCourseFromReview...-> "+review.getCourse()); // and here it will print.
    }

}
