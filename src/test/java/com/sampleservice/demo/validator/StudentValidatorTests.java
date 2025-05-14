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

    private final StudentValidator studentValidator = new StudentValidator();

    @Test(expected = ResponseStatusException.class)
    public void testValidate404ThrowsExceptionWhenOptionalIsEmpty() {
        Optional<String> emptyOptional = Optional.empty();
        studentValidator.validate404(emptyOptional, "First Name", "Jacob");
    }

    @Test
    public void testValidate404DoesNotThrowExceptionWhenOptionalIsPresent() {
        Optional<String> presentOptional = Optional.of("SomeValue");
        studentValidator.validate404(presentOptional, "Label", "Value");
    }
}