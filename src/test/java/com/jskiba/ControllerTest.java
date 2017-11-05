package com.jskiba;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        UserInterface ui = new UserInterface(printStream, fakeDataStream);

        Controller controller = new Controller();
        controller.start(ui);

        String programOutput = new String(byteArrayOutputStream.toString());

        assertEquals(expectedOutput, programOutput);
    }

    @Test
    void testNoNode() {
        List<String> testingData = new ArrayList<>();
        testingData.add("Warsaw Athens 100");
        testingData.add("Warsaw London");
        UserInterface mockedUI = mock(UserInterface.class);
        when(mockedUI.getData()).thenReturn(testingData);

        Throwable exception = assertThrows(InvalidInputException.class, () -> new Controller().start(mockedUI));
        assertEquals("No such node!", exception.getMessage());

    }

    @Test
    void testToLessWordAmount() {
        List<String> testingData = new ArrayList<>();
        testingData.add("Warsaw 100");
        testingData.add("Warsaw Warsaw");
        UserInterface mockedUI = mock(UserInterface.class);
        when(mockedUI.getData()).thenReturn(testingData);

        Throwable exception = assertThrows(InvalidInputException.class, () -> new Controller().start(mockedUI));
        assertEquals("Graph data should be in format: Node Node Distance!", exception.getMessage());
    }

    @Test
    void testToMuchWordAmount() {
        List<String> testingData = new ArrayList<>();
        testingData.add("Warsaw Amsterdam Prague 100");
        testingData.add("Warsaw Prague");
        UserInterface mockedUI = mock(UserInterface.class);
        when(mockedUI.getData()).thenReturn(testingData);

        Throwable exception = assertThrows(InvalidInputException.class, () -> new Controller().start(mockedUI));
        assertEquals("Graph data should be in format: Node Node Distance!", exception.getMessage());
    }


    @Test
    void testDistanceNotANumber() {
        List<String> testingData = new ArrayList<>();
        testingData.add("Warsaw Amsterdam dupa");
        testingData.add("Warsaw Amsterdam");
        UserInterface mockedUI = mock(UserInterface.class);
        when(mockedUI.getData()).thenReturn(testingData);

        Throwable exception = assertThrows(InvalidInputException.class, () -> new Controller().start(mockedUI));
        assertEquals("Distance should be positive integer! Given value dupa", exception.getMessage());
    }

    @Test
    void testDistanceNegativeNumber() {
        List<String> testingData = new ArrayList<>();
        testingData.add("Warsaw Amsterdam -1");
        testingData.add("Warsaw Amsterdam");
        UserInterface mockedUI = mock(UserInterface.class);
        when(mockedUI.getData()).thenReturn(testingData);

        Throwable exception = assertThrows(InvalidInputException.class, () -> new Controller().start(mockedUI));
        assertEquals("Distance should be positive integer! Given value -1", exception.getMessage());
    }

    @Test
    void testDistanceZero() {
        List<String> testingData = new ArrayList<>();
        testingData.add("Warsaw Amsterdam 0");
        testingData.add("Warsaw Amsterdam");
        UserInterface mockedUI = mock(UserInterface.class);
        when(mockedUI.getData()).thenReturn(testingData);

        Throwable exception = assertThrows(InvalidInputException.class, () -> new Controller().start(mockedUI));
        assertEquals("Distance should be positive integer! Given value 0", exception.getMessage());
    }

    @Test
    void testDistanceToLarge() {
        List<String> testingData = new ArrayList<>();
        testingData.add("Warsaw Amsterdam 2147483648");
        testingData.add("Warsaw Amsterdam");
        UserInterface mockedUI = mock(UserInterface.class);
        when(mockedUI.getData()).thenReturn(testingData);

        Throwable exception = assertThrows(InvalidInputException.class, () -> new Controller().start(mockedUI));
        assertEquals("Distance is to large! Given value: 2147483648", exception.getMessage());
    }

}