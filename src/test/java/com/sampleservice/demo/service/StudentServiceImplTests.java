package com.sampleservice.demo.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
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
        Iterable<Student> mockStudents = Arrays.asList(new Student(), new Student());
        when(studentDAO.findAll()).thenReturn(mockStudents);

        Collection<Student> result = studentService.list();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(studentDAO, times(1)).findAll();
    }

    @Test
    public void testFindByFirstName() {
        Student mockStudent = new Student();
        when(studentDAO.findByFirstNameLike("John")).thenReturn(Optional.of(mockStudent));
        doNothing().when(studentValidator).validate404(any(), any(), any());

        Student result = studentService.findByFirstName("John");

        assertNotNull(result);
        verify(studentDAO, times(1)).findByFirstNameLike("John");
        verify(studentValidator, times(1)).validate404(any(), any(), any());
    }

    @Test
    public void testFindById() {
        Student mockStudent = new Student();
        when(studentDAO.findById(1L)).thenReturn(Optional.of(mockStudent));
        doNothing().when(studentValidator).validate404(any(), any(), any());

        Student result = studentService.findById(1L);

        assertNotNull(result);
        verify(studentDAO, times(1)).findById(1L);
        verify(studentValidator, times(1)).validate404(any(), any(), any());
    }

    @Test
    public void testFindByEmail() {
        Student mockStudent = new Student();
        when(studentDAO.findByEmail("john@example.com")).thenReturn(Optional.of(mockStudent));
        doNothing().when(studentValidator).validate404(any(), any(), any());

        Student result = studentService.findByEmail("john@example.com");

        assertNotNull(result);
        verify(studentDAO, times(1)).findByEmail("john@example.com");
        verify(studentValidator, times(1)).validate404(any(), any(), any());
    }

    @Test
    public void testDelete() {
        Student mockStudent = new Student();
        doNothing().when(studentDAO).delete(mockStudent);

        studentService.delete(mockStudent);

        verify(studentDAO, times(1)).delete(mockStudent);
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
        Iterable<Student> mockStudents = Arrays.asList(new Student(), new Student());
        when(studentDAO.saveAll(any())).thenReturn(mockStudents);

        Collection<Student> result = studentService.saveAll(Arrays.asList(new Student(), new Student()));

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(studentDAO, times(1)).saveAll(any());
    }
}