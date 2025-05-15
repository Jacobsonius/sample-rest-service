package com.sampleservice.demo.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentValidatorTests {

    @Autowired
    private StudentValidator studentValidator;

    @Test
    public void contextLoads() {
        // Ensure the application context loads successfully
    }

    @Test(expected = ResponseStatusException.class)
    public void testValidate404ThrowsExceptionWhenOptionalIsEmpty() {
        Optional<String> emptyOptional = Optional.empty();
        String label = "First Name";
        String value = "Jacob";

        try {
            studentValidator.validate404(emptyOptional, label, value);
        } catch (ResponseStatusException e) {
            if (!e.getStatus().equals(HttpStatus.NOT_FOUND)) {
                throw new AssertionError("Unexpected HTTP status code", e);
            }
            throw e; // Re-throw to satisfy the expected exception
        }
    }

    @Test
    public void testValidate404DoesNotThrowExceptionWhenOptionalIsPresent() {
        Optional<String> presentOptional = Optional.of("Jacob");
        String label = "First Name";
        String value = "Jacob";

        // No exception should be thrown
        studentValidator.validate404(presentOptional, label, value);
    }
}