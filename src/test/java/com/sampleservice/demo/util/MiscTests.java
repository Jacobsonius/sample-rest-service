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

    @Test(expected = IllegalArgumentException.class)
    public void testIsColorSupportedNull() {
        Misc.isColorSupported(null);
    }

    @Test
    public void testIsColorSupported() {
        assertTrue(Misc.isColorSupported(Misc.Color.RED));
        assertFalse(Misc.isColorSupported(Misc.Color.values()[Misc.Color.values().length]));
    }

    @Test
    public void testCalculateFactorial() {
        assertEquals(120, Misc.calculateFactorial(5));
        assertEquals(1, Misc.calculateFactorial(0));
    }

    @Test
    public void testIsPrime() {
        assertTrue(Misc.isPrime(7, 2));
        assertFalse(Misc.isPrime(4, 2));
    }

    @Test
    public void testIsEven() {
        assertTrue(Misc.isEven(4));
        assertFalse(Misc.isEven(5));
    }
}