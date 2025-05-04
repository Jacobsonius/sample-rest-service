package com.sampleservice.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
        // Verify that the application context loads successfully
        assertThat(true).isTrue();
    }

    @Test
    public void mainMethodExists() {
        // Verify that the main method exists and does not throw an exception
        assertThatCode(() -> DemoApplication.main(new String[0])).doesNotThrowAnyException();
    }

    @Test
    public void testSpringBootApplicationAnnotation() {
        // Verify that the DemoApplication class is annotated with @SpringBootApplication
        boolean hasSpringBootApplication = java.util.Arrays.stream(DemoApplication.class.getAnnotations())
                .anyMatch(annotation -> annotation.annotationType().equals(org.springframework.boot.autoconfigure.SpringBootApplication.class));
        assertThat(hasSpringBootApplication).isTrue();
    }
}