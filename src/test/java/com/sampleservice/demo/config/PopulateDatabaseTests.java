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
    public void runTest() throws Exception {
        List<Student> mockStudents = new ArrayList<>();
        doAnswer(invocation -> {
            List<Student> students = invocation.getArgument(0);
            return null;
        }).when(studentService).saveAll(anyList());

        try {
            populateDatabase.run(applicationArguments);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            System.err.println("Exception occurred during test: " + e.getMessage());
            throw e;
        }

        verify(studentService, times(1)).saveAll(anyList());
    }
}