package com.sampleservice.demo.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sampleservice.demo.dao.StudentDAO;
import com.sampleservice.demo.model.Student;
import com.sampleservice.demo.validator.StudentValidator;

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
        verify(studentDAO).findAll();
    }

    @Test(expected = RuntimeException.class)
    public void testFindByFirstNameNotFound() {
        when(studentDAO.findByFirstNameLike("John")).thenReturn(Optional.empty());
        doThrow(new RuntimeException("Student not found"))
            .when(studentValidator).validate404(any(), eq("First Name"), eq("John"));
        studentService.findByFirstName("John");
    }

    @Test
    public void testFindByFirstNameSuccess() {
        Student mockStudent = new Student();
        when(studentDAO.findByFirstNameLike("John")).thenReturn(Optional.of(mockStudent));
        Student result = studentService.findByFirstName("John");
        assertNotNull(result);
        verify(studentDAO).findByFirstNameLike("John");
    }

    @Test(expected = RuntimeException.class)
    public void testFindByIdNotFound() {
        when(studentDAO.findById(1L)).thenReturn(Optional.empty());
        doThrow(new RuntimeException("Student not found"))
            .when(studentValidator).validate404(any(), eq("id"), eq("1"));
        studentService.findById(1L);
    }

    @Test
    public void testFindByIdSuccess() {
        Student mockStudent = new Student();
        when(studentDAO.findById(1L)).thenReturn(Optional.of(mockStudent));
        Student result = studentService.findById(1L);
        assertNotNull(result);
        verify(studentDAO).findById(1L);
    }

    @Test(expected = RuntimeException.class)
    public void testFindByEmailNotFound() {
        when(studentDAO.findByEmail("email@example.com")).thenReturn(Optional.empty());
        doThrow(new RuntimeException("Student not found"))
            .when(studentValidator).validate404(any(), eq("email"), eq("email@example.com"));
        studentService.findByEmail("email@example.com");
    }

    @Test
    public void testFindByEmailSuccess() {
        Student mockStudent = new Student();
        when(studentDAO.findByEmail("email@example.com")).thenReturn(Optional.of(mockStudent));
        Student result = studentService.findByEmail("email@example.com");
        assertNotNull(result);
        verify(studentDAO).findByEmail("email@example.com");
    }

    @Test
    public void testDelete() {
        Student mockStudent = new Student();
        studentService.delete(mockStudent);
        verify(studentDAO).delete(mockStudent);
    }

    @Test
    public void testSaveOrUpdate() {
        Student mockStudent = new Student();
        when(studentDAO.save(mockStudent)).thenReturn(mockStudent);
        Student result = studentService.saveOrUpdate(mockStudent);
        assertNotNull(result);
        verify(studentDAO).save(mockStudent);
    }

    @Test
    public void testSaveAll() {
        Student mockStudent1 = new Student();
        Student mockStudent2 = new Student();
        when(studentDAO.saveAll(Arrays.asList(mockStudent1, mockStudent2)))
            .thenReturn(Arrays.asList(mockStudent1, mockStudent2));
        assertEquals(2, studentService.saveAll(Arrays.asList(mockStudent1, mockStudent2)).size());
        verify(studentDAO).saveAll(Arrays.asList(mockStudent1, mockStudent2));
    }
}