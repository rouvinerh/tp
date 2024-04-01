package utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ValidationTest {
    /**
     * Tests the behaviour of the validateDateInput function with a correctly formatted string.
     * Expects no exception to be thrown.
     */
    @Test
    public void validateDateInput_validDate_noExceptionThrown() {
        String validDate = "09-11-2024";
        assertDoesNotThrow(() -> {
            Validation.validateDateInput(validDate);
        });
    }

    /**
     * Tests the behaviour of the validateDateInput function when invalid inputs
     * are passed to it.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    public void validateDateInput_invalidDateInput_expectsInvalidInputException() {
        // invalid day format
        String input1 = "9-11-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Validation.validateDateInput(input1);
        });

        // invalid month format
        String input2 = "09-1-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Validation.validateDateInput(input2);
        });

        // invalid year format
        String input3 = "09-01-24";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Validation.validateDateInput(input3);
        });

        // illegal day number
        String input4 = "32-01-24";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Validation.validateDateInput(input4);
        });

        // day zero
        String input5 = "00-11-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Validation.validateDateInput(input5);
        });

        // illegal month number
        String input6 = "09-13-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Validation.validateDateInput(input6);
        });

        // invalid delimiter
        String input7 = "09/12/2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Validation.validateDateInput(input7);
        });

        // missing year
        String input8 = "09-12";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Validation.validateDateInput(input8);
        });
    }

    /**
     * Tests the behaviour of correct parameters being passed to validateDate.
     * Expects no exception to be thrown.
     */
    @Test
    void validateDeleteInput_correctInput_noExceptionThrown() {
        String[] input = {"appointment", "2"};
        assertDoesNotThrow(() -> Validation.validateDeleteInput(input));
    }

    /**
     * Tests the behaviour of the validateDeleteInput function when invalid inputs
     * are passed to it.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateDeleteInput_invalidInput_expectsInvalidInputException() {
        // invalid item
        String[] input1 = {"free!", "2"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> Validation.validateDeleteInput(input1));

        // invalid index
        String[] input2 = {"item", "-a"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> Validation.validateDeleteInput(input2));
    }

    /**
     * Tests the behaviour of an empty string being passed to validateDeleteInput.
     * Expects InsufficientInput exception to be thrown.
     */
    @Test
    void validateDeleteInput_emptyString_expectsInsufficientInputException() {
        String[] input = {"item", ""};
        assertThrows(CustomExceptions.InsufficientInput.class, () -> Validation.validateDeleteInput(input));
    }

    /**
     * Tests the behaviour of correct parameters being passed into validateBmi.
     * Expects no exceptions to be thrown.
     */
    @Test
    void validateBmiInput_correctParameters_noExceptionThrown() {
        String[] input = {"1.71", "70.00", "22-02-2024"};
        assertDoesNotThrow(() -> Validation.validateBmiInput(input));
    }

    /**
     * Tests the behaviour of the validateBmiInput function when invalid inputs
     * are passed to it.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateBmiInput_oneDecimalPointWeight_expectsInvalidInputException() {
        // 1 decimal point weight
        String[] input1 = {"1.71", "70.0", "29-04-2024"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> Validation.validateBmiInput(input1));

        // 1 decimal point height
        String[] input2 = {"1.7", "70.03", "29-04-2024"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> Validation.validateBmiInput(input2));

        // date in future
        String[] input3 = {"1.70", "70.03", "28-03-2025"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> Validation.validateBmiInput(input3));
    }

    /**
     * Tests the behaviour of an empty string being passed to validateBmiInput.
     * Expects InsufficientInput exception to be thrown.
     */
    @Test
    void validateBmiInput_emptyString_expectsInsufficientInputException() {
        String[] input = {"", "", ""};
        assertThrows(CustomExceptions.InsufficientInput.class, () -> Validation.validateBmiInput(input));
    }

    /**
     * Tests the behaviour of correct parameters being passed into validatePeriod.
     * Expects no exception to be thrown.
     */
    @Test
    void validatePeriodInput_correctParameters_noExceptionThrown()  {
        String [] input = {"23-03-2024", "30-03-2024"};
        assertDoesNotThrow(() -> Validation.validatePeriodInput(input));
    }

    /**
     * Tests the behaviour of a string with an empty string being passed into validatePeriod.
     * Expects InsufficientInput exception to be thrown.
     */
    @Test
    void validatePeriodInput_emptyString_expectsInsufficientInputException() {
        String [] input = {"", "29-03-2024"};
        assertThrows(CustomExceptions.InsufficientInput.class, () -> Validation.validatePeriodInput(input));
    }

    /**
     * Tests the behaviour of the validatePeriodInput function when invalid inputs
     * are passed to it.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validatePeriodInput_invalidParameters_expectsInvalidInputException() {
        // date after Today
        String [] input1 = {"28-04-2025", "29-13-2025"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> Validation.validatePeriodInput(input1));

        // end date before start date
        String [] input2 = {"28-03-2024", "22-03-2024"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> Validation.validatePeriodInput(input2));
    }

    /**
     * Tests the behaviour of correct parameters being passed into validateAppointment.
     * Expects no exception to be thrown.
     */
    @Test
    void validateAppointmentInput_correctParameters_noExceptionThrown() {
        String[] input = {"29-04-2024", "19:30", "test description"};
        assertDoesNotThrow(() -> Validation.validateAppointmentDetails(input));
    }

    /**
     * Tests the behaviour of an empty string being passed into validateAppointment.
     * Expects InsufficientInput exception to be thrown.
     */
    @Test
    void validateAppointmentInput_emptyParameters_expectsInsufficientInputException() {
        String[] input = {"29-04-2024", "19:30", ""};
        assertThrows(CustomExceptions.InsufficientInput.class, () -> Validation.validateAppointmentDetails(input));
    }

    /**
     * Tests the behaviour of the validateAppointmentInput function when
     * invalid inputs are passed to it.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateAppointmentInput_invalidDescriptions_expectsInvalidInputException() {
        // description too long
        String[] input1 = {"28-04-2024", "22:30",
                           "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" +
                           "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> Validation.validateAppointmentDetails(input1));

        // description contains non-alphanumeric characters
        String[] input2 = {"28-04-2024", "22:30", "doctor | ; whoami"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> Validation.validateAppointmentDetails(input2));
    }

    /**
     * Tests the behaviour of a correctly formatted time string being passed into validateTimeInput.
     * Expects no exception to be thrown.
     */
    @Test
    void validateTimeInput_correctInput_noExceptionThrown() {
        String input = "23:50";
        assertDoesNotThrow(() -> Validation.validateTimeInput(input));
    }

    /**
     * Tests the behaviour of the validateTimeInput function when
     * invalid inputs are passed to it.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateTimeInput_invalidInput_expectsInvalidInputException() {
        // invalid delimiter
        String input1 = "23-50";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Validation.validateTimeInput(input1));

        // illegal hours
        String input2 = "24:50";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Validation.validateTimeInput(input2));

        // illegal minutes
        String input3 = "21:60";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Validation.validateTimeInput(input3));

        // time contains letters
        String input4 = "12:2a";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Validation.validateTimeInput(input4));

        // invalid format
        String input5 = "21:55:44";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Validation.validateTimeInput(input5));
    }

    /**
     * Tests the behaviour of correct filter strings being passed to validateFilter.
     * Expects no exceptions to be thrown.
     */
    @Test
    void validateDeleteFilter_correctFilters_expectsNoExceptionsThrown () {
        String input1 = "run";
        assertDoesNotThrow( () -> Validation.validateFilter(input1));

        String input2 = "gym";
        assertDoesNotThrow( () -> Validation.validateFilter(input2));

        String input3 = "bmi";
        assertDoesNotThrow( () -> Validation.validateFilter(input3));

        String input4 = "period";
        assertDoesNotThrow( () -> Validation.validateFilter(input4));

        String input5 = "appointment";
        assertDoesNotThrow( () -> Validation.validateFilter(input5));

        String input6 = "workouts";
        assertDoesNotThrow( () -> Validation.validateFilter(input6));
    }

    /**
     * Tests the behaviour of an incorrect filter string being passed to validateFilter.
     */
    @Test
    void validateDeleteFilter_incorrectFilter_expectsInvalidInputException() {
        String input = "fake";
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                Validation.validateFilter(input));
    }

    /**
     * Tests the behaviour of correct parameters being passed to validateRunInput.
     * Expects no exceptions thrown.
     */
    @Test
    void validateRunInput_correctInput_expectsNoExceptionsThrown() {
        // with dates
        String[] input1 = {"20:25", "5.15", "29-03-2024"};
        assertDoesNotThrow( () -> Validation.validateRunInput(input1));

        // without dates
        String[] input2 = {"20:25", "5.15", null};
        assertDoesNotThrow( () -> Validation.validateRunInput(input2));
    }

    /**
     * Tests the behaviour of empty strings being passed to validateRunInput.
     * Expects an InsufficientInput exception to be thrown.
     */
    @Test
    void validateRunInput_emptyStrings_expectsInsufficientInputException() {
        String[] input = {"20:25", ""};
        assertThrows(CustomExceptions.InsufficientInput.class, () ->
                Validation.validateRunInput(input));
    }

    /**
     * Tests the behaviour of invalid parameters being passed to validateRunInput.
     * Expects an InvalidInput exception to be thrown.
     */
    @Test
    void validateRunInput_invalidInputs_expectsInvalidInputException() {
        // invalid distance
        String[] input1 = {"20:25", "5"};
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                Validation.validateRunInput(input1));

        // date in future
        String[] input2 = {"20:25", "5.25", "31-3-2025"};
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                Validation.validateRunInput(input2));
    }

    /**
     * Tests the behaviour of valid parameters being passed to validateRunTimeInput.
     * Expects no exceptions to be thrown.
     */
    @Test
    void validateRunTimeInput_correctTimeInput_expectsNoExceptionThrown() {
        // without hours
        String input1 = "25:03";
        assertDoesNotThrow( () -> Validation.validateRunTimeInput(input1));

        // with hours
        String input2 = "01:25:03";
        assertDoesNotThrow( () -> Validation.validateRunTimeInput(input2));
    }

    /**
     * Tests the behaviour of invalid parameters being passed to validateRunTimeInput.
     * Expects an InvalidInput exception to be thrown.
     */
    @Test
    void validateRunTimeInput_invalidTimeInput_expectsInvalidInputException() {
        // has non integer characters
        String input1 = "2a:03";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Validation.validateRunTimeInput(input1));

        // invalid delimiter
        String input2 = "25-03";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Validation.validateRunTimeInput(input2));

        // too many parts
        String input3 = "25:03:04:22";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Validation.validateRunTimeInput(input3));

        // invalid format
        String input4 = "1:2:3";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Validation.validateRunTimeInput(input4));

        // invalid format
        String input5 = "1:2:3";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Validation.validateRunTimeInput(input5));

        // invalid minutes
        String input6 = "01:65:03";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Validation.validateRunTimeInput(input6));

        // invalid seconds
        String input7 = "01:55:83";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Validation.validateRunTimeInput(input7));

        // invalid hours
        String input8 = "00:12:34";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Validation.validateRunTimeInput(input8));
    }

    /**
     * Tests the behaviour of valid exercise names being passed to validateExerciseName.
     * Expects no exceptions to be thrown.
     */
    @Test
    void validateExerciseName_correctName_noExceptionThrown() {
        String input1 = "Bench Press";
        assertDoesNotThrow( () -> Validation.validateExerciseName(input1));

        String input2 = "squat";
        assertDoesNotThrow( () -> Validation.validateExerciseName(input2));
    }

    /**
     * Tests the behaviour of invalid exercise names being passed to validateExerciseName.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateExerciseName_invalidNames_expectsInvalidInputException() {
        // numbers in name
        String input1 = "bench1";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Validation.validateExerciseName(input1));

        // special characters in name
        String input2 = "bench-;";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Validation.validateExerciseName(input2));

        // special characters in name
        String input3 = "bench-;";
        assertThrows(CustomExceptions.InvalidInput.class, () -> Validation.validateExerciseName(input3));
    }

    /**
     * Tests the behaviour of empty exercise names being passed to validateExerciseName.
     * Expects InsufficientInput exception to be thrown.
     */
    @Test
    void validateExerciseName_emptyNames_expectsInsufficientInputException() {
        assertThrows(CustomExceptions.InsufficientInput.class, () -> Validation.validateExerciseName(""));
    }

    /**
     * Tests the behaviour of valid input being passed to validateGymInput.
     * Expects no exceptions to be thrown.
     */
    @Test
    void validateGymInput_correctInput_noExceptionThrown() {
        String[] input1 = {"4", "29-04-2023"};
        assertDoesNotThrow( () -> Validation.validateGymInput(input1));

        String[] input2 = {"4", null};
        assertDoesNotThrow( () -> Validation.validateGymInput(input2));
    }

    /**
     * Tests the behaviour of empty strings being passed to validateGymInput.
     * Expects InsufficientInput exception to be thrown.
     */
    @Test
    void validateGymInput_emptyString_expectsInsufficientInputException() {
        String[] input = {"", null};
        assertThrows(CustomExceptions.InsufficientInput.class , () ->
                Validation.validateGymInput(input));
    }

    /**
     * Tests the behaviour of invalid parameters being passed to validateGymInput.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateGymInput_invalidInput_expectsInvalidInputException() {
        String[] input = {"a", null};
        assertThrows(CustomExceptions.InvalidInput.class , () ->
                Validation.validateGymInput(input));
    }

    /**
     * Tests the behaviour of correct inputs being passed to splitAndValidateGymStationInput
     * Expects no exceptions thrown.
     *
     * @throws CustomExceptions.InsufficientInput If there are not enough parameters.
     * @throws CustomExceptions.InvalidInput If there are invalid parameters specified.
     */
    @Test
    void splitAndValidateGymStationInput_validInput_correctParametersReturned() throws
            CustomExceptions.InsufficientInput, CustomExceptions.InvalidInput {
        String input = "Bench Press /s:2 /r:4 /w:10,20";
        String[] expected = {"Bench Press", "2", "4", "10,20"};
        String[] result = Validation.splitAndValidateGymStationInput(input);
        assertArrayEquals(expected, result);
    }

    @Test
    void splitAndValidateGymStationInput_invalidInput_expectsInvalidInputException() {
        // number of sets is not a positive integer
        String input1 = "Bench Press /s:a /r:4 /w:1020";
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                Validation.splitAndValidateGymStationInput(input1));

        // number of reps is not a positive integer
        String input2 = "Bench Press /s:2 /r:a /w:1020";
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                Validation.splitAndValidateGymStationInput(input2));

        // weights are not formatted properly
        String input3 = "Bench Press /s:2 /r:a /w:1020";
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                Validation.splitAndValidateGymStationInput(input3));

        // weights specified exceed number of sets
        String input4 = "Bench Press /s:1 /r:a /w:10,20";
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                Validation.splitAndValidateGymStationInput(input4));

    }
}
