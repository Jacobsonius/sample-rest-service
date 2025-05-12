package com.sampleservice.demo.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class StudentValidatorTests {

    @InjectMocks
    private StudentValidator studentValidator;

    @Test(expected = ResponseStatusException.class)
    public void testValidate404_ThrowsResponseStatusException() {
        Optional<String> optionalValue = Optional.empty();
        try {
            studentValidator.validate404(optionalValue, "First Name", "Jacob");
        } catch (ResponseStatusException e) {
            assert e.getStatusCode() == HttpStatus.NOT_FOUND;
            assert e.getReason().equals("java.util.Optional with First Name'Jacob' does not exist.");
            throw e;
        }
    }

    @Test
    public void testValidate404_DoesNotThrowException() {
        Optional<String> optionalValue = Optional.of("ValidValue");
        studentValidator.validate404(optionalValue, "First Name", "Jacob");
    }
}