package com.sampleservice.demo.validator;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentValidatorTests {

    private StudentValidator studentValidator = new StudentValidator();

    @Test(expected = ResponseStatusException.class)
    public void testValidate404ThrowsException() {
        Optional<String> emptyObject = Optional.empty();
        studentValidator.validate404(emptyObject, "First Name", "Jacob");
    }

    @Test
    public void testValidate404Success() {
        Optional<String> presentObject = Optional.of("Jacob");
        studentValidator.validate404(presentObject, "First Name", "Jacob");
    }

}