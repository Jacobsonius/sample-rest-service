package com.sampleservice.demo;

import com.sampleservice.demo.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
        // Ensure the application context loads correctly
    }

    @Test
    public void testStudentEntity() {
        // Create a new Student object
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setKlass(10);
        student.setEmail("john.doe@example.com");

        // Verify getters return the correct values
        assertEquals(1L, student.getId().longValue());
        assertEquals("John", student.getFirstName());
        assertEquals("Doe", student.getLastName());
        assertEquals(10, student.getKlass().intValue());
        assertEquals("john.doe@example.com", student.getEmail());

        // Verify toString() method (if implemented in the future)
        assertNotNull(student.toString());
    }
}