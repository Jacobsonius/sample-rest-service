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
        // Ensure the Spring application context loads successfully
    }

    @Test
    public void mainMethodExists() {
        // Test that the main method exists and does not throw an exception
        DemoApplication.main(new String[] {});
    }
}