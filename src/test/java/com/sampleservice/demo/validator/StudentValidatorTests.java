package com.sampleservice.demo.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.Optional;
import org.springframework.web.server.ResponseStatusException;

@RunWith(SpringRunner.class)
@SpringBootTest
class StudentValidatorTests {

    private final StudentValidator studentValidator = new StudentValidator();

    @Test
    public void testValidate404_WhenObjectIsPresent_DoesNotThrowException() {
        Optional<String> presentObject = Optional.of("TestValue");
        try {
            studentValidator.validate404(presentObject, "label", "value");
        } catch (ResponseStatusException e) {
            fail("Exception should not have been thrown for a present object.");
        }
    }

    @Test
    public void testValidate404_WhenObjectIsNotPresent_ThrowsResponseStatusException() {
        Optional<String> absentObject = Optional.empty();
        try {
            studentValidator.validate404(absentObject, "label", "value");
            fail("ResponseStatusException was expected but not thrown.");
        } catch (ResponseStatusException e) {
            String expectedMessage = "java.util.Optional with label'value' does not exist.";
            assertEquals(expectedMessage, e.getReason());
        }
    }
}