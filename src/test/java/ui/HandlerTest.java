package ui;

import health.HealthList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constants.ErrorConstant;
import workouts.WorkoutList;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HandlerTest {
    private final ByteArrayInputStream inContent = new ByteArrayInputStream("".getBytes());
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
        System.setErr(originalErr);
        WorkoutList.clearWorkoutsRunGym();
        HealthList.clearHealthLists();
    }

    /**
     * Checks whether the Scanner has been closed after each JUnit test to prevent overwriting of test input for each
     * test.
     *
     * @param in Scanner object from Handler.
     * @return True if the scanner is closed. Otherwise, return false.
     */
    public static boolean isScannerClosed(Scanner in) {
        try {
            in.hasNext();
            return false;
        } catch (IllegalStateException e) {
            return true;
        }
    }

    /**
     * Tests the processInput function's behaviour to the EXIT command.
     */
    @Test
    void processInput_exitCommand_terminatesProgram() {
        String input = "EXIT";
        Handler myHandler = new Handler(input);
        myHandler.processInput();
        String output = outContent.toString();
        assertTrue(output.contains("Initiating PulsePilot landing sequence..."));
    }

    /**
     * Tests the processInput function's behaviour to the NEW command to add a Run
     * object.
     */
    @Test
    void processInput_workoutCommand_addRunExercise() {
        String input = "WORKOUT /e:run /d:10.30 /t:40:10 /date:15-03-2024";
        Handler myHandler = new Handler(input);
        myHandler.processInput();
        String output = outContent.toString();
        System.out.println(output);
        assertTrue(output.contains("Successfully added a new run session"));
    }

    /**
     * Tests the processInput function's behaviour to the HEALTH command to add
     * a BMI object.
     */
    @Test
    void processInput_healthCommand_addBMIHealthData() {
        String input = "HEALTH /h:bmi /height:1.70 /weight:65.00 /date:15-03-2024";
        Handler myHandler = new Handler(input);
        myHandler.processInput();
        String output = outContent.toString();
        assertTrue(output.contains("Added: bmi | 1.70 | 65.00 | 2024-03-15"));
    }


    /**
     * Tests the processInput function's behaviour to the HISTORY command to print
     * all run objects.
     */
    @Test
    void processInput_historyCommand_printsHistoryRun() {
        StringBuilder inputRun = new StringBuilder();
        inputRun.append("WORKOUT /e:run /d:10.3 /t:00:40:10 /date:15-03-2024");
        inputRun.append(System.lineSeparator());
        inputRun.append("HISTORY /item:run");
        Handler myHandler = new Handler(inputRun.toString());
        myHandler.processInput();
        String output = outContent.toString();
        assertTrue(output.contains("Your run history"));
    }

    /**
     * Tests the processInput function's behaviour to the LATEST command to print
     * the latest run object.
     */
    @Test
    void processInput_latestCommand_printsLatestRun() {
        String inputRun = "WORKOUT /e:run /d:10.30 /t:40:10 /date:15-03-2024"
                + System.lineSeparator()
                + "LATEST /item:run";
        Handler myHandler = new Handler(inputRun);
        myHandler.processInput();
        String output = outContent.toString();
        assertTrue(output.contains("Your latest run:"));
    }

    /**
     * Tests the processInput function's behaviour to the HELP command to print
     * the help message.
     */
    @Test
    void processInput_helpCommand_printsHelp() {
        String input = "HELP";
        Handler myHandler = new Handler(input);
        myHandler.processInput();
        String output = outContent.toString();
        assertTrue(output.contains("Commands List"));
    }


    /**
     * Tests the processInput function's behaviour to an invalid command, which prints
     * an error.
     */
    @Test
    void processInput_invalidCommand_printsInvalidCommandException() {
        String input = "INVALID";
        Handler myHandler = new Handler(input);
        myHandler.processInput();
        String expected = "Exception Caught!" +
                System.lineSeparator() +
                ErrorConstant.INVALID_COMMAND_ERROR +
                System.lineSeparator();
        assertEquals(expected, errContent.toString());
    }

    /**
     * Tests the processInput function's behaviour when the HEALTH command is given with invalid parameters.
     */
    @Test
    void processInput_healthCommand_insufficientParameters() {
        String input = "HEALTH /h:bmi /height:1.70";
        Handler myHandler = new Handler(input);
        myHandler.processInput();
        assertTrue(errContent.toString().contains(ErrorConstant.INSUFFICIENT_BMI_PARAMETERS_ERROR));
    }
}
