package utility;

import constants.ErrorConstant;
import health.Bmi;
import health.Period;
import health.HealthList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import workouts.WorkoutLists;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class ValidationTest {
    private Validation validation;

    private final ByteArrayInputStream inContent = new ByteArrayInputStream("".getBytes());
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        validation = new Validation();
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
        System.setErr(originalErr);
        WorkoutLists.clearWorkoutsRunGym();
        HealthList.clearHealthLists();
    }

    /**
     * Tests the behaviour of isEmptyParameterPresent when an array of Strings is passed that has no empty strings.
     * Expects it to return false.
     */
    @Test
    void isEmptyParameterPresent_nonEmptyStrings_returnsFalse() {
        String[] input = {"1", "2", "3", "4"};
        assertFalse(validation.isEmptyParameterPresent(input));
    }

    /**
     * Tests the behaviour of isEmptyParameterPresent when an array of Strings is passed that has empty strings.
     * Expects it to return true.
     */
    @Test
    void isEmptyParameterPresent_nonEmptyStrings_returnsTrue() {
        String[] input = {"1", "2", "3", ""};
        assertTrue(validation.isEmptyParameterPresent(input));
    }


    /**
     * Tests the behaviour of the validateDateInput function with a correctly formatted string.
     * Expects no exception to be thrown.
     */
    @Test
    void validateDateInput_validDate_noExceptionThrown() {
        String validDate = "09-11-2024";
        assertDoesNotThrow(() -> validation.validateDateInput(validDate));
    }

    /**
     * Tests the behaviour of the validateDateInput function when invalid inputs
     * are passed to it.
     * Expects InvalidInput exception to be thrown with the correct error message printed.
     */
    @Test
    void validateDateInput_invalidDateInput_expectsInvalidInputExceptionWithCorrectErrorMessage() {
        // invalid day format
        String input1 = "9-11-2024";
        Exception exceptionThrown;
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateDateInput(input1));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_DATE_ERROR));

        // invalid month format
        String input2 = "09-1-2024";
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateDateInput(input2));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_DATE_ERROR));

        // invalid year format
        String input3 = "09-01-24";
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateDateInput(input3));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_DATE_ERROR));

        // illegal day number
        String input4 = "32-01-24";
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateDateInput(input4));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_DATE_ERROR));

        // day zero
        String input5 = "00-11-2024";
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateDateInput(input5));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_DATE_ERROR));

        // illegal month number
        String input6 = "09-13-2024";
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateDateInput(input6));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_DATE_ERROR));

        // invalid delimiter
        String input7 = "09/12/2024";
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateDateInput(input7));

        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_DATE_ERROR));
        // missing year
        String input8 = "09-12";
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateDateInput(input8));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_DATE_ERROR));

        // leap year
        String input9 = "29-02-2023";
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateDateInput(input9));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_LEAP_YEAR_ERROR));

        // year before 1967
        String input10 = "29-02-0000";
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateDateInput(input10));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_YEAR_ERROR));
    }

    /**
     * Tests the behaviour of correct parameters being passed to validateDate.
     * Expects no exception to be thrown.
     */
    @Test
    void validateDeleteInput_correctInput_noExceptionThrown() {
        String[] input = {"appointment", "2"};
        assertDoesNotThrow(() -> validation.validateDeleteInput(input));
    }

    /**
     * Tests the behaviour of the validateDeleteInput function when invalid inputs are passed to it.
     * Expects either InvalidInput or InsufficientInput exception to be thrown with the correct error message printed.
     */
    @Test
    void validateDeleteInput_incorrectInput_expectsExceptionThrownWithCorrectErrorMessage() {
        // invalid item
        Exception exceptionThrown;
        String[] input1 = {"free!", "2"};
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateDeleteInput(input1));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_LATEST_OR_DELETE_FILTER));

        // invalid index
        String[] input2 = {"gym", "-a"};
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateDeleteInput(input2));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_INDEX_ERROR));

        // empty strings
        String[] input3 = {"gym", ""};
        exceptionThrown = assertThrows(CustomExceptions.InsufficientInput.class, () ->
                validation.validateDeleteInput(input3));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INSUFFICIENT_DELETE_PARAMETERS_ERROR));
    }

    /**
     * Tests the behaviour of correct filter strings being passed to validateHistoryFilter.
     * Expects no exception to be thrown.
     */
    @Test
    void validateHistoryFilter_correctFilter_expectsNoExceptionThrown() {
        assertDoesNotThrow(() -> validation.validateHistoryFilter("gym"));
        assertDoesNotThrow(() -> validation.validateHistoryFilter("run"));
        assertDoesNotThrow(() -> validation.validateHistoryFilter("bmi"));
        assertDoesNotThrow(() -> validation.validateHistoryFilter("period"));
        assertDoesNotThrow(() -> validation.validateHistoryFilter("appointment"));
        assertDoesNotThrow(() -> validation.validateHistoryFilter("workouts"));
    }

    /**
     * Tests the behaviour of incorrect filter strings being passed to validateHistoryFilter.
     * Expects InvalidInput exception to be thrown with correct error message.
     */
    @Test
    void validateHistoryFilter_incorrectFilter_expectsInvalidInputExceptionWithCorrectErrorMessage() {
        Exception exceptionThrown;
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateHistoryFilter("foo"));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_HISTORY_FILTER_ERROR));
    }

    /**
     * Tests the behaviour of correct filter strings being passed to validateDeleteAndLatestFilter.
     * Expects no exception to be thrown.
     */
    @Test
    void validateDeleteAndLatestFilter_correctFilter_expectsNoExceptionThrown() {
        assertDoesNotThrow(() -> validation.validateDeleteAndLatestFilter("gym"));
        assertDoesNotThrow(() -> validation.validateDeleteAndLatestFilter("run"));
        assertDoesNotThrow(() -> validation.validateDeleteAndLatestFilter("bmi"));
        assertDoesNotThrow(() -> validation.validateDeleteAndLatestFilter("period"));
        assertDoesNotThrow(() -> validation.validateDeleteAndLatestFilter("appointment"));
    }

    /**
     * Tests the behaviour of incorrect filter strings being passed to validateDeleteAndLatestFilter.
     * Expects InvalidInput exception to be thrown with correct error message.
     */
    @Test
    void validateDeleteAndLatestFilter_incorrectFilter_expectsInvalidInputExceptionWithCorrectErrorMessage() {
        Exception exceptionThrown;
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateDeleteAndLatestFilter("foo"));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_LATEST_OR_DELETE_FILTER));
    }

    /**
     * Tests the behaviour of correct parameters being passed into validateBmi.
     * Expects no exceptions to be thrown.
     */
    @Test
    void validateBmiInput_correctParameters_noExceptionThrown() {
        String[] input = {"1.71", "70.00", "22-02-2024"};
        assertDoesNotThrow(() -> validation.validateBmiInput(input));
    }

    /**
     * Tests the behaviour of the validateBmiInput function when invalid inputs are passed to it.
     * Expects either InsufficientInput or InvalidInput exception to be thrown with the correct error message
     * printed.
     */
    @Test
    void validateBmiInput_incorrectInputs_expectsExceptionThrownWithCorrectErrorMessage() {
        Exception exceptionThrown;
        // 1 decimal point weight
        String[] input1 = {"1.71", "70.0", "29-04-2023"};
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () -> validation.validateBmiInput(input1));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_HEIGHT_WEIGHT_INPUT_ERROR));

        // 1 decimal point height
        String[] input2 = {"1.7", "70.03", "29-04-2023"};
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateBmiInput(input2));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_HEIGHT_WEIGHT_INPUT_ERROR));

        // height = 0
        String[] input3 = {"0.00", "70.03", "29-04-2023"};
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateBmiInput(input3));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.ZERO_HEIGHT_AND_WEIGHT_ERROR));

        // height > 2.75
        String[] input4 = {"3.00", "70.03", "29-04-2023"};
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateBmiInput(input4));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.MAX_HEIGHT_ERROR));

        // weight > 650
        String[] input5 = {"2.00", "1000.00", "29-04-2023"};
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateBmiInput(input5));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.MAX_WEIGHT_ERROR));

        // specified date already added
        new Bmi("1.70", "70.00", "14-04-2024");
        String[] input6 = {"1.70", "70.03", "14-04-2024"};
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateBmiInput(input6));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.DATE_ALREADY_EXISTS_ERROR));

        // empty strings
        String[] input7 = {"", "70.0", "29-04-2023"};
        exceptionThrown = assertThrows(CustomExceptions.InsufficientInput.class, () ->
                validation.validateBmiInput(input7));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INSUFFICIENT_BMI_PARAMETERS_ERROR));

    }

    /**
     * Tests the behaviour of validateDateNotAfterToday when a date string before 2024 is given.
     * Expects no exceptions to be thrown.
     */
    @Test
    void validateDateNotAfterToday_dateBeforeToday_noExceptionThrown() {
        String input = "14-04-2023";
        assertDoesNotThrow(() -> validation.validateDateNotAfterToday(input));
    }

    /**
     * Tests the behaviour of validateDateNotAfterToday when a date string after 2024 is given.
     * Expects InvalidInput exception to be thrown with correct error message printed.
     */
    @Test
    void validateDateNotAfterToday_dateAfterToday_expectsExceptionThrownWithCorrectErrorMessage() {
        String input = "14-04-2025";
        Exception exceptionThrown;
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateDateNotAfterToday(input));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.DATE_IN_FUTURE_ERROR));
    }

    //@@author j013n3
    /**
     * Tests the behaviour of correct parameters being passed into validatePeriodInput.
     * Expects no exception thrown.
     */
    @Test
    void validatePeriodInput_correctParameters_expectsExceptionThrownWithCorrectErrorMessage() {
        boolean isParser = true;
        String[] input = {"22-03-2024", "28-03-2024"};
        assertDoesNotThrow(() -> validation.validatePeriodInput(input, isParser));
    }

    /**
     * Tests the behaviour of incorrect parameters being passed into validatePeriodInput.
     * Expects either InvalidInput or InsufficientInput exception to be thrown with correct error message printed.
     */
    @Test
    void validatePeriodInput_incorrectParameters_expectsExceptionThrownWithCorrectErrorMessage() {
        boolean isParser = true;
        Exception exceptionThrown;
        // empty strings
        String[] input1 = {"", "29-03-2024"};

        exceptionThrown = assertThrows(CustomExceptions.InsufficientInput.class, () ->
                validation.validatePeriodInput(input1, isParser));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INSUFFICIENT_PERIOD_PARAMETERS_ERROR));

        // end date before start date
        String[] input2 = {"28-03-2024", "22-03-2024"};
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validatePeriodInput(input2, isParser));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.PERIOD_END_BEFORE_START_ERROR));

        // invalid start date
        // end date before start date
        String[] input3 = {"28-13-2024", "22-03-2024"};
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validatePeriodInput(input3, isParser));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_START_DATE_ERROR));
    }


    //@@author rouvinerh
    /**
     * Tests the behaviour of correct parameters being passed into validateAppointmentDetails.
     * Expects no exception to be thrown.
     */
    @Test
    void validateAppointmentInput_correctParameters_noExceptionThrown() {
        String[] input = {"29-04-2024", "19:30", "test description"};
        assertDoesNotThrow(() -> validation.validateAppointmentDetails(input));
    }

    /**
     * Tests the behaviour of incorrect parameters being passed into validateAppointmentDetails.
     * Expects either InvalidInput or InsufficientInput exception to be thrown with correct error message printed.
     */
    @Test
    void validateAppointmentInput_incorrectParameters_expectsInvalidInputException() {
        // description too long
        Exception exceptionThrown;
        String[] input1 = {"28-04-2024", "22:30",
                           "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" +
                           "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"};
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateAppointmentDetails(input1));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.DESCRIPTION_LENGTH_ERROR));

        // description contains non-alphanumeric characters
        String[] input2 = {"28-04-2024", "22:30", "doctor | ;"};
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateAppointmentDetails(input2));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_DESCRIPTION_ERROR));

        // empty strings
        String[] input3 = {"", "22:30", "doctor"};
        exceptionThrown = assertThrows(CustomExceptions.InsufficientInput.class, () ->
                validation.validateAppointmentDetails(input3));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INSUFFICIENT_APPOINTMENT_PARAMETERS_ERROR));
    }

    /**
     * Tests the behaviour of a correctly formatted time string being passed into validateTimeInput.
     * Expects no exception to be thrown.
     */
    @Test
    void validateTimeInput_correctInput_noExceptionThrown() {
        String input = "23:50";
        assertDoesNotThrow(() -> validation.validateTimeInput(input));
    }

    /**
     * Tests the behaviour of incorrect parameters being passed into validateTimeInput.
     * Expects InvalidInput exception to be thrown with correct error message printed.
     */
    @Test
    void validateTimeInput_invalidInput_expectsInvalidInputExceptionWithCorrectErrorMessage() {
        Exception exceptionThrown;
        // invalid delimiter
        String input1 = "23-50";
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateTimeInput(input1));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_ACTUAL_TIME_ERROR));

        // illegal hours
        String input2 = "24:50";
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateTimeInput(input2));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_ACTUAL_TIME_HOUR_ERROR));

        // illegal minutes
        String input3 = "21:60";
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateTimeInput(input3));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_ACTUAL_TIME_MINUTE_ERROR));

        // time contains letters
        String input4 = "12:2a";
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateTimeInput(input4));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_ACTUAL_TIME_ERROR));

        // invalid format
        String input5 = "21:55:44";
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateTimeInput(input5));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_ACTUAL_TIME_ERROR));
    }

    /**
     * Tests the behaviour of correct parameters being passed to validateRunInput.
     * Expects no exceptions thrown.
     */
    @Test
    void validateRunInput_correctInput_expectsNoExceptionsThrown() {
        // with dates
        String[] input1 = {"20:25", "5.15", "29-03-2024"};
        assertDoesNotThrow(() -> validation.validateRunInput(input1));

        // without dates
        String[] input2 = {"20:25", "5.15", null};
        assertDoesNotThrow(() -> validation.validateRunInput(input2));
    }

    /**
     * Tests the behaviour of incorrect parameters being passed into validateRunInput.
     * Expects either InvalidInput or InsufficientInput exception to be thrown with correct error message printed.
     */
    @Test
    void validateRunInput_incorrectParameters_expectsExceptionThrownWithCorrectErrorMessage() {
        Exception exceptionThrown;
        // invalid distance
        String[] input1 = {"20:25", "5"};
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateRunInput(input1));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_RUN_DISTANCE_ERROR));

        // date in future
        String[] input2 = {"20:25", "5.25", "31-03-2025"};
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateRunInput(input2));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.DATE_IN_FUTURE_ERROR));

        // has non integer values in time
        String[] input3 = {"2a:03", "5.00"};
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateRunInput(input3));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_RUN_TIME_ERROR));

        // invalid delimiter
        String[] input4 = {"25-03", "5.00"};
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateRunInput(input4));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_RUN_TIME_ERROR));

        // too many parts
        String[] input5 = {"25:03:04:22", "5.00"};
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateRunInput(input5));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_RUN_TIME_ERROR));

        // invalid format test 1
        String[] input6 = {"1:2:3", "5.00"};
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateRunInput(input6));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_RUN_TIME_ERROR));

        // invalid format
        String[] input7 = {"100:00:00", "5.00"};
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateRunInput(input7));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_RUN_TIME_ERROR));

        // empty strings
        String[] input8 = {"20:25", ""};
        exceptionThrown = assertThrows(CustomExceptions.InsufficientInput.class, () ->
                validation.validateRunInput(input8));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INSUFFICIENT_RUN_PARAMETERS_ERROR));
    }

    /**
     * Tests the behaviour of valid input being passed to validateGymInput.
     * Expects no exceptions to be thrown.
     */
    @Test
    void validateGymInput_correctInput_noExceptionThrown() {
        String[] input1 = {"4", "29-04-2023"};
        assertDoesNotThrow(() -> validation.validateGymInput(input1));

        String[] input2 = {"4", null};
        assertDoesNotThrow(() -> validation.validateGymInput(input2));
    }

    /**
     * Tests the behaviour of incorrect parameters being passed into validateRunInput.
     * Expects either InvalidInput or InsufficientInput exception to be thrown with correct error message printed.
     */
    @Test
    void validateGymInput_invalidInput_expectsExceptionThrownWithCorrectErrorMessage() {
        Exception exceptionThrown;
        // non integer number of sets
        String[] input1 = {"a", null};
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateGymInput(input1));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_NUMBER_OF_STATIONS_ERROR));

        // number of sets exceeds maximum allowed
        String[] input2 = {"51", null};
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateGymInput(input2));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.MAX_STATIONS_ERROR));

        // number of sets below minimum allowed
        String[] input3 = {"-1", null};
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateGymInput(input3));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_NUMBER_OF_STATIONS_ERROR));

        // empty strings
        String[] input4 = {"", null};
        exceptionThrown = assertThrows(CustomExceptions.InsufficientInput.class, () ->
                validation.validateGymInput(input4));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INSUFFICIENT_GYM_PARAMETERS_ERROR));
    }

    /**
     * Tests the behaviour of a valid start date being passed to validateDateAfterLatestPeriod.
     * Expects no exception to be thrown.
     */
    @Test
    void validateDateAfterLatestPeriodInput_validInput_noExceptionThrown() {
        String input1 =  "10-04-2024";
        assertDoesNotThrow(() ->
                validation.validateDateAfterLatestPeriodInput(input1, null));

        LocalDate latestPeriodEndDate2 = LocalDate.of(2024, 3, 9);
        String input2 = "11-04-2024";
        assertDoesNotThrow(() ->
                validation.validateDateAfterLatestPeriodInput(input2, latestPeriodEndDate2));
    }

    /**
     * Tests the behaviour of invalid start dates being passed to validateDateAfterLatestPeriod.
     * Expects InvalidInput exception to be thrown with correct error message printed.
     */
    @Test
    void validateDateAfterLatestPeriodInput_invalidDateInput_expectsInvalidInputExceptionWithCorrectMessage() {
        LocalDate latestPeriodEndDate = LocalDate.of(2024, 3, 9);
        Exception exceptionThrown;
        //date is before latestPeriodEndDate
        String input1 = "09-02-2024";
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateDateAfterLatestPeriodInput(input1, latestPeriodEndDate));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.CURRENT_START_BEFORE_PREVIOUS_END));

        //date same as latestPeriodEndDate
        String input2 = "09-03-2024";
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateDateAfterLatestPeriodInput(input2, latestPeriodEndDate));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.CURRENT_START_BEFORE_PREVIOUS_END));
    }

    /**
     * Tests the behaviour of start dates being passed to validateStartDatesTally.
     * Expects no exception to be thrown.
     */
    @Test
    void validateStartDatesTally_validInput_noExceptionThrown() {
        new Period("01-01-2024");
        String[] input1 = {"01-01-2024", "05-01-2024"};
        assertDoesNotThrow(() ->
                validation.validateStartDatesTally(null, input1));

        LocalDate latestPeriodEndDate2 = LocalDate.of(2024,1,1);
        String[] input2 = {"01-01-2024", "05-02-2024"};
        assertDoesNotThrow(() ->
                validation.validateStartDatesTally(latestPeriodEndDate2, input2));
    }

    /**
     * Tests the behaviour of incorrect parameters being passed into validateRunInput.
     * Expects InvalidInput exception to be thrown with correct error message printed.
     */
    @Test
    void validateStartDatesTally_invalidInput_expectsInvalidInputExceptionWithCorrectMessage() {
        new Period("01-01-2024");
        Exception exceptionThrown;

        //start dates do not tally
        String[] input1 = {"01-01-2023", "05-01-2024"};
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateStartDatesTally(null, input1));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.INVALID_START_DATE_INPUT_ERROR));

        //end date is missing from user input
        String[] input2 = {"01-01-2024", null};
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateStartDatesTally(null, input2));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.END_DATE_NOT_FOUND_ERROR));
    }

    /**
     * Tests the behaviour of a date that is not found the list being passed to validateDateNotPresent.
     * Expects no exception to be thrown.
     */
    @Test
    void validateDateNotPresent_validInput_noExceptionThrown() {
        new Bmi("1.75", "70.00", "02-02-2024");
        new Bmi("1.75", "71.00", "02-03-2024");

        //date not found in list
        String input1 = "03-03-2024";
        assertDoesNotThrow(() ->
                validation.validateDateNotPresent(input1));
    }

    /**
     * Tests the behaviour of a date that is found the list being passed to validateDateNotPresent.
     * Expects InvalidException to be thrown with correct error message printed.
     */
    @Test
    void validateDateNotPresent_invalidInput_expectsInvalidInputExceptionWithCorrectMessage() {
        new Bmi("1.75", "70.00", "02-02-2024");
        new Bmi("1.75", "71.00", "02-03-2024");
        Exception exceptionThrown;
        //date found in list
        String input1 = "02-02-2024";
        exceptionThrown = assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateDateNotPresent(input1));
        assertTrue(exceptionThrown.toString().contains(ErrorConstant.DATE_ALREADY_EXISTS_ERROR));
    }
}
