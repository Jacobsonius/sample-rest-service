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
            // Simulate context loading check
            DemoApplication.main(new String[]{});
        } catch (Exception e) {
            // Handle any unexpected exceptions during context load
            throw new RuntimeException("Application context failed to load", e);
        }
    }

}