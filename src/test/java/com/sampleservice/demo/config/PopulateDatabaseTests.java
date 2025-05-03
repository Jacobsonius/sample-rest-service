package com.sampleservice.demo.config;

import com.sampleservice.demo.model.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.ApplicationArguments;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
public class PopulateDatabaseTests {

    @InjectMocks
    private PopulateDatabase populateDatabase;

    @Mock
    private ApplicationArguments applicationArguments;

    @Mock
    private com.sampleservice.demo.service.StudentService studentService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRun() {
        // Arrange
        List<Student> expectedStudents = new ArrayList<>();
        IntStream.range(1, 20001).forEach(id -> {
            Student student = new Student();
            student.setFirstName("f" + id);
            student.setLastName("l" + id);
            student.setEmail("email@" + id + "email.com");
            int randomClass = ThreadLocalRandom.current().nextInt(1, 12 + 1);
            student.setKlass(randomClass);
            expectedStudents.add(student);
        });

        // Act
        try {
            populateDatabase.run(applicationArguments);
        } catch (Exception e) {
            throw new RuntimeException("Error during database population", e);
        }

        // Assert
        Mockito.verify(studentService, Mockito.times(1)).saveAll(expectedStudents);
    }
}