package com.sampleservice.demo.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class MiscTests {

    @Test
    public void testSum() {
        int result = Misc.sum(2, 3);
        assertEquals(5, result);
    }

    @Test(expected = RuntimeException.class)
    public void testDivideByZero() {
        Misc.divide(10, 0);
    }

    @Test
    public void testDivide() {
        double result = Misc.divide(10, 2);
        assertEquals(5.0, result, 0.0);
    }

    @Test
    public void testIsColorSupported() {
        assertTrue(Misc.isColorSupported(Misc.Color.RED));
        try {
            assertFalse(Misc.isColorSupported(null));
        } catch (IllegalArgumentException e) {
            // Expected behavior due to Spring's Assert.isTrue
            assertNotNull(e);
        }
    }

    @Test
    public void testCalculateFactorial() {
        assertEquals(120, (long) Misc.calculateFactorial(5));
        assertEquals(1, (long) Misc.calculateFactorial(0));
        try {
            Misc.calculateFactorial(-1);
        } catch (StackOverflowError e) {
            // Expected behavior for negative input due to recursion
            assertNotNull(e);
        }
    }

    @Test
    public void testIsPrime() {
        assertTrue(Misc.isPrime(7, 2));
        assertFalse(Misc.isPrime(4, 2));
        assertFalse(Misc.isPrime(1, 1)); // Additional edge case
    }

    @Test
    public void testIsEven() {
        assertTrue(Misc.isEven(4));
        assertFalse(Misc.isEven(3));
    }
}