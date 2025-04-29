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
    public void testRunMethod() {
        List<Student> mockStudents = new ArrayList<>();
        for (int i = 1; i <= 20000; i++) {
            Student student = new Student();
            student.setFirstName("f" + i);
            student.setLastName("l" + i);
            student.setEmail("email@" + i + "email.com");
            student.setKlass(ThreadLocalRandom.current().nextInt(1, 13));
            mockStudents.add(student);
        }

        populateDatabase.run(applicationArguments);

        Mockito.verify(studentService, Mockito.times(1)).saveAll(mockStudents);
    }
}