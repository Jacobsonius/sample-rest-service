package com.sampleservice.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sampleservice.demo.dao.StudentDAO;
import com.sampleservice.demo.model.Student;
import com.sampleservice.demo.validator.StudentValidator;

@RunWith(SpringRunner.class)
@SpringBootTest
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
        Collection<Student> mockStudents = Arrays.asList(new Student(1L, "John", "john@example.com"), new Student(2L, "Jane", "jane@example.com"));
        when(studentDAO.findAll()).thenReturn(mockStudents);

        Collection<Student> result = studentService.list();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(studentDAO, times(1)).findAll();
    }

    @Test
    public void testFindByFirstName() {
        Student mockStudent = new Student(1L, "John", "john@example.com");
        when(studentDAO.findByFirstName("John")).thenReturn(Optional.of(mockStudent));
        doNothing().when(studentValidator).validate404(Optional.of(mockStudent), "First Name", "John");

        Student result = studentService.findByFirstName("John");

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(studentDAO, times(1)).findByFirstName("John");
        verify(studentValidator, times(1)).validate404(Optional.of(mockStudent), "First Name", "John");
    }

    @Test
    public void testFindById() {
        Student mockStudent = new Student(1L, "John", "john@example.com");
        when(studentDAO.findById(1L)).thenReturn(Optional.of(mockStudent));
        doNothing().when(studentValidator).validate404(Optional.of(mockStudent), "id", "1");

        Student result = studentService.findById(1L);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        verify(studentDAO, times(1)).findById(1L);
        verify(studentValidator, times(1)).validate404(Optional.of(mockStudent), "id", "1");
    }

    @Test
    public void testFindByEmail() {
        Student mockStudent = new Student(1L, "John", "john@example.com");
        when(studentDAO.findByEmail("john@example.com")).thenReturn(Optional.of(mockStudent));
        doNothing().when(studentValidator).validate404(Optional.of(mockStudent), "email", "john@example.com");

        Student result = studentService.findByEmail("john@example.com");

        assertNotNull(result);
        assertEquals("john@example.com", result.getEmail());
        verify(studentDAO, times(1)).findByEmail("john@example.com");
        verify(studentValidator, times(1)).validate404(Optional.of(mockStudent), "email", "john@example.com");
    }

    @Test
    public void testSaveOrUpdate() {
        Student mockStudent = new Student(1L, "John", "john@example.com");
        when(studentDAO.save(mockStudent)).thenReturn(mockStudent);

        Student result = studentService.saveOrUpdate(mockStudent);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        verify(studentDAO, times(1)).save(mockStudent);
    }

    @Test
    public void testDelete() {
        Student mockStudent = new Student(1L, "John", "john@example.com");
        doNothing().when(studentDAO).delete(mockStudent);

        studentService.delete(mockStudent);

        verify(studentDAO, times(1)).delete(mockStudent);
    }

    @Test
    public void testSaveAll() {
        Student mockStudent1 = new Student(1L, "John", "john@example.com");
        Student mockStudent2 = new Student(2L, "Jane", "jane@example.com");
        Collection<Student> mockStudents = Arrays.asList(mockStudent1, mockStudent2);
        when(studentDAO.saveAll(Arrays.asList(mockStudent1, mockStudent2))).thenReturn(mockStudents);

        Collection<Student> result = studentService.saveAll(Arrays.asList(mockStudent1, mockStudent2));

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(studentDAO, times(1)).saveAll(Arrays.asList(mockStudent1, mockStudent2));
    }
}