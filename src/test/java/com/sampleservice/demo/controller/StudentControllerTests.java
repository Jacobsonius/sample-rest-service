package com.sampleservice.demo.controller;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;
import com.sampleservice.demo.service.StudentService;
import com.sampleservice.demo.dto.inbound.StudentInDTO;
import com.sampleservice.demo.dto.outbound.StudentOutDTO;
import com.sampleservice.demo.model.Student;
import org.springframework.http.ResponseEntity;

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
    }

    @Test
    public void testGetById() {
        Long id = 1L;
        Student mockStudent = new Student();
        when(studentService.findById(id)).thenReturn(mockStudent);

        StudentOutDTO result = studentController.getById(id);
        assertNotNull(result);
    }

    @Test
    public void testSaveStudent() {
        StudentInDTO mockDto = new StudentInDTO() {
            @Override
            public Student toEntity() {
                return new Student();
            }
        };

        StudentOutDTO result = studentController.save(mockDto);
        assertNotNull(result);
    }

    @Test
    public void testDeleteStudent() {
        Long id = 1L;
        Student mockStudent = new Student();
        when(studentService.findById(id)).thenReturn(mockStudent);

        ResponseEntity<?> response = studentController.delete(id);
        assertNotNull(response);
        assertEquals(org.springframework.http.HttpStatus.OK, response.getStatusCode());
    }

    @Test(expected = NullPointerException.class)
    public void testGetById_StudentNotFound() {
        Long id = 1L;
        when(studentService.findById(id)).thenReturn(null);

        studentController.getById(id);
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteStudent_StudentNotFound() {
        Long id = 1L;
        when(studentService.findById(id)).thenReturn(null);

        studentController.delete(id);
    }
}