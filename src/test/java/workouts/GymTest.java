package workouts;

import constants.ErrorConstant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.CustomExceptions;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class GymTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void cleanup() {
        WorkoutLists.clearWorkoutsRunGym();
    }

    /**
     * Tests the behavior of adding a new station to the gym.
     * Verifies whether the newly added station is correctly reflected in {@Code GymSet}.
     * Expected Behaviour is to add stations and sets to the gym.
     */
    @Test
    void addStation_validInput_expectAddedStation() {
        Gym newGym = new Gym();
        try{

            newGym.addStation("ExerciseOne", "1", "10", "1.0");
            newGym.addStation("ExerciseTwo", "2", "20", "1.0,2.0");
            assertEquals(2, newGym.getStations().size());

            newGym.addStation("ExerciseThree", "3", "30", "1.0,2.0,3.0");
            ArrayList<GymStation> stations = newGym.getStations();
            assertEquals(3, stations.size());

            for(int i = 0; i < stations.size(); i++){
                String stationName = stations.get(i).getStationName();
                ArrayList<GymSet> sets = stations.get(i).getSets();
                int numberOfSets = sets.size();

                if (i == 0){
                    assertEquals("ExerciseOne", stationName);
                    assertEquals(1, numberOfSets );
                    assertEquals(1.0, sets.get(0).getWeight());
                    assertEquals(10, sets.get(0).getNumberOfRepetitions());

                } else if (i == 1){
                    assertEquals("ExerciseTwo", stationName);
                    assertEquals(2, numberOfSets );
                    assertEquals(1.0, sets.get(0).getWeight());
                    assertEquals(20, sets.get(0).getNumberOfRepetitions());
                    assertEquals(2.0, sets.get(1).getWeight());
                    assertEquals(20, sets.get(1).getNumberOfRepetitions());

                } else if (i == 2){
                    assertEquals("ExerciseThree", stationName);
                    assertEquals(3, numberOfSets );
                    assertEquals(1.0, sets.get(0).getWeight());
                    assertEquals(30, sets.get(0).getNumberOfRepetitions());
                    assertEquals(2.0, sets.get(1).getWeight());
                    assertEquals(30, sets.get(1).getNumberOfRepetitions());
                    assertEquals(3.0, sets.get(2).getWeight());
                    assertEquals(30, sets.get(2).getNumberOfRepetitions());

                }
            }

        } catch (Exception e) {
            fail("Should not throw an exception");
        }
    }


    // Test for toFileString method
    @Test
    void toFileString_correctInput_expectedCorrectString(){
        String expected1 = "GYM:2:11-11-1997:bench press:4:4:10.0,20.0,30.0,40.0:squats:4:3:20.0,30.0,40.0,50.0";
        String expected2WithNoDate = "GYM:2:NA:bench press:4:4:10.0,20.0,30.0,40.0:squats:4:3:20.0,30.0,40.0,50.0";

        try {
            Gym newGym = new Gym("11-11-1997");
            Gym newGym2 = new Gym();

            newGym.addStation("bench press", "4", "4", "10.0,20.0,30.0,40.0");
            newGym.addStation("squats", "4", "3", "20.0,30.0,40.0,50.0");
            newGym2.addStation("bench press", "4", "4", "10.0,20.0,30.0,40.0");
            newGym2.addStation("squats", "4", "3", "20.0,30.0,40.0,50.0");

            String output = newGym.toFileString();
            String output2 = newGym2.toFileString();
            assertEquals(expected1, output);
            assertEquals(expected2WithNoDate, output2);
        } catch (CustomExceptions.InvalidInput | CustomExceptions.InsufficientInput e) {
            fail("Should not throw an exception");
        }
    }

    // @@author rouvinerh
    /**
     * Tests the behaviour of valid exercise names being passed to validateExerciseName.
     * Expects no exceptions to be thrown.
     */
    @Test
    void validateExerciseName_correctName_noExceptionThrown() {
        String input1 = "Bench Press";
        Gym gym = new Gym();
        assertDoesNotThrow(() -> gym.validateGymStationName(input1));

        String input2 = "squat";
        assertDoesNotThrow(() -> gym.validateGymStationName(input2));
    }

    /**
     * Tests the behaviour of invalid exercise names being passed to validateExerciseName.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateExerciseName_invalidNames_expectsInvalidInputException() {
        Gym gym = new Gym();
        // numbers in name
        String input1 = "bench1";
        assertThrows(CustomExceptions.InvalidInput.class, () -> gym.validateGymStationName(input1));

        // special characters in name
        String input2 = "bench-;";
        assertThrows(CustomExceptions.InvalidInput.class, () -> gym.validateGymStationName(input2));

        // special characters in name
        String input3 = "bench-;";
        assertThrows(CustomExceptions.InvalidInput.class, () -> gym.validateGymStationName(input3));

        // name length > 25 chars
        String input4 = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA ";
        assertThrows(CustomExceptions.InvalidInput.class, () -> gym.validateGymStationName(input4));
    }

    /**
     * Tests the behaviour of empty exercise names being passed to validateExerciseName.
     * Expects InsufficientInput exception to be thrown.
     */
    @Test
    void validateExerciseName_emptyNames_expectsInsufficientInputException() {
        Gym gym = new Gym();
        assertThrows(CustomExceptions.InsufficientInput.class, () -> gym.validateGymStationName(""));
    }

    /**
     * Tests the behaviour of a correct weights array being passed to validateWeightsArray.
     * Expects no exception to be thrown, and the correct ArrayList of integers to be
     * returned.
     *
     * @throws CustomExceptions.InvalidInput If the input string does not have the right format.
     */
    @Test
    void validateWeightsArray_correctInput_returnCorrectArrayList() throws CustomExceptions.InvalidInput {
        String input = "1.0,2.25,50.5,60.75,0.0";
        ArrayList<Double> expected = new ArrayList<>();
        expected.add(1.0);
        expected.add(2.25);
        expected.add(50.5);
        expected.add(60.75);
        expected.add(0.0);

        Gym gym = new Gym();
        ArrayList<Double> result = gym.processWeightsArray(input);
        assertArrayEquals(expected.toArray(), result.toArray());
    }

    /**
     * Tests the behaviour of incorrect weights array being passed to validateWeightsArray.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateWeightsArray_invalidInput_expectInvalidInputException() {
        // negative weights
        String input1 = "-1,2";
        Gym gym = new Gym();
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                gym.processWeightsArray(input1));

        // non integer weights
        String input2 = "1,a";
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                gym.processWeightsArray(input2));

        // incorrect multiple of weights
        String input3 = "1.3333, 1.444, 0.998";
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                gym.processWeightsArray(input3));

        // exceed max weights
        String input4 = "3000";
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                gym.processWeightsArray(input4));

    }

    /**
     * Tests the behaviour of incorrect inputs being passed to
     * addStation method in Gym
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void addStation_invalidInput_expectsInvalidInputException() {
        Gym gym = new Gym();
        Exception exception;

        // number of sets is not a positive integer
        exception = assertThrows(CustomExceptions.InvalidInput.class, () ->
                gym.addStation("Bench Press", "a", "4", "1020"));
        assertTrue(exception.getMessage().contains(ErrorConstant.INVALID_SETS_POSITIVE_DIGIT_ERROR));

        // number of reps is not a positive integer
        exception = assertThrows(CustomExceptions.InvalidInput.class, () ->
                gym.addStation("Bench Press", "2", "a", "1020"));
        assertTrue(exception.getMessage().contains(ErrorConstant.INVALID_REPS_POSITIVE_DIGIT_ERROR));

        // weights does not have comma
        exception = assertThrows(CustomExceptions.InvalidInput.class, () ->
                gym.addStation("Bench Press", "2", "3", "1020"));
        assertTrue(exception.getMessage().contains(ErrorConstant.INVALID_WEIGHTS_NUMBER_ERROR));

        // weights array > maximum
        exception = assertThrows(CustomExceptions.InvalidInput.class, () ->
                gym.addStation("Bench Press", "2", "3", "10000,20"));
        assertTrue(exception.getMessage().contains(ErrorConstant.INVALID_WEIGHT_MAX_ERROR));

        // weights array > minimum
        exception = assertThrows(CustomExceptions.InvalidInput.class, () ->
                gym.addStation("Bench Press", "2", "3", "-10,-20"));
        assertTrue(exception.getMessage().contains(ErrorConstant.INVALID_WEIGHTS_ARRAY_FORMAT_ERROR));

        // weights array has letters
        exception = assertThrows(CustomExceptions.InvalidInput.class, () ->
                gym.addStation("Bench Press", "2", "3", "10,a"));
        assertTrue(exception.getMessage().contains(ErrorConstant.INVALID_WEIGHTS_ARRAY_FORMAT_ERROR));

        // weights array has spaces
        exception = assertThrows(CustomExceptions.InvalidInput.class, () ->
                gym.addStation("Bench Press", "2", "3", "10, 20"));
        assertTrue(exception.getMessage().contains(ErrorConstant.INVALID_WEIGHTS_ARRAY_FORMAT_ERROR));

        // weights array regex fail
        exception = assertThrows(CustomExceptions.InvalidInput.class, () ->
                gym.addStation("Bench Press", "2", "3", "a"));
        assertTrue(exception.getMessage().contains(ErrorConstant.INVALID_WEIGHTS_ARRAY_FORMAT_ERROR));

        // no weights specified
        exception = assertThrows(CustomExceptions.InvalidInput.class, () ->
                gym.addStation("Bench Press", "2", "3", ""));
        assertTrue(exception.getMessage().contains(ErrorConstant.INVALID_WEIGHTS_EMPTY_ERROR));

        // weights and sets do not match
        exception = assertThrows(CustomExceptions.InvalidInput.class, () ->
                gym.addStation("Bench Press", "1", "3", "10,20"));
        assertTrue(exception.getMessage().contains((ErrorConstant.INVALID_WEIGHTS_NUMBER_ERROR)));
    }


}
