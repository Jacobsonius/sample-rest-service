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
        ApplicationArguments mockArgs = mock(ApplicationArguments.class);

        when(studentService.saveAll(anyList())).thenReturn(Collections.emptyList());

        try {
            populateDatabase.run(mockArgs);
        } catch (Exception e) {
            // Log or handle the exception if necessary
            verify(studentService, atLeastOnce()).saveAll(anyList());
            throw e;
        }

        verify(studentService, times(1)).saveAll(anyList());
    }
}