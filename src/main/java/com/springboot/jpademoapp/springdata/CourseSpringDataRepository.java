package com.springboot.jpademoapp.springdata;

import com.springboot.jpademoapp.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

    List<Course> findByName(String name); // custom method

    @Query("Select  c  From Course c where name like '%100%'") // custom method
    List<Course> courseWith100InName();

    @Query(value = "Select  *  From Course where name like '%Administrator%'", nativeQuery = true) // custom method
    List<Course> courseWith100StepsInName_NativeQuery();

}
