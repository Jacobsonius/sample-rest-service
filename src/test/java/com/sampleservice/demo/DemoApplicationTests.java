package com.sampleservice.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
        // This test ensures that the Spring Boot application context loads successfully.
        try {
            // No explicit method call is required here as SpringBootTest annotation handles context loading
        } catch (Exception e) {
            // Handle any unexpected exceptions during context loading
            throw new RuntimeException("Application context failed to load", e);
        }
    }

}