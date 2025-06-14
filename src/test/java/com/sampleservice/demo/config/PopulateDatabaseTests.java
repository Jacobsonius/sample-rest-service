package com.sampleservice.demo.config;

import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PopulateDatabase populateDatabase;

    @Test
    public void testRun() throws Exception {
        ApplicationArguments args = mock(ApplicationArguments.class);

        // Mocking the behavior of saveAll to handle a list of students
        doNothing().when(studentService).saveAll(anyList());

        // Call the run method to execute the logic
        populateDatabase.run(args);

        // Verify that saveAll was called exactly once with any list of students
        verify(studentService, times(1)).saveAll(anyList());
    }
}