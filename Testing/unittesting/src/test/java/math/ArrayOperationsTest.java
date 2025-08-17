package math;

import io.FileIO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ArrayOperationsTest {

    private FileIO mockFileIO;
    private MyMath mockMyMath;
    private ArrayOperations arrayOps;

    @Before
    public void setUp() {
        mockFileIO = mock(FileIO.class);
        mockMyMath = mock(MyMath.class);
        arrayOps = new ArrayOperations();
    }

    @Test
    public void testFindPrimesInFile() {
        String dummyPath = "numbers.txt";
        int[] numbers = {2, 3, 4, 5, 6, 7};
        when(mockFileIO.readFile(dummyPath)).thenReturn(numbers);
        when(mockMyMath.isPrime(2)).thenReturn(true);
        when(mockMyMath.isPrime(3)).thenReturn(true);
        when(mockMyMath.isPrime(4)).thenReturn(false);
        when(mockMyMath.isPrime(5)).thenReturn(true);
        when(mockMyMath.isPrime(6)).thenReturn(false);
        when(mockMyMath.isPrime(7)).thenReturn(true);

        int[] result = arrayOps.findPrimesInFile(mockFileIO, dummyPath, mockMyMath);

        assertArrayEquals(new int[]{2, 3, 5, 7}, result);
        verify(mockMyMath).isPrime(2);
        verify(mockMyMath).isPrime(3);
        verify(mockMyMath).isPrime(4);
        verify(mockMyMath).isPrime(5);
        verify(mockMyMath).isPrime(6);
        verify(mockMyMath).isPrime(7);
    }
}
