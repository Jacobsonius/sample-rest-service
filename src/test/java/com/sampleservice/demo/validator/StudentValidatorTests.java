package com.sampleservice.demo.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        // This test ensures that the application context loads successfully.
    }

    @Test(expected = ResponseStatusException.class)
    public void validate404_ThrowsExceptionWhenOptionalIsEmpty() {
        Optional<String> emptyOptional = Optional.empty();
        String label = "First Name";
        String value = "Jacob";

        studentValidator.validate404(emptyOptional, label, value);
    }

    @Test
    public void validate404_DoesNotThrowExceptionWhenOptionalIsPresent() {
        Optional<String> presentOptional = Optional.of("Jacob");
        String label = "First Name";
        String value = "Jacob";

        try {
            studentValidator.validate404(presentOptional, label, value);
        } catch (ResponseStatusException e) {
            throw new AssertionError("Exception should not be thrown when Optional is present.", e);
        }
    }
}