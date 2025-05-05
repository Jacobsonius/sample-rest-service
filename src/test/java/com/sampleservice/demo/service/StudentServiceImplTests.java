package com.sampleservice.demo.service;

import com.sampleservice.demo.dao.StudentDAO;
import com.sampleservice.demo.model.Student;
import com.sampleservice.demo.validator.StudentValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

public class StudentServiceImplTests {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentDAO studentDAO;

    @Mock
    private StudentValidator studentValidator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testList() {
        when(studentDAO.findAll()).thenReturn(Arrays.asList(new Student(), new Student()));
        Set<Student> students = (Set<Student>) studentService.list();
        assertNotNull(students);
        assertEquals(2, students.size());
    }

    @Test
    public void testFindByFirstName() {
        Student mockStudent = new Student();
        when(studentDAO.findByFirstNameLike(anyString())).thenReturn(Optional.of(mockStudent));
        doNothing().when(studentValidator).validate404(eq(Optional.of(mockStudent)), eq("First Name"), anyString());
        Student student = studentService.findByFirstName("John");
        assertNotNull(student);
    }

    @Test
    public void testFindByFirstName_NoResult() {
        when(studentDAO.findByFirstNameLike(anyString())).thenReturn(Optional.empty());
        try {
            studentService.findByFirstName("NonExistent");
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().contains("Not Found"));
        }
    }

    @Test
    public void testFindById() {
        Student mockStudent = new Student();
        when(studentDAO.findById(anyLong())).thenReturn(Optional.of(mockStudent));
        doNothing().when(studentValidator).validate404(eq(Optional.of(mockStudent)), eq("id"), anyString());
        Student student = studentService.findById(1L);
        assertNotNull(student);
    }

    @Test
    public void testFindById_NoResult() {
        when(studentDAO.findById(anyLong())).thenReturn(Optional.empty());
        try {
            studentService.findById(999L);
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().contains("Not Found"));
        }
    }

    @Test
    public void testFindByEmail() {
        Student mockStudent = new Student();
        when(studentDAO.findByEmail(anyString())).thenReturn(Optional.of(mockStudent));
        doNothing().when(studentValidator).validate404(eq(Optional.of(mockStudent)), eq("email"), anyString());
        Student student = studentService.findByEmail("test@example.com");
        assertNotNull(student);
    }

    @Test
    public void testFindByEmail_NoResult() {
        when(studentDAO.findByEmail(anyString())).thenReturn(Optional.empty());
        try {
            studentService.findByEmail("nonexistent@example.com");
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().contains("Not Found"));
        }
    }

    @Test
    public void testDelete() {
        Student mockStudent = new Student();
        studentService.delete(mockStudent);
        verify(studentDAO, times(1)).delete(mockStudent);
    }

    @Test
    public void testSaveOrUpdate() {
        Student mockStudent = new Student();
        when(studentDAO.save(any(Student.class))).thenReturn(mockStudent);
        Student savedStudent = studentService.saveOrUpdate(mockStudent);
        assertNotNull(savedStudent);
    }

    @Test
    public void testSaveAll() {
        List<Student> students = Arrays.asList(new Student(), new Student());
        when(studentDAO.saveAll(students)).thenReturn(students);
        Set<Student> savedStudents = (Set<Student>) studentService.saveAll(students);
        assertNotNull(savedStudents);
        assertEquals(2, savedStudents.size());
    }
}