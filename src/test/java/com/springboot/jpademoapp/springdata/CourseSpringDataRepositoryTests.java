package com.springboot.jpademoapp.springdata;

import com.springboot.jpademoapp.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CourseSpringDataRepositoryTests {

    @Autowired
    private CourseSpringDataRepository courseSpringDataRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void findById(){
        Optional<Course> findById = courseSpringDataRepository.findById(10001L);
         assertTrue(findById.isPresent());
    }

    @Test
    public void playingWithSpringData(){
        /*
        Course course = new Course("My New Course");
        courseSpringDataRepository.save(course);

        course.setName("My New Course - Updated 2024");
        courseSpringDataRepository.save(course);
        logger.info("all courses using springData after Updating -> {}",courseSpringDataRepository.findAll());
        */
        logger.info("Count of all courses -> {}", courseSpringDataRepository.count());
        logger.info("all courses using springData -> {}",courseSpringDataRepository.findAll());
        /* all courses using springData -> [Course{id=1, name='Kubernetes Administrator'}, Course{id=2, name='WebServices in 100 Steps - Updated'},
        Course{id=3, name='Angular Js in 100 Steps'}, Course{id=4, name='Microservices in 100 steps'}, Course{id=5, name='Advanced SpringBoot'},
        Course{id=10001, name='JPA Course'}, Course{id=10002, name='AKS Course'}, Course{id=10003, name='AWS Course'}, Course{id=10004, name='Linux Administrator'}]
        */

    }

    @Test
    public void sort(){
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        List<Course> sortedCourses=courseSpringDataRepository.findAll(sort);
        logger.info("sortedCourses -> {}", sortedCourses);
    }

    @Test
    public void pagination(){
        PageRequest pageRequest = PageRequest.of(0,3);
        Page<Course> firstPage = courseSpringDataRepository.findAll(pageRequest);
        logger.info("firstPage -> {}", firstPage.getContent());

    }

    @Test
    public void paginationAllPages() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Course> pages = courseSpringDataRepository.findAll(pageRequest);

        while (!pages.isEmpty()) {
            logger.info("page " + (1 + pages.getNumber()) + " -> {}", pages.getContent());
            pageRequest = pageRequest.next();
            pages = courseSpringDataRepository.findAll(pageRequest);
        }
    }

    @Test
    public void findUsingName() {
        logger.info("FindByName -> {} ", courseSpringDataRepository.findByName("JPA Course"));  // Using defined custom method
    }

    @Test
    public void coursesWith100(){
        logger.info("Courses which have 100 in names -> {}", courseSpringDataRepository.courseWith100InName());  // Using defined custom method
    }

    @Test
    public void coursesAdministrator(){
        logger.info("Courses which have Administrator in names -> {}", courseSpringDataRepository.courseWith100StepsInName_NativeQuery());  // Using defined custom method
    }

}
