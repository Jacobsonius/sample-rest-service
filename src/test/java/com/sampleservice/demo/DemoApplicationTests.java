package com.sampleservice.demo;

import com.sampleservice.demo.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private Student student;

    @Test
    public void contextLoads() {
        assertNotNull("Application context should load successfully", student);
    }

    @Test
    public void testStudentFields() {
        // Create a new Student object
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setKlass(10);
        student.setEmail("john.doe@example.com");

        // Verify getters return the correct values
        assertEquals("Incorrect ID", 1L, student.getId());
        assertEquals("Incorrect first name", "John", student.getFirstName());
        assertEquals("Incorrect last name", "Doe", student.getLastName());
        assertEquals("Incorrect class", 10, student.getKlass().intValue());
        assertEquals("Incorrect email", "john.doe@example.com", student.getEmail());

        // Test setters with null values
        student.setFirstName(null);
        assertNull("First name should be null", student.getFirstName());

        student.setLastName(null);
        assertNull("Last name should be null", student.getLastName());

        student.setKlass(null);
        assertNull("Class should be null", student.getKlass());

        student.setEmail(null);
        assertNull("Email should be null", student.getEmail());
    }
}