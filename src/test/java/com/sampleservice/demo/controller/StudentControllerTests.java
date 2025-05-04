package com.sampleservice.demo.controller;

import com.sampleservice.demo.dto.inbound.StudentInDTO;
import com.sampleservice.demo.dto.outbound.StudentOutDTO;
import com.sampleservice.demo.model.Student;
import com.sampleservice.demo.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
        List<Student> mockStudents = Arrays.asList(new Student(), new Student());
        when(studentService.list()).thenReturn(mockStudents);

        List<StudentOutDTO> result = studentController.list();
        assertEquals(mockStudents.size(), result.size());
    }

    @Test
    public void testGetStudentByIdFound() {
        Long id = 1L;
        Student mockStudent = new Student();
        when(studentService.findById(id)).thenReturn(mockStudent);

        StudentOutDTO result = studentController.getById(id);
        assertNotNull(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetStudentByIdNotFound() {
        Long id = 1L;
        when(studentService.findById(id)).thenReturn(null);

        studentController.getById(id);
    }

    @Test
    public void testSaveStudent() {
        StudentInDTO mockDto = new StudentInDTO();
        Student mockStudent = new Student();
        when(mockDto.toEntity()).thenReturn(mockStudent);

        StudentOutDTO result = studentController.save(mockDto);
        assertNotNull(result);
        verify(studentService, times(1)).saveOrUpdate(mockStudent);
    }

    @Test
    public void testDeleteStudent() {
        Long id = 1L;
        Student mockStudent = new Student();
        when(studentService.findById(id)).thenReturn(mockStudent);

        ResponseEntity<?> response = studentController.delete(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(studentService, times(1)).delete(mockStudent);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteStudentInvalidId() {
        Long id = 1L;
        when(studentService.findById(id)).thenReturn(null);

        studentController.delete(id);
    }
}