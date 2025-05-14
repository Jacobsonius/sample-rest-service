package com.sampleservice.demo.controller;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    public void testList() {
        // Arrange
        Student student = new Student();
        student.setId(1L);
        when(studentService.list()).thenReturn(Arrays.asList(student));

        // Act
        List<StudentOutDTO> result = studentController.list();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testGetById_ExistingId() {
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
    public void testGetById_NonExistingId() {
        // Arrange
        Long id = 999L;
        when(studentService.findById(id)).thenReturn(null);

        // Act & Assert
        try {
            studentController.getById(id);
            fail("Expected an exception for non-existing ID");
        } catch (NullPointerException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testSave() {
        // Arrange
        StudentInDTO dto = new StudentInDTO();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setKlass(10);

        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setKlass(10);

        when(dto.toEntity()).thenReturn(student);

        // Act
        StudentOutDTO result = studentController.save(dto);

        // Assert
        assertNotNull(result);
        verify(studentService, times(1)).saveOrUpdate(student);
    }

    @Test
    public void testDelete() {
        // Arrange
        Long id = 1L;
        Student student = new Student();
        student.setId(id);
        when(studentService.findById(id)).thenReturn(student);

        // Act
        studentController.delete(id);

        // Assert
        verify(studentService, times(1)).delete(student);
    }
}