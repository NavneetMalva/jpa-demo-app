package com.springboot.jpademoapp.repository;

import com.springboot.jpademoapp.entity.Course;
import com.springboot.jpademoapp.entity.Passport;
import com.springboot.jpademoapp.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class StudentRepository {

    @Autowired
    EntityManager em;

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public void deleteById(Long id) {
        Student student = findById(id);
        em.remove(student);
    }

    public Student save(Student student) {

        if (student.getId() == null) {
            em.persist(student);
        } else {
            em.merge(student);
        }
        return student;
    }


    public void saveStudentWithPassport() {
        Passport passport = new Passport("Z123456");
        em.persist(passport);

        Student student = new Student("Mike");
        student.setPassport(passport);
        em.persist(student);

    }

    public void insertHardCodedStudentAndCourse(){
        Student student = new Student("Jack");
        em.persist(student);
        Course course = new Course("Microservices in 100 steps");
        em.persist(course);
        student.addCourse(course);
        course.addStudent(student);
        em.persist(student);
    }

    public void insertStudentAndCourse(Student student, Course course) {
        student.addCourse(course);
        course.addStudent(student);
        em.persist(student);
        em.persist(course);
    }

}
