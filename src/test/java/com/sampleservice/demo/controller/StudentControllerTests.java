package com.sampleservice.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTests {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    @Test
    public void testList() {
        Collection<com.sampleservice.demo.model.Student> mockStudents = new ArrayList<>();
        mockStudents.add(new com.sampleservice.demo.model.Student(1L, "John", "Doe"));
        when(studentService.list()).thenReturn(mockStudents);

        var result = studentController.list();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testGetById() {
        Long id = 1L;
        com.sampleservice.demo.model.Student mockStudent = new com.sampleservice.demo.model.Student(id, "Jane", "Smith");
        when(studentService.findById(id)).thenReturn(mockStudent);

        var result = studentController.getById(id);
        assertNotNull(result);
        assertEquals(id, result.getId().longValue());
    }

    @Test
    public void testSave() {
        com.sampleservice.demo.dto.inbound.StudentInDTO dto = new com.sampleservice.demo.dto.inbound.StudentInDTO();
        dto.setFirstName("New");
        dto.setLastName("User");

        com.sampleservice.demo.model.Student mockStudent = dto.toEntity();
        when(studentService.saveOrUpdate(any(com.sampleservice.demo.model.Student.class))).thenAnswer(i -> i.getArguments()[0]);

        var result = studentController.save(dto);
        assertNotNull(result);
        assertEquals("New", result.getFirstName());
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        com.sampleservice.demo.model.Student mockStudent = new com.sampleservice.demo.model.Student(id, "Old", "User");
        when(studentService.findById(id)).thenReturn(mockStudent);

        ResponseEntity<?> response = studentController.delete(id);
        verify(studentService, times(1)).delete(mockStudent);
        assertEquals(200, response.getStatusCodeValue());
    }
}