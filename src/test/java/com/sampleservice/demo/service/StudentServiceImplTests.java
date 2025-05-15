package com.sampleservice.demo.service;

import com.sampleservice.demo.dao.StudentDAO;
import com.sampleservice.demo.model.Student;
import com.sampleservice.demo.validator.StudentValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class StudentServiceImplTests {

    @Mock
    private StudentDAO studentDAO;

    @Mock
    private StudentValidator studentValidator;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testList() {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setKlass(10);
        student.setEmail("john@example.com");

        when(studentDAO.findAll()).thenReturn(Arrays.asList(student));

        assertEquals(1, studentService.list().size());
        verify(studentDAO, times(1)).findAll();
    }

    @Test
    public void testFindByFirstName() {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setKlass(10);
        student.setEmail("john@example.com");

        when(studentDAO.findByFirstNameLike("John")).thenReturn(Optional.of(student));

        Student result = studentService.findByFirstName("John");

        assertEquals("John", result.getFirstName());
        verify(studentDAO, times(1)).findByFirstNameLike("John");
        verify(studentValidator, times(1)).validate404(Optional.of(student), "First Name", "John");
    }

    @Test
    public void testFindById() {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setKlass(10);
        student.setEmail("john@example.com");

        when(studentDAO.findById(1L)).thenReturn(Optional.of(student));

        Student result = studentService.findById(1L);

        assertEquals(Long.valueOf(1L), result.getId());
        verify(studentDAO, times(1)).findById(1L);
        verify(studentValidator, times(1)).validate404(Optional.of(student), "id", "1");
    }

    @Test
    public void testFindByEmail() {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setKlass(10);
        student.setEmail("john@example.com");

        when(studentDAO.findByEmail("john@example.com")).thenReturn(Optional.of(student));

        Student result = studentService.findByEmail("john@example.com");

        assertEquals("john@example.com", result.getEmail());
        verify(studentDAO, times(1)).findByEmail("john@example.com");
        verify(studentValidator, times(1)).validate404(Optional.of(student), "email", "john@example.com");
    }

    @Test
    public void testDelete() {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setKlass(10);
        student.setEmail("john@example.com");

        doNothing().when(studentDAO).delete(student);

        studentService.delete(student);

        verify(studentDAO, times(1)).delete(student);
    }

    @Test
    public void testSaveOrUpdate() {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setKlass(10);
        student.setEmail("john@example.com");

        when(studentDAO.save(student)).thenReturn(student);

        Student result = studentService.saveOrUpdate(student);

        assertEquals(student, result);
        verify(studentDAO, times(1)).save(student);
    }

    @Test
    public void testSaveAll() {
        Student student1 = new Student();
        student1.setId(1L);
        student1.setFirstName("John");
        student1.setLastName("Doe");
        student1.setKlass(10);
        student1.setEmail("john@example.com");

        Student student2 = new Student();
        student2.setId(2L);
        student2.setFirstName("Jane");
        student2.setLastName("Doe");
        student2.setKlass(10);
        student2.setEmail("jane@example.com");

        when(studentDAO.saveAll(Arrays.asList(student1, student2))).thenReturn(Arrays.asList(student1, student2));

        assertEquals(2, studentService.saveAll(Arrays.asList(student1, student2)).size());
        verify(studentDAO, times(1)).saveAll(Arrays.asList(student1, student2));
    }
}