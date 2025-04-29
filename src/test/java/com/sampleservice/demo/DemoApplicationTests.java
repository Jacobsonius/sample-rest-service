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
        // Test that the main method initializes without throwing exceptions
        DemoApplication.main(new String[] {});
    }
}