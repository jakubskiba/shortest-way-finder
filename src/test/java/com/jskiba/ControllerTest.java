package com.jskiba;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    @Test
    void systemTest() throws InvalidInputException {
        String testData = "Warsaw Paris 100\n" +
                "Paris Lisboa 300\n" +
                "Lisboa London 250\n" +
                "Warsaw London 450\n" +
                "London Athens 500\n" +
                "Lisboa Athens 800\n" +
                "Warsaw Athens";

        String expectedOutput = "Route:\n" +
                "Warsaw\n" +
                "London\n" +
                "Athens\n" +
                "Distance:\n" +
                "950\n";

        InputStream fakeDataStream = new ByteArrayInputStream(testData.getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);

        Controller controller = new Controller();
        controller.start(printStream, fakeDataStream);

        String programOutput = new String(byteArrayOutputStream.toString());

        assertEquals(expectedOutput, programOutput);
    }
}