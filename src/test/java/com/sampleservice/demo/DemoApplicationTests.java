package com.sampleservice.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
        // Verify that the application context loads successfully
    }

    @Test
    public void testMainMethod() {
        try {
            // Test the main method to ensure it runs without throwing exceptions
            String[] args = {};
            DemoApplication.main(args);
        } catch (Exception e) {
            // Handle any potential exceptions thrown by the main method
            throw new RuntimeException("Error during main method execution", e);
        }
    }
}