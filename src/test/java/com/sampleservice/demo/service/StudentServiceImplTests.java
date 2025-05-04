package com.sampleservice.demo.service;

import com.sampleservice.demo.dao.StudentDAO;
import com.sampleservice.demo.model.Student;
import com.sampleservice.demo.validator.StudentValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceImplTests {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentDAO studentDAO;

    @Mock
    private StudentValidator studentValidator;

    private Student mockStudent;

    @Before
    public void setUp() {
        mockStudent = new Student();
        mockStudent.setId(1L);
        mockStudent.setFirstName("John");
        mockStudent.setEmail("john@example.com");
    }

    @Test(expected = RuntimeException.class)
    public void testFindByFirstName_ThrowsException() {
        Mockito.when(studentDAO.findByFirstNameLike("John"))
               .thenReturn(Optional.empty());
        Mockito.doThrow(new RuntimeException("Not found"))
               .when(studentValidator).validate404(Mockito.any(), Mockito.eq("First Name"), Mockito.eq("John"));
        studentService.findByFirstName("John");
    }

    @Test
    public void testFindByFirstName_Success() {
        Mockito.when(studentDAO.findByFirstNameLike("John"))
               .thenReturn(Optional.of(mockStudent));
        Student result = studentService.findByFirstName("John");
        Mockito.verify(studentDAO).findByFirstNameLike("John");
        Mockito.verify(studentValidator).validate404(Mockito.any(), Mockito.eq("First Name"), Mockito.eq("John"));
    }

    @Test(expected = RuntimeException.class)
    public void testFindById_ThrowsException() {
        Mockito.when(studentDAO.findById(1L)).thenReturn(Optional.empty());
        Mockito.doThrow(new RuntimeException("Not found"))
               .when(studentValidator).validate404(Mockito.any(), Mockito.eq("id"), Mockito.eq("1"));
        studentService.findById(1L);
    }

    @Test
    public void testFindById_Success() {
        Mockito.when(studentDAO.findById(1L)).thenReturn(Optional.of(mockStudent));
        Student result = studentService.findById(1L);
        Mockito.verify(studentDAO).findById(1L);
        Mockito.verify(studentValidator).validate404(Mockito.any(), Mockito.eq("id"), Mockito.eq("1"));
    }

    @Test(expected = RuntimeException.class)
    public void testFindByEmail_ThrowsException() {
        Mockito.when(studentDAO.findByFirstNameLike("john@example.com"))
               .thenReturn(Optional.empty());
        Mockito.doThrow(new RuntimeException("Not found"))
               .when(studentValidator).validate404(Mockito.any(), Mockito.eq("email"), Mockito.eq("john@example.com"));
        studentService.findByEmail("john@example.com");
    }

    @Test
    public void testFindByEmail_Success() {
        Mockito.when(studentDAO.findByFirstNameLike("john@example.com"))
               .thenReturn(Optional.of(mockStudent));
        Student result = studentService.findByEmail("john@example.com");
        Mockito.verify(studentDAO).findByFirstNameLike("john@example.com");
        Mockito.verify(studentValidator).validate404(Mockito.any(), Mockito.eq("email"), Mockito.eq("john@example.com"));
    }

    @Test
    public void testList() {
        Mockito.when(studentDAO.findAll()).thenReturn(Arrays.asList(mockStudent));
        Collection<Student> result = studentService.list();
        Mockito.verify(studentDAO).findAll();
    }

    @Test
    public void testSaveOrUpdate() {
        Mockito.when(studentDAO.save(mockStudent)).thenReturn(mockStudent);
        Student result = studentService.saveOrUpdate(mockStudent);
        Mockito.verify(studentDAO).save(mockStudent);
    }

    @Test
    public void testDelete() {
        studentService.delete(mockStudent);
        Mockito.verify(studentDAO).delete(mockStudent);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testDelete_Exception() {
        Mockito.doThrow(EmptyResultDataAccessException.class).when(studentDAO).delete(mockStudent);
        studentService.delete(mockStudent);
    }

    @Test
    public void testSaveAll() {
        Collection<Student> students = Arrays.asList(mockStudent);
        Mockito.when(studentDAO.saveAll(students)).thenReturn(students);
        Collection<Student> result = studentService.saveAll(Arrays.asList(mockStudent));
        Mockito.verify(studentDAO).saveAll(students);
    }
}