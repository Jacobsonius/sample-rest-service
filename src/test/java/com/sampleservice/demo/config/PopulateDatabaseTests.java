package com.sampleservice.demo.config;

import static org.mockito.Mockito.*;

import java.util.Arrays;
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
    public void runTest() throws Exception {
        ApplicationArguments mockArgs = mock(ApplicationArguments.class);

        Student student1 = new Student();
        student1.setFirstName("f1");
        student1.setLastName("l1");
        student1.setEmail("email@1email.com");
        student1.setKlass(5);

        Student student2 = new Student();
        student2.setFirstName("f2");
        student2.setLastName("l2");
        student2.setEmail("email@2email.com");
        student2.setKlass(8);

        when(studentService.saveAll(anyList())).thenReturn(Arrays.asList(student1, student2));

        try {
            populateDatabase.run(mockArgs);
        } catch (Exception e) {
            // Log or handle the exception if needed
            throw e;
        }

        verify(studentService, times(1)).saveAll(anyList());
    }
}