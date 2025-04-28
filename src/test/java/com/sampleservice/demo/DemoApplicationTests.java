package com.sampleservice.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

import com.sampleservice.demo.model.Student;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    private Student student;

    @Before
    public void setUp() {
        student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setKlass(10);
        student.setEmail("john.doe@example.com");
    }

    @Test
    public void contextLoads() {
        // Verify that the application context loads successfully
    }

    @Test
    public void testStudentGettersAndSetters() {
        assertEquals("John", student.getFirstName());
        assertEquals("Doe", student.getLastName());
        assertEquals(Integer.valueOf(10), student.getKlass());
        assertEquals("john.doe@example.com", student.getEmail());

        student.setFirstName("Jane");
        student.setLastName("Smith");
        student.setKlass(11);
        student.setEmail("jane.smith@example.com");

        assertEquals("Jane", student.getFirstName());
        assertEquals("Smith", student.getLastName());
        assertEquals(Integer.valueOf(11), student.getKlass());
        assertEquals("jane.smith@example.com", student.getEmail());
    }
}