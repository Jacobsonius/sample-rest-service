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
public class StudentValidatorTests {

    private final StudentValidator studentValidator = new StudentValidator();

    @Test(expected = ResponseStatusException.class)
    public void validate404_WhenOptionalIsEmpty_ShouldThrowResponseStatusException() {
        Optional<String> emptyOptional = Optional.empty();
        studentValidator.validate404(emptyOptional, "First Name", "Jacob");
    }

    @Test
    public void validate404_WhenOptionalIsPresent_ShouldNotThrowException() {
        Optional<String> presentOptional = Optional.of("SomeValue");
        try {
            studentValidator.validate404(presentOptional, "Label", "Value");
        } catch (ResponseStatusException e) {
            throw new AssertionError("Exception should not have been thrown for a present Optional", e);
        }
    }
}