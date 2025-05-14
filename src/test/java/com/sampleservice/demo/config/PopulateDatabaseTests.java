package com.sampleservice.demo.config;

import com.sampleservice.demo.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.ApplicationArguments;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PopulateDatabaseTests {

    @InjectMocks
    private PopulateDatabase populateDatabase;

    @Mock
    private StudentService studentService;

    @Mock
    private ApplicationArguments applicationArguments;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRunMethod() {
        try {
            populateDatabase.run(applicationArguments);

            // Verify that the saveAll method was called once with a list of students
            verify(studentService, times(1)).saveAll(Mockito.any(List.class));
        } catch (Exception e) {
            // Handle potential exceptions from the run method
            e.printStackTrace();
        }
    }
}