package com.sampleservice.demo.controller;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sampleservice.demo.dto.inbound.StudentInDTO;
import com.sampleservice.demo.dto.outbound.StudentOutDTO;
import com.sampleservice.demo.model.Student;
import com.sampleservice.demo.service.StudentService;

public class StudentControllerTests {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListStudents() {
        // Arrange
        Student student = new Student();
        student.setId(1L);
        when(studentService.list()).thenReturn(Collections.singletonList(student));

        // Act
        List<StudentOutDTO> result = studentController.list();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId().longValue());
    }

    @Test
    public void testGetStudentById() {
        // Arrange
        Long id = 1L;
        Student student = new Student();
        student.setId(id);
        when(studentService.findById(id)).thenReturn(student);

        // Act
        StudentOutDTO result = studentController.getById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    public void testSaveStudent() {
        // Arrange
        StudentInDTO dto = new StudentInDTO();
        Student student = dto.toEntity();
        when(studentService.saveOrUpdate(student)).then(invocation -> {
            return null; // Simulate save behavior without returning anything
        });

        // Act
        StudentOutDTO result = studentController.save(dto);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testDeleteStudent() {
        // Arrange
        Long id = 1L;
        Student student = new Student();
        student.setId(id);
        when(studentService.findById(id)).thenReturn(student);

        // Act
        ResponseEntity<?> response = studentController.delete(id);

        // Assert
        verify(studentService, times(1)).delete(student);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}