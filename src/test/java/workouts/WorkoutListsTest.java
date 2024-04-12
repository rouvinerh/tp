package workouts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.CustomExceptions;
import constants.WorkoutConstant;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class WorkoutListsTest {
    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void cleanup() {
        WorkoutLists.clearWorkoutsRunGym();
    }


    /**
     * Tests the behavior of adding a new run to the run list.
     * Verifies whether the newly added run is correctly reflected in the run and WorkoutList.
     */
    @Test
    void addRun_normalInput_expectAppend()  {
        try {
            Run inputRun = new Run("40:10", "10.3", "15-03-2024");

            WorkoutLists workoutListsInstance = new WorkoutLists();
            workoutListsInstance.addRun(inputRun);

            ArrayList<Run> runList = WorkoutLists.getRuns();
            ArrayList<Workout> workoutList = WorkoutLists.getWorkouts();

            Workout expectedRun = runList.get(runList.size() - 1);
            Workout expectedWorkout = workoutList.get(runList.size() - 1);

            assertEquals(inputRun, expectedRun);
            assertEquals(inputRun, expectedWorkout);

        } catch (CustomExceptions.InvalidInput e) {
            fail("Should not throw an exception.");
        }
    }


    /**
     * Tests the behavior of getting the workout list with {@code RUN} , {@code ALL}
     * Verifies whether the method is able to correct retrieve the list of workouts.
     *
     */
    @Test
    void getWorkouts_properInput_expectRetrievalRun() {
        try {

            ArrayList<Run> inputList = new ArrayList<>();
            inputList.add(new Run("40:10", "10.3", "15-03-2024"));
            inputList.add(new Run("30:10", "20.3", "30-03-2023"));


            ArrayList<Run> runList = WorkoutLists.getRuns();
            for(int i = 0; i < inputList.size(); i++) {
                Run expected = inputList.get(i);
                Run actual = (Run) runList.get(i);
                assertEquals(expected, actual);
            }

        } catch (CustomExceptions.InvalidInput e) {
            fail("Should not throw an exception.");
        }
    }



//    /**
//     * Tests the behavior of getting an empty run / gym list
//     * Expected behaviour is to raise {@code OutOfBounds} exception.
//     */
//    @Test
//    void getWorkouts_emptyList_throwOutOfBoundsForRun() {
//        assertThrows(CustomExceptions.OutOfBounds.class, () -> WorkoutLists.getGyms());
//        assertThrows(CustomExceptions.OutOfBounds.class, () -> WorkoutLists.getRuns());
//    }

//    /**
//     * Tests the behavior of getting an empty run list
//     * Expected behaviour is to raise {@code OutOfBounds} exception.
//     */
//    @Test
//    void getWorkouts_emptyList_throwOutOfBoundsForAll() {
//        assertThrows(CustomExceptions.OutOfBounds.class, () -> WorkoutLists.getWorkouts(WorkoutConstant.ALL));
//    }

    /**
     * Tests the behavior of getting the latest run from the run list.
     * Expected behavior is for {@code actual} to equal to the {@code secondRun}
     */
    @Test
    void getLatestRun_properList_correctRetrieval() {
        try {
            new Run("20:10", "10.3", "15-03-2024");
            Run secondRun = new Run("20:10", "10.3", "15-03-2024");

            Run actual = WorkoutLists.getLatestRun();
            assertEquals(secondRun, actual);
        } catch (CustomExceptions.OutOfBounds | CustomExceptions.InvalidInput e) {
            fail("Should not throw an exception");
        }
    }

    /**
     * Test the behaviour when you try to get the latest run from an empty list.
     * Expected behaviour is to raise {@code OutOfBounds} exception.
     */
    @Test
    void getLatestRun_emptyList_throwOutOfBound() {
        // Call the method or code that should throw the exception
        assertThrows(CustomExceptions.OutOfBounds.class, WorkoutLists::getLatestRun);
    }


    /**
     * Test deleting of runs with valid list and valid index.
     * Expected behaviour is to have one run left in the list.
     *
     * @throws CustomExceptions.InvalidInput If there are invalid Run input parameters.
     * @throws CustomExceptions.OutOfBounds If the index is invalid.
     */
    @Test
    void deleteRun_properList_listOfSizeOne() throws CustomExceptions.InvalidInput, CustomExceptions.OutOfBounds {
        new Run("20:10", "10.3", "15-03-2024");
        new Run("20:11", "10.3", "15-03-2023");
        int index = 1;
        WorkoutLists.deleteRun(index);
        assertEquals(1, WorkoutLists.getRunSize());
    }

    /**
     * Test deleting of runs with empty list.
     * Expected behaviour is for an AssertionError to be thrown.
     */
    @Test
    void deleteRun_emptyList_throwsAssertionError() {
        assertThrows (AssertionError.class, () ->
                WorkoutLists.deleteRun(0));
    }

    /**
     * Test deleting of runs with invalid index.
     * Expected behaviour is for an OutOfBounds error to be thrown.
     *
     * @throws CustomExceptions.InvalidInput If there are invalid Run input parameters.
     */
    @Test
    void deleteRun_properListInvalidIndex_throwOutOfBoundsForRun() throws CustomExceptions.InvalidInput {
        new Run("20:11", "10.3", "15-03-2023");
        int invalidIndex = 5;
        assertThrows (CustomExceptions.OutOfBounds.class, () ->
                WorkoutLists.deleteRun(invalidIndex));
    }

    /**
     * Test deleting of gyms with valid list and valid index.
     * Expected behaviour is to have one gym left in the list.
     *
     * @throws CustomExceptions.InvalidInput If there are invalid Run input parameters.
     * @throws CustomExceptions.OutOfBounds If the index is invalid.
     */
    @Test
    void deleteGym_properList_listOfSizeOne() throws CustomExceptions.InvalidInput, CustomExceptions.OutOfBounds {
        Gym gym1 = new Gym();
        ArrayList<Double> array1 = new ArrayList<>(Arrays.asList(1.0));
        ArrayList<Double> array2 = new ArrayList<>(Arrays.asList(1.0,2.0));

        gym1.addStation("Bench Press", 1, 50, array1);
        gym1.addStation("Shoulder Press", 2, 10, array2);

        Gym gym2 = new Gym();
        gym2.addStation("Squat Press", 1, 50, array1);
        gym2.addStation("Lat Press", 2, 10, array2);
        gym2.addStation("Bicep curls", 1, 10, array1);

        int index = 1;
        WorkoutLists.deleteGym(index);
        assertEquals(1, WorkoutLists.getGymSize());
    }

    /**
     * Test deleting of gym with empty list.
     * Expected behaviour is for an AssertionError to be thrown.
     */
    @Test
    void deleteGym_emptyList_throwsAssertionError() {
        assertThrows (AssertionError.class, () ->
                WorkoutLists.deleteGym(0));
    }

    /**
     * Test deleting of runs with invalid index.
     * Expected behaviour is for an OutOfBounds error to be thrown.
     *
     * @throws CustomExceptions.InvalidInput If there are invalid Run input parameters.
     */
    @Test
    void deleteGym_properListInvalidIndex_throwOutOfBoundsForRun() throws CustomExceptions.InvalidInput {
        Gym gym1 = new Gym();
        ArrayList<Double> array1 = new ArrayList<>(Arrays.asList(1.0));
        ArrayList<Double> array2 = new ArrayList<>(Arrays.asList(1.0,2.0));
        gym1.addStation("Bench Press", 1, 50, array1);
        gym1.addStation("Shoulder Press", 2, 10, array2);
        int invalidIndex = 5;
        assertThrows (CustomExceptions.OutOfBounds.class, () ->
                WorkoutLists.deleteGym(invalidIndex));
    }
}
