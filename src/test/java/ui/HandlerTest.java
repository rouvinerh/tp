package ui;

import constants.UiConstant;
import health.HealthList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import constants.ErrorConstant;
import workouts.WorkoutLists;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the functionality of the `Handler` class.
 * It includes tests for processing various user inputs and verifying the expected output.
 */
class HandlerTest {
    private final ByteArrayInputStream inContent = new ByteArrayInputStream("".getBytes());
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    /**
     * Sets up the test environment by redirecting the standard input, output, and error streams.
     */
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);
        System.setErr(new PrintStream(errContent));
    }

    /**
     * Restores the original standard input, output, and error streams, and cleans up the
     * `WorkoutLists` and `HealthList` after each test.
     */
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
        System.setErr(originalErr);
        WorkoutLists.clearWorkoutsRunGym();
        HealthList.clearHealthLists();
    }

    /**
     * Tests the `processInput` function's behaviour when the user enters the 'EXIT' command.
     * Verifies that the program terminates.
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
     * Tests the `processInput` function's behaviour when the user enters the 'WORKOUT' command
     * to add a new run exercise.
     * Verifies that the run is added successfully.
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
     * Tests the `processInput` function's behaviour when the user enters the 'HEALTH' command
     * to add a new BMI data point.
     * Verifies that the BMI data is added successfully.
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
     * Tests the `processInput` function's behaviour when the user enters the 'HEALTH' command
     * to add a new appointment.
     * Verifies that the appointment is added successfully.
     */
    @Test
    void processInput_healthCommand_addAppointment() {
        String input = "health /h:appointment /date:30-03-2024 /time:19:30 /description:test";
        Handler myHandler = new Handler(input);
        myHandler.processInput();
        String output = outContent.toString();
        assertTrue(output.contains("Added: appointment | 2024-03-30 | 19:30 | test"));
    }


    /**
     * Tests the `processInput` function's behaviour when the user enters the 'HISTORY' command
     * to print the history of runs.
     * Verifies that the run history is printed correctly.
     */
    @Test
    void processInput_historyCommand_printsHistoryRun() {
        String inputRun = "WORKOUT /e:run /d:10.30 /t:40:10" +
                System.lineSeparator() +
                "HISTORY /item:run";
        Handler myHandler = new Handler(inputRun);
        myHandler.processInput();
        String output = outContent.toString();
        assertTrue(output.contains("Your run history"));
    }

    /**
     * Tests the `processInput` function's behaviour when the user enters the 'LATEST' command
     * to print the latest run.
     * Verifies that the latest run is printed correctly.
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
     * Tests the `processInput` function's behaviour when the user enters the 'HELP' command.
     * Verifies that the help message is printed correctly.
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
     * Tests the `processInput` function's behaviour when the user enters an invalid command.
     * Verifies that an error message is printed.
     */
    @Test
    void processInput_invalidCommand_printsInvalidCommandException() {
        String input = "INVALID";
        Handler handler = new Handler(input);
        handler.processInput();
        String expected = "\u001b[31mException Caught!" +
                System.lineSeparator() +
                ErrorConstant.INVALID_COMMAND_ERROR +
                "\u001b[0m" +
                System.lineSeparator();
        assertEquals(expected, errContent.toString());
    }

    /**
     * Tests the `processInput` function's behaviour when the user enters an invalid run command
     * with an invalid distance.
     * Verifies that an error message is printed.
     */
    @Test
    void processInput_invalidRunCommand_printsInvalidDistanceError() {
        String input = "workout /e:run /t:22:11 /d:5";
        Handler handler = new Handler(input);
        handler.processInput();
        String expected = "\u001b[31mException Caught!" +
                System.lineSeparator() +
                "\u001b[31m" +
                "Invalid Input Exception: " +
                ErrorConstant.INVALID_RUN_DISTANCE_ERROR +
                "\u001b[0m\u001b[0m" +
                System.lineSeparator();
        assertEquals(expected, errContent.toString());
    }

    /**
     * Tests the `processInput` function's behaviour when the user enters the 'HEALTH' command
     * with insufficient parameters.
     * Verifies that an error message is printed.
     */
    @Test
    void processInput_healthCommand_insufficientParameters() {
        String input = "HEALTH /h:bmi /height:1.70";
        Handler myHandler = new Handler(input);
        myHandler.processInput();
        assertTrue(errContent.toString().contains(ErrorConstant.INSUFFICIENT_BMI_PARAMETERS_ERROR));
    }

    /**
     * Tests the `processInput` function's behaviour when the user enters the 'DELETE' command
     * with a valid BMI entry.
     * Verifies that the BMI entry is deleted successfully.
     */
    @Test
    void processInput_deleteCommandWithValidBmi_validDeleteMessage() {
        String input = "HEALTH /h:bmi /height:1.70 /weight:70.00 /date:09-04-2024"
                + System.lineSeparator()
                + "DELETE /item:bmi /index:1"
                + System.lineSeparator();
        Handler myHandler = new Handler(input);
        myHandler.processInput();
        assertTrue(outContent.toString().contains("Removed BMI entry"));
    }

    /**
     * Tests the `processInput` function's behaviour when the user enters the 'DELETE' command
     * with no BMI objects added.
     * Verifies that an error message is printed.
     */
    @Test
    void processInput_deleteCommandWithInvalidBmiIndex_expectErrorMessage() {
        String input = "HEALTH /h:bmi /height:1.70 /weight:70.00 /date:09-04-2024"
                + System.lineSeparator()
                + "DELETE /item:bmi /index:99"
                + System.lineSeparator();
        Handler myHandler = new Handler(input);
        myHandler.processInput();
        assertTrue(errContent.toString().contains("\u001b[31mException Caught!"));
    }

    /**
     * Tests the `processInput` function's behaviour when the user enters the 'DELETE' command
     * with a valid appointment.
     * Verifies that the appointment is deleted successfully.
     */
    @Test
    void processInput_deleteCommandWithValidAppointment_validDeleteMessage() {
        String input = "health /h:appointment /date:03-04-2024 /time:14:15 /description:review checkup with surgeon"
                + System.lineSeparator()
                + "DELETE /item:appointment /index:1"
                + System.lineSeparator();
        Handler myHandler = new Handler(input);
        myHandler.processInput();
        assertTrue(outContent.toString().contains("Removed appointment"));
    }

    /**
     * Tests the `processInput` function's behaviour when the user enters the 'DELETE' command
     * with an invalid appointment index.
     * Verifies that an error message is printed.
     */
    @Test
    void processInput_deleteCommandWithInvalidAppointmentIndex_validDeleteMessage() {
        String input = "health /h:appointment /date:03-04-2024 /time:14:15 /description:review checkup with surgeon"
                + System.lineSeparator()
                + "DELETE /item:appointment /index:99"
                + System.lineSeparator();
        Handler myHandler = new Handler(input);
        myHandler.processInput();
        assertTrue(errContent.toString().contains("\u001b[31mException Caught!"));
    }

    /**
     * Tests the `processInput` function's behaviour when the user enters the 'DELETE' command
     * with a valid run.
     * Verifies that the run is deleted successfully.
     */
    @Test
    void processInput_deleteCommandWithValidRun_validDeleteMessage() {
        String input = "workout /e:run /d:5.15 /t:25:03 /date:25-03-2023"
                + System.lineSeparator()
                + "DELETE /item:run /index:1"
                + System.lineSeparator();
        Handler myHandler = new Handler(input);
        myHandler.processInput();
        assertTrue(outContent.toString().contains("Removed Run"));
    }

    /**
     * Tests the `processInput` function's behaviour when the user enters the 'DELETE' command
     * with an invalid run index.
     * Verifies that an error message is printed.
     */
    @Test
    void processInput_deleteCommandWithInvalidRunIndex_validDeleteMessage() {
        String input = "workout /e:run /d:5.15 /t:25:03 /date:25-03-2023"
                + System.lineSeparator()
                + "DELETE /item:run /index:99"
                + System.lineSeparator();
        Handler myHandler = new Handler(input);
        myHandler.processInput();
        assertTrue(errContent.toString().contains("\u001b[31mException Caught!"));
    }

    /**
     * Tests the `processInput` function's behaviour when the user enters the 'DELETE' command
     * with a valid period.
     * Verifies that the period is deleted successfully.
     */
    @Test
    void processInput_deleteCommandWithValidPeriod_validDeleteMessage() {
        String input = "health /h:period /start:09-03-2022 /end:16-03-2022"
                + System.lineSeparator()
                + "DELETE /item:period /index:1"
                + System.lineSeparator();
        Handler myHandler = new Handler(input);
        myHandler.processInput();
        assertTrue(outContent.toString().contains("Removed period"));
    }

    /**
     * Tests the `processInput` function's behaviour when the user enters the 'DELETE' command
     * with an invalid period index.
     * Verifies that an error message is printed.
     */
    @Test
    void processInput_deleteCommandWithInvalidPeriodIndex_validDeleteMessage() {
        String input = "workout /e:run /d:5.15 /t:25:03 /date:25-03-2023"
                + System.lineSeparator()
                + "DELETE /item:run /index:99"
                + System.lineSeparator();
        Handler myHandler = new Handler(input);
        myHandler.processInput();
        assertTrue(errContent.toString().contains("\u001b[31mException Caught!"));
    }

    /**
     * Tests the `processInput` function's behaviour when the user enters the 'DELETE' command
     * with a valid gym.
     * Verifies that the gym is deleted successfully.
     */
    @Test
    void processInput_deleteCommandWithValidGym_validDeleteMessage() {
        String input = "workout /e:gym /n:1 /date:25-03-2023"
                + System.lineSeparator()
                + "bench press /s:2 /r:4 /w:10,20"
                + System.lineSeparator()
                + "DELETE /item:gym /index:1"
                + System.lineSeparator();
        Handler myHandler = new Handler(input);
        myHandler.processInput();
        assertTrue(outContent.toString().contains("Removed Gym"));
    }

    /**
     * Tests the `processInput` function's behaviour when the user enters the 'DELETE' command
     * with an invalid gym index.
     * Verifies that an error message is printed.
     */
    @Test
    void processInput_deleteCommandWithInvalidGymIndex_validDeleteMessage() {
        String input = "workout /e:gym /n:1 /date:25-03-2023"
                + System.lineSeparator()
                + "bench press /s:2 /r:4 /w:10,20"
                + System.lineSeparator()
                + "DELETE /item:gym /index:2"
                + System.lineSeparator();
        Handler myHandler = new Handler(input);
        myHandler.processInput();
        assertTrue(errContent.toString().contains("\u001b[31mException Caught!"));
    }

    /**
     * Tests the `processInput` function's behaviour when the user enters a gym command, adds one
     * station, and then types 'back' to exit.
     * Verifies that the gym is not added and a delete message is printed.
     */
    @Test
    void processInput_workoutCommandWithGymStationExit_expectsNoGymAddedAndDeleteMessage() {
        String input = "workout /e:gym /n:2 /date:25-03-2023"
                + System.lineSeparator()
                + "bench press /s:2 /r:4 /w:10,20"
                + System.lineSeparator()
                + "back"
                + System.lineSeparator();
        Handler myHandler = new Handler(input);
        myHandler.processInput();
        assertTrue(outContent.toString().contains("Removed Gym entry with 1 station(s)."));
    }

    /**
     * Tests the `userInduction` function's behaviour when the user enters a valid username.
     * Verifies that the welcome greeting is printed.
     */
    @Test
    void userInduction_validUsername_printGreeting() {
        String input = "john";
        Handler myHandler = new Handler(input);
        myHandler.userInduction();
        String expected = "Welcome aboard, Captain john"
                + System.lineSeparator()
                + UiConstant.PARTITION_LINE
                + System.lineSeparator()
                + "Tips: Enter 'help' to view the pilot manual!"
                + System.lineSeparator()
                + "Initiating FTL jump sequence..."
                + System.lineSeparator()
                + "FTL jump completed."
                + System.lineSeparator();
        assertEquals(outContent.toString(), expected);
    }

    /**
     * Tests the `handleWorkout` function's behaviour when an invalid string is passed.
     * Verifies that an error message is printed.
     */
    @Test
    void handleWorkout_invalidInput_expectsErrorMessagePrinted() {
        Handler myHandler = new Handler();
        myHandler.handleWorkout("boo");
        assertTrue(errContent.toString().contains("\u001b[31mException Caught!"));
    }

    /**
     * Tests the `handleHealth` function's behaviour when an invalid string is passed.
     * Verifies that an error message is printed.
     */
    @Test
    void handleHealth_invalidInput_expectsErrorMessagePrinted() {
        Handler myHandler = new Handler();
        myHandler.handleWorkout("boo");
        assertTrue(errContent.toString().contains("\u001b[31mException Caught!"));
    }
}
