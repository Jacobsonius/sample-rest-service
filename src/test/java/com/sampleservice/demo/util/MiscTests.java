package com.sampleservice.demo.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class MiscTests {

    @Test
    public void testSum() {
        assertEquals(5, Misc.sum(2, 3));
    }

    @Test
    public void testDivide() {
        assertEquals(2.0, Misc.divide(4, 2), 0.001);
    }

    @Test(expected = RuntimeException.class)
    public void testDivideByZero() {
        Misc.divide(4, 0);
    }

    @Test
    public void testIsColorSupported() {
        assertTrue(Misc.isColorSupported(Misc.Color.RED));
        assertTrue(Misc.isColorSupported(Misc.Color.YELLOW));
        assertTrue(Misc.isColorSupported(Misc.Color.BLUE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsColorSupportedWithNull() {
        Misc.isColorSupported(null);
    }

    @Test
    public void testCalculateFactorial() {
        assertEquals(120, Misc.calculateFactorial(5));
        assertEquals(1, Misc.calculateFactorial(0));
    }

    @Test
    public void testIsPrime() {
        assertTrue(Misc.isPrime(7, 2));
        assertFalse(Misc.isPrime(8, 2));
    }

    @Test
    public void testIsEven() {
        assertTrue(Misc.isEven(4));
        assertFalse(Misc.isEven(5));
    }
}