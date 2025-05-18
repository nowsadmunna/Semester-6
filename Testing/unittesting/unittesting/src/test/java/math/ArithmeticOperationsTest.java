package math;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArithmeticOperationsTest {

    private ArithmeticOperations operations;

    @Before
    public void setUp() {
        operations = new ArithmeticOperations();
    }

    //test for division
    @Test
    public void testDividePositiveNumbers() {
        assertEquals(2.0, operations.divide(10.0, 5.0), 0.001);
    }

    @Test
    public void testDivideNegativeNumerator() {
        assertEquals(-2.0, operations.divide(-10.0, 5.0), 0.001);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZeroThrowsException() {
        operations.divide(10.0, 0.0);
    }

    //test for multipy
    @Test
    public void testMultiplyPositiveNumbers() {
        assertEquals(20, operations.multiply(4, 5));
    }

    @Test
    public void testMultiplyByZero() {
        assertEquals(0, operations.multiply(0, 5));
        assertEquals(0, operations.multiply(5, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyWithNegativeXThrowsException() {
        operations.multiply(-1, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyWithNegativeYThrowsException() {
        operations.multiply(5, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyOverflowThrowsException() {
        operations.multiply(Integer.MAX_VALUE, 2);
    }
}
