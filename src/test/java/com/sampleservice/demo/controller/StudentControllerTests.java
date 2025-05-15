package com.sampleservice.demo.controller;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.sampleservice.demo.dto.inbound.StudentInDTO;
import com.sampleservice.demo.dto.outbound.StudentOutDTO;
import com.sampleservice.demo.model.Student;
import com.sampleservice.demo.service.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentControllerTests {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testList() {
        Student student1 = new Student();
        student1.setId(1L);
        student1.setFirstName("John");
        student1.setLastName("Doe");

        Student student2 = new Student();
        student2.setId(2L);
        student2.setFirstName("Jane");
        student2.setLastName("Doe");

        when(studentService.list()).thenReturn(Arrays.asList(student1, student2));

        List<StudentOutDTO> result = studentController.list();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Jane", result.get(1).getFirstName());

        verify(studentService, times(1)).list();
    }

    @Test
    public void testGetById() {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");

        when(studentService.findById(1L)).thenReturn(student);

        StudentOutDTO result = studentController.getById(1L);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());

        verify(studentService, times(1)).findById(1L);
    }

    @Test
    public void testSave() {
        StudentInDTO dto = new StudentInDTO();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setKlass(5);

        Student student = dto.toEntity();
        student.setId(1L);

        when(studentService.saveOrUpdate(any(Student.class))).thenReturn(student);

        StudentOutDTO result = studentController.save(dto);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals(Integer.valueOf(5), result.getKlass());
        assertEquals(Long.valueOf(1L), result.getId());

        verify(studentService, times(1)).saveOrUpdate(any(Student.class));
    }

    @Test
    public void testDelete() {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");

        when(studentService.findById(1L)).thenReturn(student);
        doNothing().when(studentService).delete(student);

        ResponseEntity<?> response = studentController.delete(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(studentService, times(1)).findById(1L);
        verify(studentService, times(1)).delete(student);
    }
}