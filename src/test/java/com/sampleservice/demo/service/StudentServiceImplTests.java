package com.sampleservice.demo.service;

import com.sampleservice.demo.dao.StudentDAO;
import com.sampleservice.demo.model.Student;
import com.sampleservice.demo.validator.StudentValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.Optional;
import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

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
    public void testListStudents() {
        when(studentDAO.findAll()).thenReturn(Collections.emptyList());
        Collection<Student> students = studentService.list();
        assertNotNull(students);
        verify(studentDAO, times(1)).findAll();
    }

    @Test
    public void testFindByFirstName() {
        String firstName = "John";
        Student mockStudent = new Student();
        when(studentDAO.findByFirstNameLike(firstName)).thenReturn(Optional.of(mockStudent));
        try {
            Student result = studentService.findByFirstName(firstName);
            assertNotNull(result);
            verify(studentValidator, times(1)).validate404(eq(Optional.of(mockStudent)), eq("First Name"), eq(firstName));
        } catch (Exception e) {
            verify(studentValidator, times(1)).validate404(any(Optional.class), eq("First Name"), eq(firstName));
        }
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Student mockStudent = new Student();
        when(studentDAO.findById(id)).thenReturn(Optional.of(mockStudent));
        try {
            Student result = studentService.findById(id);
            assertNotNull(result);
            verify(studentValidator, times(1)).validate404(eq(Optional.of(mockStudent)), eq("id"), eq("1"));
        } catch (Exception e) {
            verify(studentValidator, times(1)).validate404(any(Optional.class), eq("id"), eq("1"));
        }
    }

    @Test
    public void testFindByEmail() {
        String email = "test@example.com";
        Student mockStudent = new Student();
        when(studentDAO.findByFirstNameLike(email)).thenReturn(Optional.of(mockStudent));
        try {
            Student result = studentService.findByEmail(email);
            assertNotNull(result);
            verify(studentValidator, times(1)).validate404(eq(Optional.of(mockStudent)), eq("email"), eq(email));
        } catch (Exception e) {
            verify(studentValidator, times(1)).validate404(any(Optional.class), eq("email"), eq(email));
        }
    }

    @Test
    public void testSaveOrUpdate() {
        Student mockStudent = new Student();
        when(studentDAO.save(mockStudent)).thenReturn(mockStudent);
        Student result = studentService.saveOrUpdate(mockStudent);
        assertNotNull(result);
        verify(studentDAO, times(1)).save(mockStudent);
    }

    @Test
    public void testSaveAll() {
        Student mockStudent1 = new Student();
        Student mockStudent2 = new Student();
        when(studentDAO.saveAll(Collections.singletonList(mockStudent1))).thenReturn(Collections.singletonList(mockStudent1));
        Collection<Student> result = studentService.saveAll(Collections.singletonList(mockStudent1));
        assertNotNull(result);
        verify(studentDAO, times(1)).saveAll(Collections.singletonList(mockStudent1));
    }
}