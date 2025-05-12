package com.sampleservice.demo.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class MiscTests {

    @Test
    public void testSum() {
        assertEquals(5, Misc.sum(2, 3));
        assertEquals(-1, Misc.sum(-5, 4));
    }

    @Test(expected = RuntimeException.class)
    public void testDivideByZero() {
        Misc.divide(10, 0);
    }

    @Test
    public void testDivide() {
        assertEquals(2.0, Misc.divide(10, 5), 0.001);
        assertEquals(3.0, Misc.divide(9, 3), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsColorSupportedWithNull() {
        Misc.isColorSupported(null);
    }

    @Test
    public void testIsColorSupported() {
        assertTrue(Misc.isColorSupported(Misc.Color.RED));
        assertTrue(Misc.isColorSupported(Misc.Color.YELLOW));
        assertTrue(Misc.isColorSupported(Misc.Color.BLUE));
    }

    @Test
    public void testCalculateFactorial() {
        assertEquals(1, Misc.calculateFactorial(0));
        assertEquals(6, Misc.calculateFactorial(3));
        assertEquals(120, Misc.calculateFactorial(5));
    }

    @Test
    public void testIsPrime() {
        assertTrue(Misc.isPrime(2, 2));
        assertTrue(Misc.isPrime(3, 2));
        assertFalse(Misc.isPrime(4, 2));
        assertTrue(Misc.isPrime(5, 2));
    }

    @Test
    public void testIsEven() {
        assertTrue(Misc.isEven(2));
        assertFalse(Misc.isEven(3));
        assertTrue(Misc.isEven(0));
    }
}