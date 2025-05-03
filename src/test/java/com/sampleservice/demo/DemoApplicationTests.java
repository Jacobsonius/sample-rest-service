package com.sampleservice.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
        // Verify that the application context loads successfully
        assertThat(true).isTrue();
    }

    @Test
    public void testMainMethod() {
        // Mock static main method call to ensure it runs without exceptions
        try {
            java.lang.reflect.Method mainMethod = DemoApplication.class.getMethod("main", String[].class);
            String[] args = new String[0];
            mainMethod.invoke(null, (Object) args);
        } catch (Exception e) {
            org.junit.Assert.fail("Main method threw an exception: " + e.getMessage());
        }
    }
}