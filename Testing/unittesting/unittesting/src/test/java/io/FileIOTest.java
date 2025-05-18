package io;

import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileIOTest {

    private FileIO fileIO;

    @Before
    public void setUp() {
        fileIO = new FileIO();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFileNotFound() {
        fileIO.readFile("non_existing_file.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyFile() throws IOException {
        fileIO.readFile("E:/emester-6/Testing/unittesting/unittesting/src/test/resources/empty_file.txt");
    }

    @Test
    public void testValidNumbers() throws IOException {
        int[] expected = {3,9,0,2,10,9,3,8,0,3};
        assertArrayEquals(expected, fileIO.readFile("E:/Semester-6/Testing/unittesting/unittesting/src/test/resources/grades_valid.txt"));
    }

    @Test
    public void testFileWithInvalidLines() throws IOException {
        int[] expected = {3,9,2,10,8,0,3};
        assertArrayEquals(expected, fileIO.readFile("E:/Semester-6/Testing/unittesting/unittesting/src/test/resources/grades_invalid.txt"));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFileWithAllInvalidLines() throws IOException {
        fileIO.readFile("E:/Semester-6/Testing/unittesting/unittesting/src/test/resources/grades_all_invalid.txt");
    }
}
