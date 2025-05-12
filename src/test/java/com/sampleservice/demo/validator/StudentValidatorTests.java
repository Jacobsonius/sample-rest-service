package com.sampleservice.demo.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentValidatorTests {

    @Autowired
    private StudentValidator studentValidator;

    @Test
    public void testValidate404_withEmptyOptional_throwsException() {
        Optional<String> emptyOptional = Optional.empty();
        String label = "First Name";
        String value = "Jacob";

        try {
            studentValidator.validate404(emptyOptional, label, value);
            fail("Expected ResponseStatusException to be thrown");
        } catch (ResponseStatusException exception) {
            assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
            assertEquals("class java.lang.String with First Name 'Jacob' does not exist.", exception.getReason());
        }
    }

    @Test
    public void testValidate404_withPresentOptional_doesNotThrowException() {
        Optional<String> presentOptional = Optional.of("Jacob");
        String label = "First Name";
        String value = "Jacob";

        studentValidator.validate404(presentOptional, label, value);
    }

    @Test
    public void contextLoads() {
        // Ensure the application context loads successfully
    }
}