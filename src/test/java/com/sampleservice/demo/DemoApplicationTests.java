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
            // Test to ensure the main method is present and can be invoked.
            boolean mainMethodExists = DemoApplication.class.getMethod("main", String[].class) != null;
            org.junit.Assert.assertTrue("Main method should exist and be invokable", mainMethodExists);
        } catch (NoSuchMethodException e) {
            org.junit.Assert.fail("Main method is missing: " + e.getMessage());
        } catch (SecurityException e) {
            org.junit.Assert.fail("Security exception while accessing main method: " + e.getMessage());
        }
    }
}