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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(studentService, times(1)).list();
    }

    @Test
    public void testGetById() {
        Long id = 1L;
        Student mockStudent = new Student();
        when(studentService.findById(id)).thenReturn(mockStudent);

        StudentOutDTO result = studentController.getById(id);
        assertNotNull(result);
        verify(studentService, times(1)).findById(id);
    }

    @Test
    public void testSaveStudent() {
        StudentInDTO mockDto = new StudentInDTO();
        Student mockStudent = new Student();
        when(mockDto.toEntity()).thenReturn(mockStudent);

        StudentOutDTO result = studentController.save(mockDto);
        assertNotNull(result);
        verify(studentService, times(1)).saveOrUpdate(any(Student.class));
    }

    @Test
    public void testDeleteStudent() {
        Long id = 1L;
        Student mockStudent = new Student();
        when(studentService.findById(id)).thenReturn(mockStudent);

        ResponseEntity<?> response = studentController.delete(id);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(studentService, times(1)).delete(mockStudent);
    }
}