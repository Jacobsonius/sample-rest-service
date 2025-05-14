package com.sampleservice.demo.service;

import com.sampleservice.demo.dao.StudentDAO;
import com.sampleservice.demo.model.Student;
import com.sampleservice.demo.validator.StudentValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.Collection;

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
        when(studentDAO.findAll()).thenReturn(Arrays.asList(new Student(), new Student()));
        Collection<Student> students = studentService.list();
        assertNotNull(students);
        assertEquals(2, students.size());
    }

    @Test
    public void testFindByFirstName() {
        Student mockStudent = new Student();
        when(studentDAO.findByFirstNameLike(anyString())).thenReturn(Optional.of(mockStudent));
        Student student = studentService.findByFirstName("John");
        assertNotNull(student);
        verify(studentValidator).validate404(any(Optional.class), eq("First Name"), eq("John"));
    }

    @Test(expected = org.springframework.web.server.ResponseStatusException.class)
    public void testFindByFirstNameNotFound() {
        when(studentDAO.findByFirstNameLike(anyString())).thenReturn(Optional.empty());
        studentService.findByFirstName("NonExistent");
        verify(studentValidator).validate404(any(Optional.class), eq("First Name"), eq("NonExistent"));
    }

    @Test
    public void testFindById() {
        Student mockStudent = new Student();
        when(studentDAO.findById(anyLong())).thenReturn(Optional.of(mockStudent));
        Student student = studentService.findById(1L);
        assertNotNull(student);
        verify(studentValidator).validate404(any(Optional.class), eq("id"), eq("1"));
    }

    @Test(expected = org.springframework.web.server.ResponseStatusException.class)
    public void testFindByIdNotFound() {
        when(studentDAO.findById(anyLong())).thenReturn(Optional.empty());
        studentService.findById(999L);
        verify(studentValidator).validate404(any(Optional.class), eq("id"), eq("999"));
    }

    @Test
    public void testFindByEmail() {
        Student mockStudent = new Student();
        when(studentDAO.findByEmail(anyString())).thenReturn(Optional.of(mockStudent));
        Student student = studentService.findByEmail("test@example.com");
        assertNotNull(student);
        verify(studentValidator).validate404(any(Optional.class), eq("email"), eq("test@example.com"));
    }

    @Test(expected = org.springframework.web.server.ResponseStatusException.class)
    public void testFindByEmailNotFound() {
        when(studentDAO.findByEmail(anyString())).thenReturn(Optional.empty());
        studentService.findByEmail("nonexistent@example.com");
        verify(studentValidator).validate404(any(Optional.class), eq("email"), eq("nonexistent@example.com"));
    }

    @Test
    public void testSaveOrUpdate() {
        Student mockStudent = new Student();
        when(studentDAO.save(any(Student.class))).thenReturn(mockStudent);
        Student savedStudent = studentService.saveOrUpdate(mockStudent);
        assertNotNull(savedStudent);
    }

    @Test
    public void testSaveAll() {
        when(studentDAO.saveAll(anyList())).thenReturn(Collections.singletonList(new Student()));
        Collection<Student> savedStudents = studentService.saveAll(Collections.singletonList(new Student()));
        assertNotNull(savedStudents);
        assertEquals(1, savedStudents.size());
    }

    @Test
    public void testDelete() {
        Student mockStudent = new Student();
        studentService.delete(mockStudent);
        verify(studentDAO, times(1)).delete(mockStudent);
    }
}