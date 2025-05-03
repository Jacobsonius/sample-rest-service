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
        // Verify that the application context loads successfully
    }

    @Test
    public void testMainMethod() {
        try {
            String[] args = {};
            DemoApplication.main(args);
        } catch (Exception e) {
            // Log or handle the exception if necessary
            throw new RuntimeException("Error during main method execution", e);
        }
    }
}