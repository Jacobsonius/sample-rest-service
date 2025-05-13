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
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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
    public void testList() {
        when(studentDAO.findAll()).thenReturn(Arrays.asList(new Student(1L, "John", "Doe", "john@example.com")));

        assertNotNull(studentService.list());
        assertEquals(1, studentService.list().size());
        verify(studentDAO, times(1)).findAll();
    }

    @Test
    public void testFindByFirstName() {
        Student mockStudent = new Student();
        mockStudent.setId(1L);
        mockStudent.setFirstName("John");
        mockStudent.setLastName("Doe");
        mockStudent.setEmail("john@example.com");

        when(studentDAO.findByFirstNameLike("John")).thenReturn(Optional.of(mockStudent));
        doNothing().when(studentValidator).validate404(any(), any(), any());

        Student result = studentService.findByFirstName("John");

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(studentDAO, times(1)).findByFirstNameLike("John");
    }

    @Test
    public void testFindById() {
        Student mockStudent = new Student();
        mockStudent.setId(1L);
        mockStudent.setFirstName("John");
        mockStudent.setLastName("Doe");
        mockStudent.setEmail("john@example.com");

        when(studentDAO.findById(1L)).thenReturn(Optional.of(mockStudent));
        doNothing().when(studentValidator).validate404(any(), any(), any());

        Student result = studentService.findById(1L);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        verify(studentDAO, times(1)).findById(1L);
    }

    @Test
    public void testFindByEmail() {
        Student mockStudent = new Student();
        mockStudent.setId(1L);
        mockStudent.setFirstName("John");
        mockStudent.setLastName("Doe");
        mockStudent.setEmail("john@example.com");

        when(studentDAO.findByFirstNameLike("john@example.com")).thenReturn(Optional.of(mockStudent));
        doNothing().when(studentValidator).validate404(any(), any(), any());

        Student result = studentService.findByEmail("john@example.com");

        assertNotNull(result);
        assertEquals("john@example.com", result.getEmail());
        verify(studentDAO, times(1)).findByFirstNameLike("john@example.com");
    }

    @Test
    public void testDelete() {
        Student mockStudent = new Student();
        mockStudent.setId(1L);
        mockStudent.setFirstName("John");
        mockStudent.setLastName("Doe");
        mockStudent.setEmail("john@example.com");

        doNothing().when(studentDAO).delete(mockStudent);

        studentService.delete(mockStudent);

        verify(studentDAO, times(1)).delete(mockStudent);
    }

    @Test
    public void testSaveOrUpdate() {
        Student mockStudent = new Student();
        mockStudent.setId(1L);
        mockStudent.setFirstName("John");
        mockStudent.setLastName("Doe");
        mockStudent.setEmail("john@example.com");

        when(studentDAO.save(mockStudent)).thenReturn(mockStudent);

        Student result = studentService.saveOrUpdate(mockStudent);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        verify(studentDAO, times(1)).save(mockStudent);
    }

    @Test
    public void testSaveAll() {
        Student mockStudent1 = new Student();
        mockStudent1.setId(1L);
        mockStudent1.setFirstName("John");
        mockStudent1.setLastName("Doe");
        mockStudent1.setEmail("john@example.com");

        Student mockStudent2 = new Student();
        mockStudent2.setId(2L);
        mockStudent2.setFirstName("Jane");
        mockStudent2.setLastName("Doe");
        mockStudent2.setEmail("jane@example.com");

        when(studentDAO.saveAll(Arrays.asList(mockStudent1, mockStudent2))).thenReturn(Arrays.asList(mockStudent1, mockStudent2));

        assertNotNull(studentService.saveAll(Arrays.asList(mockStudent1, mockStudent2)));
        assertEquals(2, studentService.saveAll(Arrays.asList(mockStudent1, mockStudent2)).size());
        verify(studentDAO, times(2)).saveAll(any());
    }
}