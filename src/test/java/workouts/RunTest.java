package workouts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import utility.CustomExceptions;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RunTest {

    @AfterEach
    void cleanup() {
        WorkoutLists.clearWorkoutsRunGym();
    }
    /**
     * Tests the behaviour of checkRunTime when valid inputs are passed.
     * Expects no exceptions to be thrown.
     */
    @Test
    void checkRunTime_correctInput_returnListOfTimes() throws CustomExceptions.InvalidInput {

        // with hours
        String testTime = "01:59:10";
        Run runTest = new Run(testTime, "15.3");
        Integer[] result = runTest.checkRunTime(testTime);
        Integer[] expected = {1, 59, 10};
        assertArrayEquals(expected, result);

        // without hours
        Integer[] result2 = runTest.checkRunTime("50:52");
        Integer[] expected2 = {-1, 50, 52};
        assertArrayEquals(expected2, result2);

        // with hours, zero minutes zero seconds
        Integer[] result3 = runTest.checkRunTime("01:00:00");
        Integer[] expected3 = {1, 0, 0};
        assertArrayEquals(expected3, result3);

        // max time
        Integer[] result4 = runTest.checkRunTime("99:59:59");
        Integer[] expected4 = {99, 59, 59};
        assertArrayEquals(expected4, result4);

        // min time
        Integer[] result5 = runTest.checkRunTime("00:01");
        Integer[] expected5 = {-1, 0, 1};
        assertArrayEquals(expected5, result5);

        // max minute max second
        Integer[] result6 = runTest.checkRunTime("59:59");
        Integer[] expected6 = {-1, 59, 59};
        assertArrayEquals(expected6, result6);
    }

    /**
     * Tests the behaviour of checkRunTime when invalid inputs are passed.
     * Expects InvalidInput exception to be thrown for all test cases.
     */
    @Test
    void checkRunTime_invalidInputs_throwsInvalidInputException() {
        // hours set to 00
        String input1 = "00:30:00";
        assertThrows(CustomExceptions.InvalidInput.class, () -> new Run(input1, "15.3"));

        // invalid minutes
        String input2 = "60:00";
        assertThrows(CustomExceptions.InvalidInput.class, () -> new Run(input2, "15.3"));

        // invalid seconds
        String input3 = "05:60";
        assertThrows(CustomExceptions.InvalidInput.class, () -> new Run(input3, "15.3"));

        // 00:00 as time
        String input4 = "00:00";
        assertThrows(CustomExceptions.InvalidInput.class, () -> new Run(input4, "15.3"));
    }

    /**
     * Test the behaviour of checkDistance when valid distance is passed.
     * Expects the 2 decimal place string distance to be returned.
     *
     * @throws CustomExceptions.InvalidInput If distance is outside valid range
     */
    @Test
    void checkDistance_validDistance_returnTwoDecimalPlaceDistance() throws CustomExceptions.InvalidInput {
        Run run1 = new Run("25:00", "5.00");
        assertEquals("5.00", run1.getDistance());

        // min distance
        Run run2 = new Run("00:02", "0.01");
        assertEquals("0.01", run2.getDistance());

        // max distance
        Run run3 = new Run("99:59:00", "5000.00");
        assertEquals("5000.00", run3.getDistance());
    }

    /**
     * Tests the behaviour of checkDistance when invalid distances are passed.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void checkDistance_invalidDistance_throwsInvalidInputException() {
        // more than max of 5000
        assertThrows(CustomExceptions.InvalidInput.class, () -> new Run("03:30:00", "5001.00"));

        // less than min of 0
        assertThrows(CustomExceptions.InvalidInput.class, () -> new Run("03:30:00", "0.00"));
    }

    /**
     * Tests the behaviour of calculatedPace when a valid run is added.
     * Expects no exception to be thrown and correct pace returned.
     */
    @Test
    void calculatePace_validTimeAndDistance_returnCorrectPace() throws CustomExceptions.InvalidInput {
        Run run1 = new Run("25:00", "5.00");
        assertEquals("5:00/km", run1.getPace());

        Run run2 = new Run("01:25:00", "5.00");
        assertEquals("17:00/km", run2.getPace());

        // min pace
        Run run3 = new Run("5:00", "5.00");
        assertEquals("1:00/km", run3.getPace());

        // max pace
        Run run4 = new Run("02:30:00", "5.00");
        assertEquals("30:00/km", run4.getPace());
    }

    /**
     * Tests the behaviour of calculatedPace when a run with an invalid pace is added.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void calculatePace_invalidTimeAndDistance_throwInvalidInputException() {
        // exceed max pace of 30:00/km
        assertThrows(CustomExceptions.InvalidInput.class, () -> new Run("03:30:00", "5.00"));

        // below min pace of 1:00/km
        assertThrows(CustomExceptions.InvalidInput.class, () -> new Run("02:00", "10.00"));
    }
}
