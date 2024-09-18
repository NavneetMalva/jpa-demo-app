package com.springboot.jpademoapp.repository;


import com.springboot.jpademoapp.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class CourseRepositoryTests {

    @Autowired
    CourseRepository courseRepository;

    @Test
    public void findById(){
        Course course = courseRepository.findById(101L);
        assertEquals("JPA Course", course.getName());

    }

    @Test
    @DirtiesContext
    public void deleteById(){
        courseRepository.deleteById(101L);
        assertNull(courseRepository.findById(101L));
    }

    @Test
    @DirtiesContext
    public void save(){
        // get a course
        Course course = courseRepository.findById(101L);
        assertEquals("JPA Course", course.getName());

        // Update Course
        course.setName("JPA Course - Updated");
        courseRepository.save(course);

        // check the course
        Course course1 = courseRepository.findById(101L);
        assertEquals("JPA Course - Updated", course1.getName());

    }

}
