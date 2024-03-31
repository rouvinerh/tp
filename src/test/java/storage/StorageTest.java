package storage;

import health.HealthList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ui.Output;
import utility.CustomExceptions;
import utility.Parser;
import workouts.Gym;
import workouts.WorkoutList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void cleanup() {
        WorkoutList.clearWorkoutsRunGym();
        HealthList.clearHealthLists();
        outContent.reset();
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    /**
     * Tests the behaviour of having the same expected output when saving and loading a Gym object.
     * This is tested by ensuring the print history of the Gym object is the same before and after saving and loading.
     */
    @Test
    void shouldSaveAndLoadGym_gymObjectInput_expectSamePrintHistory(){
        Gym newGym = new Gym();
        try {
            ArrayList<Integer> array1 = new ArrayList<>(Arrays.asList(1));
            ArrayList<Integer> array2 = new ArrayList<>(Arrays.asList(1, 2));
            newGym.addStation("Exercise 1", array1, 1, 10);
            newGym.addStation("Exercise 2", array2, 2, 20);

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

}

