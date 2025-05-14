package com.sampleservice.demo.config;

import static org.mockito.Mockito.*;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.sampleservice.demo.model.Student;
import com.sampleservice.demo.service.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest
class PopulateDatabaseTests {

    @MockBean
    private StudentService studentService;

    @Test
    public void runTest() throws Exception {
        ApplicationArguments mockArgs = mock(ApplicationArguments.class);

        // Mock the behavior of the saveAll method to avoid exceptions during execution
        doNothing().when(studentService).saveAll(anyList());

        PopulateDatabase populateDatabase = new PopulateDatabase(studentService);
        
        // Handle potential exceptions in the run method
        try {
            populateDatabase.run(mockArgs);
        } catch (Exception e) {
            // Log or handle the exception if needed, but ensure the test doesn't fail unexpectedly
            verify(studentService, times(1)).saveAll(anyList());
            throw e; // Re-throw the exception to ensure proper test behavior
        }

        verify(studentService, times(1)).saveAll(anyList());
    }
}