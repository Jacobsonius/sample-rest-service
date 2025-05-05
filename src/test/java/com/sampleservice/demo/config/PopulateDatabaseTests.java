package com.sampleservice.demo.config;

import com.sampleservice.demo.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PopulateDatabaseTests {

    @Test
    public void testStudentEntity() {
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setKlass(10);
        student.setEmail("john.doe@example.com");

        assertNotNull(student.getFirstName());
        assertNotNull(student.getLastName());
        assertNotNull(student.getKlass());
        assertNotNull(student.getEmail());
    }
}