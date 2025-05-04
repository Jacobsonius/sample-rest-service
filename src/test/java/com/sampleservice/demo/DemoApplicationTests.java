package com.sampleservice.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
        ApplicationContext context = SpringApplication.run(DemoApplication.class, new String[] {});
        assertNotNull("Application context should not be null", context);
    }

    @Test
    public void testActuatorEndpoint() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.getForEntity("/actuator/health", String.class);
            assertNotNull("Response entity should not be null", response);
            assertEquals("Response status code should be OK", HttpStatus.OK, response.getStatusCode());
        } catch (Exception e) {
            fail("Unexpected exception occurred: " + e.getMessage());
        }
    }
}