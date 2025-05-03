package com.sampleservice.demo.config;

import com.sampleservice.demo.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.ApplicationArguments;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RunWith(MockitoJUnitRunner.class)
public class PopulateDatabaseTests {

    @InjectMocks
    private PopulateDatabase populateDatabase;

    @Mock
    private ApplicationArguments applicationArguments;

    @Mock
    private com.sampleservice.demo.service.StudentService studentService;

    @Test
    public void testRun() {
        List<Student> mockStudents = new ArrayList<>();
        for (int i = 1; i <= 20000; i++) {
            Student student = new Student();
            student.setFirstName("f" + i);
            student.setLastName("l" + i);
            student.setEmail("email@" + i + "email.com");
            student.setKlass(ThreadLocalRandom.current().nextInt(1, 13));
            mockStudents.add(student);
        }

        try {
            Mockito.doNothing().when(studentService).saveAll(mockStudents);

            populateDatabase.run(applicationArguments);

            Mockito.verify(studentService, Mockito.times(1)).saveAll(mockStudents);
        } catch (Exception e) {
            Mockito.verify(studentService, Mockito.never()).saveAll(mockStudents);
            throw new RuntimeException("Test failed due to unexpected exception", e);
        }
    }
}