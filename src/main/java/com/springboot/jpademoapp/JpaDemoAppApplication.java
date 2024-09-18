package com.springboot.jpademoapp;

import com.springboot.jpademoapp.entity.Course;
import com.springboot.jpademoapp.repository.CourseRepository;
import com.springboot.jpademoapp.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaDemoAppApplication implements CommandLineRunner {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	private final Logger logger = LoggerFactory.getLogger(JpaDemoAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Course course= courseRepository.findById(101L);
		logger.info("Course 101 -> {}", course);
		// logger.warn("Deleting the data...");
		// repository.deleteById(102L);
		courseRepository.save(new Course("Kubernetes Administrator"));
		logger.info("Running playingWithEntityManager");
		courseRepository.playWithEntityManager();

		logger.info("Running studentRepository...");
		studentRepository.saveStudentWithPassport();
	}
}
