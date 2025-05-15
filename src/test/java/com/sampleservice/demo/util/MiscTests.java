package com.sampleservice.demo.util;

import org.junit.Assert;
import org.junit.Test;

public class MiscTests {

    @Test
    public void testSum() {
        Assert.assertEquals(5, Misc.sum(2, 3));
    }

    @Test(expected = RuntimeException.class)
    public void testDivideByZero() {
        Misc.divide(10, 0);
    }

    @Test
    public void testDivide() {
        Assert.assertEquals(2.0, Misc.divide(10, 5), 0.001);
    }

    @Test
    public void testIsColorSupported() {
        Assert.assertTrue(Misc.isColorSupported(Misc.Color.RED));
        
        // Correcting the test to handle the IllegalArgumentException thrown by Assert.isTrue
        try {
            Misc.isColorSupported(null);
            Assert.fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("color cannot be null", e.getMessage());
        }
    }

    @Test
    public void testCalculateFactorial() {
        Assert.assertEquals(120, Misc.calculateFactorial(5));
        Assert.assertEquals(1, Misc.calculateFactorial(0));
    }

    @Test
    public void testIsPrime() {
        Assert.assertTrue(Misc.isPrime(7, 2));
        Assert.assertFalse(Misc.isPrime(10, 2));
    }

    @Test
    public void testIsEven() {
        Assert.assertTrue(Misc.isEven(4));
        Assert.assertFalse(Misc.isEven(3));
    }
}