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
import java.util.List;
import java.util.Optional;
import java.util.Collection;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
        // Arrange
        when(studentDAO.findAll()).thenReturn(Arrays.asList(new Student(), new Student()));

        // Act
        Collection<Student> result = studentService.list();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testFindByFirstName() {
        // Arrange
        String firstName = "John";
        Student mockStudent = new Student();
        mockStudent.setFirstName(firstName);
        when(studentDAO.findByFirstNameLike(firstName)).thenReturn(Optional.of(mockStudent));

        // Act
        Student result = studentService.findByFirstName(firstName);

        // Assert
        assertNotNull(result);
        assertEquals(firstName, result.getFirstName());
    }

    @Test(expected = org.springframework.web.server.ResponseStatusException.class)
    public void testFindByFirstNameNotFound() {
        // Arrange
        String firstName = "NonExistent";
        when(studentDAO.findByFirstNameLike(firstName)).thenReturn(Optional.empty());

        // Act
        studentService.findByFirstName(firstName);
    }

    @Test
    public void testFindById() {
        // Arrange
        Long id = 1L;
        Student mockStudent = new Student();
        mockStudent.setId(id);
        when(studentDAO.findById(id)).thenReturn(Optional.of(mockStudent));

        // Act
        Student result = studentService.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test(expected = org.springframework.web.server.ResponseStatusException.class)
    public void testFindByIdNotFound() {
        // Arrange
        Long id = 999L;
        when(studentDAO.findById(id)).thenReturn(Optional.empty());

        // Act
        studentService.findById(id);
    }

    @Test
    public void testFindByEmail() {
        // Arrange
        String email = "test@example.com";
        Student mockStudent = new Student();
        mockStudent.setEmail(email);
        when(studentDAO.findByEmail(email)).thenReturn(Optional.of(mockStudent));

        // Act
        Student result = studentService.findByEmail(email);

        // Assert
        assertNotNull(result);
        assertEquals(email, result.getEmail());
    }

    @Test(expected = org.springframework.web.server.ResponseStatusException.class)
    public void testFindByEmailNotFound() {
        // Arrange
        String email = "nonexistent@example.com";
        when(studentDAO.findByEmail(email)).thenReturn(Optional.empty());

        // Act
        studentService.findByEmail(email);
    }

    @Test
    public void testSaveOrUpdate() {
        // Arrange
        Student mockStudent = new Student();
        when(studentDAO.save(mockStudent)).thenReturn(mockStudent);

        // Act
        Student result = studentService.saveOrUpdate(mockStudent);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testSaveAll() {
        // Arrange
        List<Student> students = Arrays.asList(new Student(), new Student());
        when(studentDAO.saveAll(students)).thenReturn(students);

        // Act
        Collection<Student> result = studentService.saveAll(students);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testDelete() {
        // Arrange
        Student mockStudent = new Student();

        // Act
        studentService.delete(mockStudent);

        // Assert
        verify(studentDAO, times(1)).delete(mockStudent);
    }
}