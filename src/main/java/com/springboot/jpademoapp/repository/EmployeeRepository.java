package com.springboot.jpademoapp.repository;

import com.springboot.jpademoapp.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class EmployeeRepository {

    @Autowired
    EntityManager em;

    private final Logger logger = LoggerFactory.getLogger(EmployeeRepository.class);

    public void insertEmployee(Employee employee) {
        em.persist(employee);
    }

    public List<Employee> retrieveAllEmployees() {
        List<Employee> allEmployees = em.createQuery("select e from Employee e", Employee.class).getResultList();
        return allEmployees;
    }
    
}
