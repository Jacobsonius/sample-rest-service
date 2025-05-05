package com.sampleservice.demo.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MiscTests {

    @Test
    public void testSum() {
        int result = Misc.sum(3, 5);
        assertEquals(8, result);
    }

    @Test(expected = RuntimeException.class)
    public void testDivideByZero() {
        Misc.divide(10, 0);
    }

    @Test
    public void testDivideValid() {
        double result = Misc.divide(10, 2);
        assertEquals(5.0, result, 0.0);
    }

    @Test
    public void testIsColorSupported() {
        assertTrue(Misc.isColorSupported(Misc.Color.RED));
        try {
            Misc.isColorSupported(null);
            fail("Expected IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("color cannot be null", e.getMessage());
        }
    }

    @Test
    public void testCalculateFactorial() {
        assertEquals(120, Misc.calculateFactorial(5));
        assertEquals(1, Misc.calculateFactorial(0));
    }

    @Test
    public void testIsPrime() {
        assertTrue(Misc.isPrime(7, 2));
        assertFalse(Misc.isPrime(10, 2));
    }

    @Test
    public void testIsEven() {
        assertTrue(Misc.isEven(4));
        assertFalse(Misc.isEven(5));
    }
}