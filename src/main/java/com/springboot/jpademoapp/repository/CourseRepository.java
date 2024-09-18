package com.springboot.jpademoapp.repository;

import com.springboot.jpademoapp.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CourseRepository {

    @Autowired
    EntityManager em;

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

}
