package com.sampleservice.demo.service;

import com.sampleservice.demo.dao.StudentDAO;
import com.sampleservice.demo.model.Student;
import com.sampleservice.demo.validator.StudentValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceImplTests {

    @Mock
    private StudentDAO studentDAO;

    @Mock
    private StudentValidator studentValidator;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testList() {
        when(studentDAO.findAll()).thenReturn(Arrays.asList(new Student(), new Student()));
        assertEquals(2, studentService.list().size());
        verify(studentDAO, times(1)).findAll();
    }

    @Test
    public void testFindByFirstName() {
        Student student = new Student();
        student.setFirstName("John");
        when(studentDAO.findByFirstNameLike("John")).thenReturn(Optional.of(student));
        doNothing().when(studentValidator).validate404(any(), eq("First Name"), eq("John"));

        Student result = studentService.findByFirstName("John");

        assertEquals("John", result.getFirstName());
        verify(studentDAO, times(1)).findByFirstNameLike("John");
        verify(studentValidator, times(1)).validate404(any(), eq("First Name"), eq("John"));
    }

    @Test
    public void testFindById() {
        Student student = new Student();
        student.setId(1L);
        when(studentDAO.findById(1L)).thenReturn(Optional.of(student));
        doNothing().when(studentValidator).validate404(any(), eq("id"), eq("1"));

        Student result = studentService.findById(1L);

        assertEquals(Long.valueOf(1L), result.getId());
        verify(studentDAO, times(1)).findById(1L);
        verify(studentValidator, times(1)).validate404(any(), eq("id"), eq("1"));
    }

    @Test
    public void testFindByEmail() {
        Student student = new Student();
        student.setEmail("test@example.com");
        when(studentDAO.findByEmail("test@example.com")).thenReturn(Optional.of(student));
        doNothing().when(studentValidator).validate404(any(), eq("email"), eq("test@example.com"));

        Student result = studentService.findByEmail("test@example.com");

        assertEquals("test@example.com", result.getEmail());
        verify(studentDAO, times(1)).findByEmail("test@example.com");
        verify(studentValidator, times(1)).validate404(any(), eq("email"), eq("test@example.com"));
    }

    @Test
    public void testDelete() {
        Student student = new Student();
        student.setId(1L);

        studentService.delete(student);

        verify(studentDAO, times(1)).delete(student);
    }

    @Test
    public void testSaveOrUpdate() {
        Student student = new Student();
        student.setId(1L);
        when(studentDAO.save(student)).thenReturn(student);

        Student result = studentService.saveOrUpdate(student);

        assertEquals(Long.valueOf(1L), result.getId());
        verify(studentDAO, times(1)).save(student);
    }

    @Test
    public void testSaveAll() {
        Student student1 = new Student();
        student1.setId(1L);
        Student student2 = new Student();
        student2.setId(2L);

        when(studentDAO.saveAll(Arrays.asList(student1, student2))).thenReturn(Arrays.asList(student1, student2));

        assertEquals(2, studentService.saveAll(Arrays.asList(student1, student2)).size());
        verify(studentDAO, times(1)).saveAll(any());
    }
}