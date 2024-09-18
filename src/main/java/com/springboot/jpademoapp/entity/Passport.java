package com.springboot.jpademoapp.entity;

import jakarta.persistence.*;

@Entity
public class Passport {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String number;

    // mapped by is defined in non-owning side , so that there wont be any duplication.
    // if we dont define that student will have passport_id, and passport will have student_id
    // this will be duplication of ids
    // Also on non-owning side(child side), fetch type is always EAGER even if you defined.
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "passport")
    private Student student;

    protected Passport() {
    }

    public Passport(String number) {
        this.number = number;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Passport[%s]", number);
    }
}
