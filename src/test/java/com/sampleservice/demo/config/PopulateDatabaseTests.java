package com.sampleservice.demo.config;

import com.sampleservice.demo.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.ApplicationArguments;
import java.util.List;

import static org.mockito.Mockito.*;

public class PopulateDatabaseTests {

    @InjectMocks
    private PopulateDatabase populateDatabase;

    @Mock
    private StudentService studentService;

    @Mock
    private ApplicationArguments applicationArguments;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRunMethod() {
        try {
            populateDatabase.run(applicationArguments);
            verify(studentService, times(1)).saveAll(any(List.class));
        } catch (Exception e) {
            // Handle exception if necessary
        }
    }
}