package com.sampleservice.demo.controller;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
    public void testList() {
        Student student = new Student();
        student.setId(1L);
        when(studentService.list()).thenReturn(Collections.singletonList(student));

        List<StudentOutDTO> result = studentController.list();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testGetById() {
        Student student = new Student();
        student.setId(1L);
        when(studentService.findById(1L)).thenReturn(student);

        StudentOutDTO result = studentController.getById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId().longValue());
    }

    @Test
    public void testSave() {
        StudentInDTO dto = new StudentInDTO();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setKlass(10);

        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setKlass(10);

        when(dto.toEntity()).thenAnswer(invocation -> {
            Student entity = new Student();
            entity.setFirstName(dto.getFirstName());
            entity.setLastName(dto.getLastName());
            entity.setKlass(dto.getKlass());
            return entity;
        });
        doNothing().when(studentService).saveOrUpdate(student);

        StudentOutDTO result = studentController.save(dto);
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
    }

    @Test
    public void testDelete() {
        Student student = new Student();
        student.setId(1L);
        when(studentService.findById(1L)).thenReturn(student);
        doNothing().when(studentService).delete(student);

        ResponseEntity<?> response = studentController.delete(1L);
        assertNotNull(response);
        assertEquals(org.springframework.http.HttpStatus.OK, response.getStatusCode());
    }
}