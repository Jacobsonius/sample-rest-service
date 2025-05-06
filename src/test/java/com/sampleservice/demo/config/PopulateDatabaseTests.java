package com.sampleservice.demo.config;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.ApplicationArguments;
import org.springframework.test.context.junit4.SpringRunner;

import com.sampleservice.demo.model.Student;
import com.sampleservice.demo.service.StudentService;

@RunWith(SpringRunner.class)
public class PopulateDatabaseTests {

    @Mock
    private StudentService studentService;

    @Mock
    private ApplicationArguments applicationArguments;

    @InjectMocks
    private PopulateDatabase populateDatabase;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRun() throws Exception {
        List<Student> mockStudents = new ArrayList<>();
        for (int id = 1; id < 20000; id++) {
            Student student = new Student();
            student.setFirstName("f" + id);
            student.setLastName("l" + id);
            student.setEmail("email@" + id + "email.com");
            student.setKlass(1 + (int) (Math.random() * 12));
            mockStudents.add(student);
        }

        doNothing().when(studentService).saveAll(mockStudents);

        try {
            populateDatabase.run(applicationArguments);
        } catch (Exception e) {
            // Handle exceptions if necessary, or verify they are thrown as expected
            verify(studentService, never()).saveAll(mockStudents);
            throw e;
        }

        verify(studentService, times(1)).saveAll(mockStudents);
    }
}