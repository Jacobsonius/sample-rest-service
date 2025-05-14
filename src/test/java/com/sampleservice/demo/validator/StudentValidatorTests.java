package com.sampleservice.demo.validator;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class StudentValidatorTests {

    private final StudentValidator studentValidator = new StudentValidator();

    @Test
    public void testValidate404_withEmptyOptional_throwsResponseStatusException() {
        Optional<String> emptyOptional = Optional.empty();
        String label = "[First Name]";
        String value = "Jacob";

        try {
            studentValidator.validate404(emptyOptional, label, value);
            fail("Expected ResponseStatusException to be thrown");
        } catch (ResponseStatusException exception) {
            assertTrue(exception.getStatus().equals(HttpStatus.NOT_FOUND));
            assertTrue(exception.getReason().contains("Optional with [First Name]'Jacob' does not exist."));
        }
    }

    @Test
    public void testValidate404_withPresentOptional_doesNotThrowException() {
        Optional<String> presentOptional = Optional.of("Jacob");
        String label = "[First Name]";
        String value = "Jacob";

        try {
            studentValidator.validate404(presentOptional, label, value);
        } catch (ResponseStatusException exception) {
            fail("No exception should be thrown for a present Optional.");
        }
    }
}