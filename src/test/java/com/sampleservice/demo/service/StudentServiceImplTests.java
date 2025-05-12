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
    public void testListStudents() {
        // Arrange
        Student student1 = new Student();
        student1.setId(1L);
        when(studentDAO.findAll()).thenReturn(Arrays.asList(student1));

        // Act
        Collection<Student> students = studentService.list();

        // Assert
        assertNotNull(students);
        assertEquals(1, students.size());
    }

    @Test
    public void testFindByFirstName() {
        // Arrange
        String firstName = "John";
        Student student = new Student();
        student.setFirstName(firstName);
        when(studentDAO.findByFirstNameLike(firstName)).thenReturn(Optional.of(student));
        doNothing().when(studentValidator).validate404(any(), anyString(), anyString());

        // Act
        Student result = studentService.findByFirstName(firstName);

        // Assert
        assertNotNull(result);
        assertEquals(firstName, result.getFirstName());
    }

    @Test(expected = RuntimeException.class)
    public void testFindByFirstNameNotFound() {
        // Arrange
        String firstName = "Unknown";
        when(studentDAO.findByFirstNameLike(firstName)).thenReturn(Optional.empty());
        doNothing().when(studentValidator).validate404(any(), anyString(), anyString());

        // Act
        studentService.findByFirstName(firstName);
    }

    @Test
    public void testFindById() {
        // Arrange
        Long id = 1L;
        Student student = new Student();
        student.setId(id);
        when(studentDAO.findById(id)).thenReturn(Optional.of(student));
        doNothing().when(studentValidator).validate404(any(), anyString(), anyString());

        // Act
        Student result = studentService.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test(expected = RuntimeException.class)
    public void testFindByIdNotFound() {
        // Arrange
        Long id = 999L;
        when(studentDAO.findById(id)).thenReturn(Optional.empty());
        doNothing().when(studentValidator).validate404(any(), anyString(), anyString());

        // Act
        studentService.findById(id);
    }

    @Test
    public void testFindByEmail() {
        // Arrange
        String email = "test@example.com";
        Student student = new Student();
        student.setEmail(email);
        when(studentDAO.findByFirstNameLike(email)).thenReturn(Optional.of(student));
        doNothing().when(studentValidator).validate404(any(), anyString(), anyString());

        // Act
        Student result = studentService.findByEmail(email);

        // Assert
        assertNotNull(result);
        assertEquals(email, result.getEmail());
    }

    @Test(expected = RuntimeException.class)
    public void testFindByEmailNotFound() {
        // Arrange
        String email = "unknown@example.com";
        when(studentDAO.findByFirstNameLike(email)).thenReturn(Optional.empty());
        doNothing().when(studentValidator).validate404(any(), anyString(), anyString());

        // Act
        studentService.findByEmail(email);
    }
}