package ui;

import constants.UiConstant;
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
     * Tests the processInput function's behaviour to the HEALTH command to add
     * an appointment object.
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
     * Tests the processInput function's behaviour to the HISTORY command to print
     * all run objects.
     */
    @Test
    void processInput_historyCommand_printsHistoryRun() {
        String inputRun = "WORKOUT /e:run /d:10.3 /t:00:40:10 /date:15-03-2024" +
                System.lineSeparator() +
                "HISTORY /item:run";
        Handler myHandler = new Handler(inputRun);
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
        String expected = "\u001b[31mException Caught!" +
                System.lineSeparator() +
                ErrorConstant.INVALID_COMMAND_ERROR +
                "\u001b[0m" +
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

    /**
     * Tests the processInput function's behaviour when the DELETE command is given with valid BMI added.
     * Expects delete BMI message to be printed.
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
     * Tests the processInput function's behaviour when the DELETE command is given with no BMI objects added.
     * Expects error message to be printed.
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
     * Tests the processInput function's behaviour when the DELETE command is given with valid BMI added.
     * Expects delete BMI message to be printed.
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
     * Tests the processInput function's behaviour when the DELETE command is given with valid BMI added.
     * Expects delete BMI message to be printed.
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
     * Tests the processInput function's behaviour when the DELETE command is given with valid BMI added.
     * Expects delete BMI message to be printed.
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
     * Tests the processInput function's behaviour when the DELETE command is given with valid BMI added.
     * Expects delete BMI message to be printed.
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
     * Tests the processInput function's behaviour when the DELETE command is given with valid BMI added.
     * Expects delete BMI message to be printed.
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
     * Tests the processInput function's behaviour when the DELETE command is given with valid BMI added.
     * Expects delete BMI message to be printed.
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
     * Tests the behaviour of userInduction when valid username is entered.
     * Expects welcome greeting to be printed.
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
}
