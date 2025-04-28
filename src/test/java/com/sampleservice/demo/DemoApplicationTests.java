package com.sampleservice.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void contextLoads() {
        // Ensure the application context is not null
        assertNotNull("Application Context should not be null", applicationContext);
    }

    @Test
    public void testKeyBeansArePresent() {
        // Verify that specific beans are present in the application context
        assertNotNull("WebApplicationContext bean should be available", applicationContext.getBean("webApplicationContext"));
        assertNotNull("dataSource bean should be available", applicationContext.getBean("dataSource"));
    }
}