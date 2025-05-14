package com.sampleservice.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.sampleservice.demo.service.StudentService;
import com.sampleservice.demo.dto.inbound.StudentInDTO;
import com.sampleservice.demo.dto.outbound.StudentOutDTO;
import com.sampleservice.demo.model.Student;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTests {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    @Test
    public void testList() {
        // Arrange
        Student mockStudent = new Student();
        mockStudent.setId(1L);
        when(studentService.list()).thenReturn(Collections.singletonList(mockStudent));

        // Act
        List<StudentOutDTO> result = studentController.list();

        // Assert
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId().longValue());
    }

    @Test
    public void testGetById() {
        // Arrange
        Long id = 1L;
        Student mockStudent = new Student();
        mockStudent.setId(id);
        when(studentService.findById(id)).thenReturn(mockStudent);

        // Act
        StudentOutDTO result = studentController.getById(id);

        // Assert
        assertEquals(id, result.getId());
    }

    @Test
    public void testSave() {
        // Arrange
        StudentInDTO dto = new StudentInDTO();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setKlass(10);
        Student mockStudent = dto.toEntity();
        when(studentService.saveOrUpdate(mockStudent)).thenReturn(mockStudent);

        // Act
        StudentOutDTO result = studentController.save(dto);

        // Assert
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals(Integer.valueOf(10), result.getKlass());
    }

    @Test
    public void testDelete() {
        // Arrange
        Long id = 1L;
        Student mockStudent = new Student();
        mockStudent.setId(id);
        when(studentService.findById(id)).thenReturn(mockStudent);

        // Act
        ResponseEntity<?> response = null;
        try {
            response = studentController.delete(id);
        } catch (Exception e) {
            // Assert
            verify(studentService, never()).delete(mockStudent);
            throw e;
        }

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(studentService, times(1)).delete(mockStudent);
    }
}