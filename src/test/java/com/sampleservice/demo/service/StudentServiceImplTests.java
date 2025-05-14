package com.sampleservice.demo.service;

import com.sampleservice.demo.dao.StudentDAO;
import com.sampleservice.demo.model.Student;
import com.sampleservice.demo.validator.StudentValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class StudentServiceImplTests {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentDAO studentDAO;

    @Mock
    private StudentValidator studentValidator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testList() {
        when(studentDAO.findAll()).thenReturn(Collections.emptyList());
        Set<Student> students = (Set<Student>) studentService.list();
        assertTrue(students.isEmpty());
        verify(studentDAO, times(1)).findAll();
    }

    @Test
    public void testFindByFirstName() {
        String firstName = "John";
        Student mockStudent = new Student();
        when(studentDAO.findByFirstNameLike(firstName)).thenReturn(Optional.of(mockStudent));
        Student result = studentService.findByFirstName(firstName);
        assertNotNull(result);
        verify(studentValidator, times(1)).validate404(any(), eq("First Name"), eq(firstName));
    }

    @Test(expected = org.springframework.web.server.ResponseStatusException.class)
    public void testFindByFirstNameNotFound() {
        String firstName = "NonExistent";
        when(studentDAO.findByFirstNameLike(firstName)).thenReturn(Optional.empty());
        studentService.findByFirstName(firstName);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Student mockStudent = new Student();
        when(studentDAO.findById(id)).thenReturn(Optional.of(mockStudent));
        Student result = studentService.findById(id);
        assertNotNull(result);
        verify(studentValidator, times(1)).validate404(any(), eq("id"), eq("1"));
    }

    @Test(expected = org.springframework.web.server.ResponseStatusException.class)
    public void testFindByIdNotFound() {
        Long id = 999L;
        when(studentDAO.findById(id)).thenReturn(Optional.empty());
        studentService.findById(id);
    }

    @Test
    public void testFindByEmail() {
        String email = "test@example.com";
        Student mockStudent = new Student();
        when(studentDAO.findByEmail(email)).thenReturn(Optional.of(mockStudent));
        Student result = studentService.findByEmail(email);
        assertNotNull(result);
        verify(studentValidator, times(1)).validate404(any(), eq("email"), eq(email));
    }

    @Test(expected = org.springframework.web.server.ResponseStatusException.class)
    public void testFindByEmailNotFound() {
        String email = "nonexistent@example.com";
        when(studentDAO.findByEmail(email)).thenReturn(Optional.empty());
        studentService.findByEmail(email);
    }
}