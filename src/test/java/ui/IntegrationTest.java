package ui;

import constants.WorkoutConstant;
import health.HealthList;
import utility.CustomExceptions;
import utility.Parser;
import workouts.Gym;
import workouts.Run;
import workouts.WorkoutList;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class IntegrationTest {


    private static final ByteArrayInputStream inContent = new ByteArrayInputStream("".getBytes());
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static final InputStream originalIn = System.in;
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;

    @BeforeAll
    public static void setUpStreams() {
        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void cleanup() {
        WorkoutList.clearWorkoutsRunGym();
        HealthList.clearHealthLists();
        outContent.reset();
        Handler.destroyScanner();
        if (Handler.in == null) {
            return;
        }
        assert HandlerTest.isScannerClosed(Handler.in) : "Scanner is not closed";
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setIn(originalIn);
    }

    /**
     * Tests the behaviour of having the same expected output when saving and loading a Gym object.
     * This is tested by ensuring the print history of the Gym object is the same before and after saving and loading.
     */
    @Test
    void testSaveAndLoadGym_gymObjectInput_expectSamePrintHistory(){
        Gym newGym = new Gym();
        try {
            ArrayList<Integer> array1 = new ArrayList<>(Arrays.asList(1));
            ArrayList<Integer> array2 = new ArrayList<>(Arrays.asList(1, 2));
            newGym.addStation("Exercise 1", 1, 10, array1);
            newGym.addStation("Exercise 2", 2, 20 , array2);

            // Save the expected output
            Output.printAddGym(newGym);
            String expectedString = outContent.toString();

            // Save the string, clear the static list, and then simulate load
            String saveString = newGym.toFileString();
            cleanup();
            Gym loadedGym = Parser.parseGymFileInput(saveString);
            Output.printAddGym(loadedGym);
            String output = outContent.toString();

            // Expect the same value
            assertEquals(expectedString, output);


        } catch (CustomExceptions.InvalidInput | CustomExceptions.FileReadError e){
            fail("Should not throw an exception");
        }

    }

    /**
     * Test to check if user inputs when creating run and gym objects is properly reflected in the output
     * Test this by checking if Latest gym/run and History gym/run is properly displayed.
     */
    @Test
    void testLatestDisplay_userInputsTwoGymAndRuns_expectsLatestGymAndRun(){
        String run1 = "WORKOUT /e:run /d:10.30 /t:40:10 /date:15-03-2024";
        String run2 = "WORKOUT /e:run /d:40.30 /t:30:10 /date:17-03-2024";
        String gym1 = "WORKOUT /e:gym /n:2 /date:18-03-2024";
        String gym1Station1 = "benchpress /s:2 /r:4 /w:40,60";
        String gym1Station2 = "squats /s:3 /r:4 /w:10,20,30";
        String gym2 = "WORKOUT /e:gym /n:1 /date:22-03-2024";
        String gym2Station1 = "deadlift /s:4 /r:4 /w:120,130,140,160";
        String showLatestGym = "LATEST /item:gym";
        String showLatestRun = "LATEST /item:run";
        String showHistoryGym = "HISTORY /item:gym";
        String showHistoryRun = "HISTORY /item:run";
        String showHistoryAll = "HISTORY /item:workouts";


        StringBuilder input = new StringBuilder();
        input.append(run1).append(System.lineSeparator());
        input.append(run2).append(System.lineSeparator());
        input.append(gym1).append(System.lineSeparator());
        input.append(gym1Station1).append(System.lineSeparator());
        input.append(gym1Station2).append(System.lineSeparator());
        input.append(gym2).append(System.lineSeparator());
        input.append(gym2Station1).append(System.lineSeparator());
        input.append(showLatestGym).append(System.lineSeparator());
        input.append(showLatestRun).append(System.lineSeparator());
        input.append(showHistoryGym).append(System.lineSeparator());
        input.append(showHistoryRun).append(System.lineSeparator());
        input.append(showHistoryAll).append(System.lineSeparator());
        String inputString = input.toString();

        System.setIn(new ByteArrayInputStream(inputString.getBytes()));
        Handler.initialiseScanner();
        Handler.processInput();
        String output = outContent.toString();

        cleanup();

        // Craft expected output
        try{
            Run run1Expected = new Run("40:10", "10.30", "15-03-2024");
            Run run2Expected = new Run("30:10", "40.30", "17-03-2024");

            Gym gym1expected = new Gym("18-03-2024");
            gym1expected.addStation("benchpress", 2, 4,
                    new ArrayList<>(Arrays.asList(40,60)));
            gym1expected.addStation("squats", 3, 4,
                    new ArrayList<>(Arrays.asList(10,20,30)));

            Gym gym2expected = new Gym("22-03-2024");
            gym2expected.addStation("deadlift",  4, 4,
                    new ArrayList<>(Arrays.asList(120,130,140,160)));

            Output.printAddRun(run1Expected);
            Output.printAddRun(run2Expected);
            Output.printGymStationPrompt(1);
            Output.printGymStationPrompt(2);
            Output.printAddGym(gym1expected);
            Output.printGymStationPrompt(1);
            Output.printAddGym(gym2expected);
            Output.printLatestGym();
            Output.printLatestRun();
            Output.printHistory(WorkoutConstant.GYM);
            Output.printHistory(WorkoutConstant.RUN);
            Output.printHistory(WorkoutConstant.ALL);

            String expected = outContent.toString();
            assertEquals(expected, output);

        } catch (CustomExceptions.InvalidInput e) {
            fail("Shouldn't have failed");
        }
    }
}
