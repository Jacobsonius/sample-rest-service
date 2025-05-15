package com.sampleservice.demo.validator;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;
import java.util.Optional;

@SpringBootTest
public class StudentValidatorTests {

    private StudentValidator studentValidator = new StudentValidator();

    @Test(expected = org.springframework.web.server.ResponseStatusException.class)
    public void testValidate404ThrowsException() {
        Optional<String> emptyOptional = Optional.empty();
        studentValidator.validate404(emptyOptional, "First Name", "Jacob");
    }

    @Test
    public void testValidate404Success() {
        Optional<String> presentOptional = Optional.of("Jacob");
        try {
            studentValidator.validate404(presentOptional, "First Name", "Jacob");
        } catch (Exception e) {
            fail("Unexpected exception thrown: " + e.getMessage());
        }
    }
}