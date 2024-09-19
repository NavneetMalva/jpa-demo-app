package com.springboot.jpademoapp.repository;

import com.springboot.jpademoapp.entity.Course;
import com.springboot.jpademoapp.entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class CourseRepository {

    @Autowired
    EntityManager em;

    private final Logger logger = LoggerFactory.getLogger(CourseRepository.class);

    public Course findById(Long id){
        return em.find(Course.class, id);
    }

    public void deleteById(Long id){
        Course course = findById(id);
        em.remove(course);
    }

    public Course save(Course course){
        if(course.getId()== null){
            em.persist(course);
        }else{
            em.merge(course);
        }
        return course;
    }

    public void playWithEntityManager(){
        Course course1 = new Course("WebServices in 100 Steps");
        em.persist(course1);
        Course course2 = new Course("Angular Js in 100 Steps");
        em.persist(course2);
        em.flush();
        em.detach(course2); // course2 will not be tracked by entityManager anymore

        course1.setName("WebServices in 100 Steps - Updated"); // This will be updated in db
        course2.setName("Angular Js in 100 Steps - Updated"); // This will not be updated in db
    }

    public void addHardCodedReviewsForCourse(){
        Course course = findById(10002L);
        logger.info("Course Reviews -> {}", course.getReviews());

        //add the reviews
        Review review1 = new Review("5","Great hands-on stuff");
        Review review2 = new Review("5","hatsoff");

        // setting the relationship
        course.addReview(review1);
        review1.setCourse(course);

        course.addReview(review2);
        review2.setCourse(course);

        // save it to the database
        // the changes are only for review table, no changes in course table
        em.persist(review1);
        em.persist(review2);

        // checking again the reviews
        logger.info("Course Reviews -> {}", course.getReviews());
    }

    public void addReviewsForCourse(Long courseId, List<Review> reviews){
        Course course = findById(courseId);
        for(Review review:reviews){
            course.addReview(review);
            review.setCourse(course);
            em.persist(review);
        }
        logger.info("Course Reviews -> {}", course.getReviews());
    }

    // This method won't map this review to any course
    // course id will be null in review table
    public void addReviewDirectTodb(){
        Review review = new Review("4","Wonderful course");
        em.persist(review);
    }

}
