package com.sampleservice.demo.service;

import com.sampleservice.demo.dao.StudentDAO;
import com.sampleservice.demo.model.Student;
import com.sampleservice.demo.validator.StudentValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceImplTests {

    @Mock
    private StudentDAO studentDAO;

    @Mock
    private StudentValidator studentValidator;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student mockStudent;

    @Before
    public void setUp() {
        mockStudent = new Student();
        mockStudent.setId(1L);
        mockStudent.setFirstName("John");
        mockStudent.setEmail("john@example.com");
    }

    @Test
    public void testList() {
        when(studentDAO.findAll()).thenReturn(Arrays.asList(mockStudent));

        assertEquals(1, studentService.list().size());
        verify(studentDAO, times(1)).findAll();
    }

    @Test
    public void testFindByFirstName() {
        when(studentDAO.findByFirstNameLike("John")).thenReturn(Optional.of(mockStudent));
        doNothing().when(studentValidator).validate404(any(), any(), any());

        Student result = studentService.findByFirstName("John");

        assertEquals("John", result.getFirstName());
        verify(studentDAO, times(1)).findByFirstNameLike("John");
        verify(studentValidator, times(1)).validate404(any(), any(), any());
    }

    @Test
    public void testFindById() {
        when(studentDAO.findById(1L)).thenReturn(Optional.of(mockStudent));
        doNothing().when(studentValidator).validate404(any(), any(), any());

        Student result = studentService.findById(1L);

        assertEquals(Long.valueOf(1L), result.getId());
        verify(studentDAO, times(1)).findById(1L);
        verify(studentValidator, times(1)).validate404(any(), any(), any());
    }

    @Test
    public void testFindByEmail() {
        when(studentDAO.findByFirstNameLike("john@example.com")).thenReturn(Optional.of(mockStudent));
        doNothing().when(studentValidator).validate404(any(), any(), any());

        Student result = studentService.findByEmail("john@example.com");

        assertEquals("john@example.com", result.getEmail());
        verify(studentDAO, times(1)).findByFirstNameLike("john@example.com");
        verify(studentValidator, times(1)).validate404(any(), any(), any());
    }

    @Test
    public void testDelete() {
        doNothing().when(studentDAO).delete(any());

        studentService.delete(mockStudent);

        verify(studentDAO, times(1)).delete(mockStudent);
    }

    @Test
    public void testSaveOrUpdate() {
        when(studentDAO.save(any())).thenReturn(mockStudent);

        Student result = studentService.saveOrUpdate(mockStudent);

        assertEquals(mockStudent, result);
        verify(studentDAO, times(1)).save(any());
    }

    @Test
    public void testSaveAll() {
        when(studentDAO.saveAll(any())).thenReturn(Collections.singletonList(mockStudent));

        assertEquals(1, studentService.saveAll(Collections.singletonList(mockStudent)).size());
        verify(studentDAO, times(1)).saveAll(any());
    }
}