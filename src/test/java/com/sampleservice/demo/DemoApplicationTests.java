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
        // This test verifies that the Spring application context loads successfully.
        try {
            // No explicit method calls are needed here as SpringBootTest annotation 
            // automatically triggers the loading of the application context.
        } catch (Exception ex) {
            // Handle any unexpected exceptions that might occur during context loading
            throw new RuntimeException("Application context failed to load", ex);
        }
    }
}