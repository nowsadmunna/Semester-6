package math;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyMathTest {

    private MyMath myMath;

    @Before
    public void setUp() {
        myMath = new MyMath();
    }

    //test for factorial

    @Test
    public void testFactorialOf0() {
        assertEquals(1, myMath.factorial(0));
    }

    @Test
    public void testFactorialOf5() {
        assertEquals(120, myMath.factorial(5));
    }

    @Test
    public void testFactorialOf12() {
        assertEquals(479001600, myMath.factorial(12));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactorialNegative() {
        myMath.factorial(-3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactorialTooLarge() {
        myMath.factorial(13);
    }

    //test for prime

    @Test
    public void testPrimeNumber() {
        assertTrue(myMath.isPrime(7));
    }

    @Test
    public void testNonPrimeNumber() {
        assertFalse(myMath.isPrime(10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsPrimeWithInputLessThan2() {
        myMath.isPrime(1);
    }

    @Test
    public void testIsPrimeWithEdgeCase() {
        assertTrue(myMath.isPrime(2));
    }
}
