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
        // This test verifies that the application context loads successfully.
    }

    @Test
    public void testMainMethod() {
        try {
            // Test to ensure the main method is present and can be invoked without exceptions.
            DemoApplication.main(new String[] {});
        } catch (Exception e) {
            // Fail the test if an exception is thrown during main method invocation.
            throw new RuntimeException("Main method failed with exception: " + e.getMessage(), e);
        }
    }
}