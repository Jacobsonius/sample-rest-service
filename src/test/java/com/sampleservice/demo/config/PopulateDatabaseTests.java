package com.sampleservice.demo.config;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sampleservice.demo.dao.StudentDAO;
import com.sampleservice.demo.model.Student;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PopulateDatabaseTests {

    @Autowired
    private StudentDAO studentDAO;

    @Test
    public void runTest() {
        try {
            // Create a sample student
            Student student = new Student();
            student.setFirstName("John");
            student.setEmail("john.doe@example.com");

            // Save the student to the database
            studentDAO.save(student);

            // Test findByFirstNameLike method
            Optional<Student> foundByFirstName = studentDAO.findByFirstNameLike("%John%");
            assertTrue(foundByFirstName.isPresent());
            assertTrue(foundByFirstName.get().getFirstName().contains("John"));

            // Test findByEmail method
            Optional<Student> foundByEmail = studentDAO.findByEmail("john.doe@example.com");
            assertTrue(foundByEmail.isPresent());
            assertTrue(foundByEmail.get().getEmail().equals("john.doe@example.com"));
        } catch (Exception e) {
            // Handle any exceptions that may occur during the test
            e.printStackTrace();
            throw e; // Re-throw the exception to fail the test
        }
    }
}