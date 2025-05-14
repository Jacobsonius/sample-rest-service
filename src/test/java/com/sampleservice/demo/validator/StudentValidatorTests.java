package com.sampleservice.demo.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class StudentValidatorTests {

    private StudentValidator studentValidator = new StudentValidator();

    @Test(expected = ResponseStatusException.class)
    public void testValidate404_ThrowsException() {
        Optional<String> optionalValue = Optional.empty();
        try {
            studentValidator.validate404(optionalValue, "First Name", "Jacob");
        } catch (ResponseStatusException e) {
            if (e.getStatusCode() != HttpStatus.NOT_FOUND) {
                throw e; // Re-throw if the exception does not match expected status
            }
            throw e; // Re-throw to satisfy @Test(expected = ...)
        }
    }

    @Test
    public void testValidate404_NoException() {
        Optional<String> optionalValue = Optional.of("Valid Value");
        studentValidator.validate404(optionalValue, "First Name", "Jacob");
    }
}