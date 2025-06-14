package com.sampleservice.demo.validator;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class StudentValidatorTests {

    private final StudentValidator studentValidator = new StudentValidator();

    @Test
    public void testValidate404_withEmptyOptional_shouldThrowResponseStatusException() {
        Optional<String> emptyOptional = Optional.empty();
        String label = "First Name";
        String value = "Jacob";

        try {
            studentValidator.validate404(emptyOptional, label, value);
            fail("Expected ResponseStatusException to be thrown");
        } catch (ResponseStatusException exception) {
            // Verify the exception details
            assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
            assertEquals("class java.lang.String with First Name'Jacob' does not exist.", exception.getReason());
        }
    }

    @Test
    public void testValidate404_withPresentOptional_shouldNotThrowException() {
        Optional<String> presentOptional = Optional.of("Jacob");
        String label = "First Name";
        String value = "Jacob";

        try {
            studentValidator.validate404(presentOptional, label, value);
        } catch (Exception e) {
            fail("No exception should be thrown for a present Optional.");
        }
    }
}