package com.springboot.jpademoapp.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    // This will be child for the review entity.
    // One course to Many Reviews.
    // By default, for OneToMany have LAZY fetching.
    @OneToMany(mappedBy="course")
    private List<Review> reviews = new ArrayList<>();

    // This will be child entity for the student entity.
    @ManyToMany(mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    public Course(String name) {
        this.name = name;
    }

    public Course(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
