package io;

import static org.junit.Assert.*;
import org.junit.*;
import org.mockito.Mockito;

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
        fileIO.readFile("/home/mdnowsadhossenmunna/Desktop/Semester-6/Testing/unittesting/src/test/resources/empty_file.txt");
    }

    @Test
    public void testValidNumbers() throws IOException {
        int[] expected = {3,9,0,2,10,9,3,8,0,3};
        assertArrayEquals(expected, fileIO.readFile("/home/mdnowsadhossenmunna/Desktop/Semester-6/Testing/unittesting/src/test/resources/grades_valid.txt"));
    }

    @Test
    public void testFileWithInvalidLines() throws IOException {
        int[] expected = {3,9,2,10,8,0,3};
        assertArrayEquals(expected, fileIO.readFile("/home/mdnowsadhossenmunna/Desktop/Semester-6/Testing/unittesting/src/test/resources/grades_invalid.txt"));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFileWithAllInvalidLines() throws IOException {
        fileIO.readFile("/home/mdnowsadhossenmunna/Desktop/Semester-6/Testing/unittesting/src/test/resources/grades_all_invalid.txt");
    }
    @Test
    public void test_ReadFile_IOException() throws IOException {
        FileIO fileIO = new FileIO();

        // Create a temporary file and make it unreadable
        File tempFile = File.createTempFile("temp_unreadable", ".txt");
        tempFile.setReadable(false);

        try {
            fileIO.readFile(tempFile.getAbsolutePath());
        } catch (Exception e) {
            // We don't care about the exception here; just want to reach printStackTrace
        } finally {
            // Cleanup
            tempFile.setReadable(true);
            tempFile.delete();
        }
    }

    @Test
    public void test_ReadFile_IOException_PrintStackTrace_Captured() throws IOException {
        FileIO fileIO = new FileIO();

        // Create a temporary unreadable file
        File tempFile = File.createTempFile("temp_unreadable", ".txt");
        tempFile.setReadable(false);

        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(errContent));

        try {
            fileIO.readFile(tempFile.getAbsolutePath());
        } catch (Exception e) {

        } finally {
            System.setErr(originalErr);
            tempFile.setReadable(true);
            tempFile.delete();
        }

        String output = errContent.toString();
        assertTrue(output.contains("java.io.IOException") || output.contains("Exception"));
    }
}

