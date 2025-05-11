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

import static org.junit.Assert.assertEquals;
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
        assertEquals(0, students.size());
    }

    @Test(expected = RuntimeException.class)
    public void testFindByFirstNameNotFound() {
        when(studentDAO.findByFirstNameLike(anyString())).thenReturn(Optional.empty());
        doNothing().when(studentValidator).validate404(any(), anyString(), anyString());
        studentService.findByFirstName("John");
    }

    @Test
    public void testFindByFirstNameFound() {
        Student mockStudent = new Student();
        mockStudent.setFirstName("John");
        when(studentDAO.findByFirstNameLike(eq("John"))).thenReturn(Optional.of(mockStudent));
        doNothing().when(studentValidator).validate404(any(), anyString(), anyString());
        Student result = studentService.findByFirstName("John");
        assertEquals(mockStudent, result);
    }

    @Test(expected = RuntimeException.class)
    public void testFindByIdNotFound() {
        when(studentDAO.findById(anyLong())).thenReturn(Optional.empty());
        doNothing().when(studentValidator).validate404(any(), anyString(), anyString());
        studentService.findById(1L);
    }

    @Test
    public void testFindByIdFound() {
        Student mockStudent = new Student();
        mockStudent.setId(1L);
        when(studentDAO.findById(eq(1L))).thenReturn(Optional.of(mockStudent));
        doNothing().when(studentValidator).validate404(any(), anyString(), anyString());
        Student result = studentService.findById(1L);
        assertEquals(mockStudent, result);
    }

    @Test(expected = RuntimeException.class)
    public void testFindByEmailNotFound() {
        when(studentDAO.findByFirstNameLike(anyString())).thenReturn(Optional.empty());
        doNothing().when(studentValidator).validate404(any(), anyString(), anyString());
        studentService.findByEmail("test@example.com");
    }

    @Test
    public void testSaveOrUpdate() {
        Student mockStudent = new Student();
        when(studentDAO.save(any(Student.class))).thenReturn(mockStudent);
        Student result = studentService.saveOrUpdate(mockStudent);
        assertEquals(mockStudent, result);
    }

    @Test
    public void testSaveAll() {
        Student mockStudent1 = new Student();
        Student mockStudent2 = new Student();
        when(studentDAO.saveAll(anyList())).thenReturn(Collections.singletonList(mockStudent1));
        Collection<Student> result = studentService.saveAll(Collections.singletonList(mockStudent2));
        assertEquals(1, result.size());
    }

    @Test
    public void testDelete() {
        Student mockStudent = new Student();
        studentService.delete(mockStudent);
        verify(studentDAO, times(1)).delete(mockStudent);
    }
}