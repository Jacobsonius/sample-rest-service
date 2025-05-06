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
        try {
            // This test verifies that the Spring application context loads successfully.
        } catch (Exception e) {
            // Handle any unexpected exceptions during context loading
            throw new RuntimeException("Application context failed to load", e);
        }
    }
}