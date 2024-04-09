package ui;

import constants.WorkoutConstant;
import health.HealthList;
import health.Period;
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
import java.util.List;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
    public void tearDown(){
        WorkoutList.clearWorkoutsRunGym();
        HealthList.clearHealthLists();
        outContent.reset();
        errContent.reset();
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
            ArrayList<Double> array1 = new ArrayList<>(List.of(1.0));
            ArrayList<Double> array2 = new ArrayList<>(Arrays.asList(1.0, 2.0));
            newGym.addStation("ExerciseA", 1, 10, array1);
            newGym.addStation("ExerciseB", 2, 20 , array2);

            // Save the expected output
            Output output = new Output();
            output.printAddGym(newGym);
            String expectedString = outContent.toString();

            // Save the string, clear the static list, and then simulate load
            String saveString = newGym.toFileString();
            tearDown();
            Parser parser = new Parser();
            Gym loadedGym = parser.parseGymFileInput(saveString);
            output.printAddGym(loadedGym);
            String outputContent = outContent.toString();

            // Expect the same value
            assertEquals(expectedString, outputContent);


        } catch (CustomExceptions.InvalidInput | CustomExceptions.FileReadError | CustomExceptions.InsufficientInput e){
            fail("Should not throw an exception");
        }

    }

    /**
     * Tests if the output of the bot when adding runs and gyms, using history and latest commands is correct.
     * Two gyms and runs are added, followed by the history and latest commands to view it.
     */
    @Test
    void testLatestDisplay_userInputsTwoGymAndRuns_expectsLatestGymAndRun(){
        String run1 = "WORKOUT /e:run /d:10.30 /t:40:10 /date:15-03-2024";
        String run2 = "WORKOUT /e:run /d:11.59 /t:30:10 /date:17-03-2024";
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


        String inputString = run1 +System.lineSeparator() 
                + run2 +System.lineSeparator() 
                + gym1 + System.lineSeparator() 
                + gym1Station1 + System.lineSeparator() 
                + gym1Station2 + System.lineSeparator() 
                + gym2 + System.lineSeparator() 
                + gym2Station1 + System.lineSeparator() 
                + showLatestGym + System.lineSeparator() 
                + showLatestRun + System.lineSeparator() 
                + showHistoryGym + System.lineSeparator() 
                + showHistoryRun + System.lineSeparator() 
                + showHistoryAll + System.lineSeparator();

        Handler newHandler = new Handler(inputString);
        newHandler.processInput();
        String result = outContent.toString();
        tearDown();

        // Craft expected output
        try{
            Run run1Expected = new Run("40:10", "10.30", "15-03-2024");
            Run run2Expected = new Run("30:10", "11.59", "17-03-2024");

            Gym gym1expected = new Gym("18-03-2024");
            gym1expected.addStation("benchpress", 2, 4,
                    new ArrayList<>(Arrays.asList(40.0,60.0)));
            gym1expected.addStation("squats", 3, 4,
                    new ArrayList<>(Arrays.asList(10.0,20.0,30.0)));

            Gym gym2expected = new Gym("22-03-2024");
            gym2expected.addStation("deadlift",  4, 4,
                    new ArrayList<>(Arrays.asList(120.0,130.0,140.0,160.0)));

            Output output = new Output();
            output.printAddRun(run1Expected);
            output.printAddRun(run2Expected);
            output.printGymStationPrompt(1);
            output.printGymStationPrompt(2);
            output.printAddGym(gym1expected);
            output.printGymStationPrompt(1);
            output.printAddGym(gym2expected);
            output.printLatestGym();
            output.printLatestRun();
            output.printHistory(WorkoutConstant.GYM);
            output.printHistory(WorkoutConstant.RUN);
            output.printHistory(WorkoutConstant.ALL);

            String expected = outContent.toString();
            assertEquals(expected, result);

        } catch (CustomExceptions.InvalidInput e) {
            fail("Shouldn't have failed");
        }
    }

    /**
     * Tests the behaviour of the bot when 4 Period objects are added, expects the four periods to be reflected
     * accordingly with a valid prediction on when the next cycle is.
     */
    @Test
    void testPrediction_userInputsFourPeriods_expectPrediction() throws CustomExceptions.InsufficientInput {
        String period1 = "health /h:period /start:18-12-2023 /end:26-12-2023";
        String period2 = "health /h:period /start:18-01-2024 /end:26-01-2024";
        String period3 = "health /h:period /start:21-02-2024 /end:28-02-2024";
        String period4 = "health /h:period /start:22-03-2024 /end:29-03-2024";
        String prediction = "health /h:prediction";

        String inputString = period1 + System.lineSeparator()
                + period2 +System.lineSeparator()
                + period3 + System.lineSeparator()
                + period4 + System.lineSeparator()
                + prediction + System.lineSeparator();


        Handler newHandler = new Handler(inputString);
        newHandler.processInput();
        String result = outContent.toString();
        tearDown();

        Output output = new Output();

        Period expectedPeriod1 = new Period("18-12-2023" , "26-12-2023");
        output.printAddPeriod(expectedPeriod1);

        Period expectedPeriod2 = new Period("18-01-2024" , "26-01-2024");
        output.printAddPeriod(expectedPeriod2);

        Period expectedPeriod3 = new Period("21-02-2024", "28-02-2024");
        output.printAddPeriod(expectedPeriod3);

        Period expectedPeriod4 = new Period("22-03-2024", "29-03-2024");
        output.printAddPeriod(expectedPeriod4);

        Parser parser = new Parser();
        parser.parsePredictionInput();

        String expected = outContent.toString();
        assertEquals(expected, result);

    }

    /**
     * Tests the behaviour of the bot when 3 Period objects are added and a prediction is attempted.
     * Expects an exception thrown for prediction since there are insufficient Period objects added.
     */
    @Test
    void testPrediction_userInputsThreePeriods_expectNoPredictionPrintedAndErrorMessagePrinted() {
        String period1 = "health /h:period /start:18-12-2023 /end:26-12-2023";
        String period2 = "health /h:period /start:18-01-2024 /end:26-01-2024";
        String period3 = "health /h:period /start:21-02-2024 /end:28-02-2024";
        String prediction = "health /h:prediction";

        String inputString = period1 +System.lineSeparator()
                + period2 + System.lineSeparator()
                + period3 + System.lineSeparator()
                + prediction + System.lineSeparator();

        Handler newHandler = new Handler(inputString);
        Output output = new Output();
        newHandler.processInput();
        String result = outContent.toString();
        String resultErr = errContent.toString();
        tearDown();

        Period expectedPeriod1 = new Period("18-12-2023" , "26-12-2023");
        output.printAddPeriod(expectedPeriod1);
        Period expectedPeriod2 = new Period("18-01-2024" , "26-01-2024");
        output.printAddPeriod(expectedPeriod2);
        Period expectedPeriod3 = new Period("21-02-2024", "28-02-2024");
        output.printAddPeriod(expectedPeriod3);

        String expected = outContent.toString();
        assertEquals(expected, result);

        // expect error message
        try {
            Parser parser = new Parser();
            parser.parsePredictionInput();
        } catch (CustomExceptions.InsufficientInput e) {
            output.printException(e.getMessage());
        }
        String expectedErr = errContent.toString();
        assertEquals(expectedErr, resultErr);
    }
}
