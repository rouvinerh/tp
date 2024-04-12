package utility;

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
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


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
     * Tests the behaviour of the validateDateInput function with a correctly formatted string.
     * Expects no exception to be thrown.
     */
    @Test
    public void validateDateInput_validDate_noExceptionThrown() {
        String validDate = "09-11-2024";
        assertDoesNotThrow(() -> {
            validation.validateDateInput(validDate);
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
            validation.validateDateInput(input1);
        });

        // invalid month format
        String input2 = "09-1-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            validation.validateDateInput(input2);
        });

        // invalid year format
        String input3 = "09-01-24";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            validation.validateDateInput(input3);
        });

        // illegal day number
        String input4 = "32-01-24";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            validation.validateDateInput(input4);
        });

        // day zero
        String input5 = "00-11-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            validation.validateDateInput(input5);
        });

        // illegal month number
        String input6 = "09-13-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            validation.validateDateInput(input6);
        });

        // invalid delimiter
        String input7 = "09/12/2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            validation.validateDateInput(input7);
        });

        // missing year
        String input8 = "09-12";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            validation.validateDateInput(input8);
        });

        // leap year
        String input9 = "29-02-2023";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            validation.validateDateInput(input9);
        });

        // year before 1967
        String input10 = "29-02-0000";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            validation.validateDateInput(input10);
        });
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
     * Tests the behaviour of the validateDeleteInput function when invalid inputs
     * are passed to it.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateDeleteInput_invalidInput_expectsInvalidInputException() {
        // invalid item
        String[] input1 = {"free!", "2"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> validation.validateDeleteInput(input1));

        // invalid index
        String[] input2 = {"item", "-a"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> validation.validateDeleteInput(input2));
    }

    /**
     * Tests the behaviour of an empty string being passed to validateDeleteInput.
     * Expects InsufficientInput exception to be thrown.
     */
    @Test
    void validateDeleteInput_emptyString_expectsInsufficientInputException() {
        String[] input = {"item", ""};
        assertThrows(CustomExceptions.InsufficientInput.class, () -> validation.validateDeleteInput(input));
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
     * Tests the behaviour of the validateBmiInput function when invalid inputs
     * are passed to it.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateBmiInput_oneDecimalPointWeight_expectsInvalidInputException() {
        // 1 decimal point weight
        String[] input1 = {"1.71", "70.0", "29-04-2024"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> validation.validateBmiInput(input1));

        // 1 decimal point height
        String[] input2 = {"1.7", "70.03", "29-04-2024"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> validation.validateBmiInput(input2));

        // date in future
        String[] input3 = {"1.70", "70.03", "28-03-2025"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> validation.validateBmiInput(input3));
    }

    /**
     * Tests the behaviour of an empty string being passed to validateBmiInput.
     * Expects InsufficientInput exception to be thrown.
     */
    @Test
    void validateBmiInput_emptyString_expectsInsufficientInputException() {
        String[] input = {"", "", ""};
        assertThrows(CustomExceptions.InsufficientInput.class, () -> validation.validateBmiInput(input));
    }

    /**
     * Tests the behaviour of correct parameters being passed into validatePeriod.
     * Expects no exception to be thrown.
     */
    @Test
    void validatePeriodInput_correctParameters_noExceptionThrown() {
        boolean isParser = true;
        String[] input = {"23-03-2024", "30-03-2024"};
        assertDoesNotThrow(() -> validation.validatePeriodInput(input, isParser));
    }

    /**
     * Tests the behaviour of a string with an empty string being passed into validatePeriod.
     * Expects InsufficientInput exception to be thrown.
     */
    @Test
    void validatePeriodInput_emptyString_expectsInsufficientInputException() {
        boolean isParser = true;
        String[] input = {"", "29-03-2024"};
        assertThrows(CustomExceptions.InsufficientInput.class, () -> validation.validatePeriodInput(input, isParser));
    }

    /**
     * Tests the behaviour of the validatePeriodInput function when invalid inputs
     * are passed to it.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validatePeriodInput_invalidParameters_expectsInvalidInputException() {
        boolean isParser = true;
        // date after Today
        String[] input1 = {"28-04-2025", "29-13-2025"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> validation.validatePeriodInput(input1, isParser));

        // end date before start date
        String[] input2 = {"28-03-2024", "22-03-2024"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> validation.validatePeriodInput(input2, isParser));
    }

    /**
     * Tests the behaviour of correct parameters being passed into validateAppointment.
     * Expects no exception to be thrown.
     */
    @Test
    void validateAppointmentInput_correctParameters_noExceptionThrown() {
        String[] input = {"29-04-2024", "19:30", "test description"};
        assertDoesNotThrow(() -> validation.validateAppointmentDetails(input));
    }

    /**
     * Tests the behaviour of an empty string being passed into validateAppointment.
     * Expects InsufficientInput exception to be thrown.
     */
    @Test
    void validateAppointmentInput_emptyParameters_expectsInsufficientInputException() {
        String[] input = {"29-04-2024", "19:30", ""};
        assertThrows(CustomExceptions.InsufficientInput.class, () -> validation.validateAppointmentDetails(input));
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
        assertThrows(CustomExceptions.InvalidInput.class, () -> validation.validateAppointmentDetails(input1));

        // description contains non-alphanumeric characters
        String[] input2 = {"28-04-2024", "22:30", "doctor | ; whoami"};
        assertThrows(CustomExceptions.InvalidInput.class, () -> validation.validateAppointmentDetails(input2));
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
     * Tests the behaviour of the validateTimeInput function when
     * invalid inputs are passed to it.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateTimeInput_invalidInput_expectsInvalidInputException() {
        // invalid delimiter
        String input1 = "23-50";
        assertThrows(CustomExceptions.InvalidInput.class, () -> validation.validateTimeInput(input1));

        // illegal hours
        String input2 = "24:50";
        assertThrows(CustomExceptions.InvalidInput.class, () -> validation.validateTimeInput(input2));

        // illegal minutes
        String input3 = "21:60";
        assertThrows(CustomExceptions.InvalidInput.class, () -> validation.validateTimeInput(input3));

        // time contains letters
        String input4 = "12:2a";
        assertThrows(CustomExceptions.InvalidInput.class, () -> validation.validateTimeInput(input4));

        // invalid format
        String input5 = "21:55:44";
        assertThrows(CustomExceptions.InvalidInput.class, () -> validation.validateTimeInput(input5));
    }

    /**
     * Tests the behaviour of correct filter strings being passed to validateFilter.
     * Expects no exceptions to be thrown.
     */
    @Test
    void validateDeleteFilter_correctFilters_expectsNoExceptionsThrown() {
        String input1 = "run";
        assertDoesNotThrow(() -> validation.validateHistoryFilter(input1));

        String input2 = "gym";
        assertDoesNotThrow(() -> validation.validateHistoryFilter(input2));

        String input3 = "bmi";
        assertDoesNotThrow(() -> validation.validateHistoryFilter(input3));

        String input4 = "period";
        assertDoesNotThrow(() -> validation.validateHistoryFilter(input4));

        String input5 = "appointment";
        assertDoesNotThrow(() -> validation.validateHistoryFilter(input5));

        String input6 = "workouts";
        assertDoesNotThrow(() -> validation.validateHistoryFilter(input6));
    }

    /**
     * Tests the behaviour of an incorrect filter string being passed to validateFilter.
     */
    @Test
    void validateDeleteFilter_incorrectFilter_expectsInvalidInputException() {
        String input = "fake";
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateHistoryFilter(input));
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
     * Tests the behaviour of empty strings being passed to validateRunInput.
     * Expects an InsufficientInput exception to be thrown.
     */
    @Test
    void validateRunInput_emptyStrings_expectsInsufficientInputException() {
        String[] input = {"20:25", ""};
        assertThrows(CustomExceptions.InsufficientInput.class, () ->
                validation.validateRunInput(input));
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
                validation.validateRunInput(input1));

        // date in future
        String[] input2 = {"20:25", "5.25", "31-3-2025"};
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateRunInput(input2));

        // dist exceed max
        String[] input3 = {"20:25", "5000.25", "31-3-2025"};
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateRunInput(input3));

        // dist below min
        String[] input4 = {"20:25", "0.00", "31-3-2025"};
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateRunInput(input4));

        // has non integer values in time
        String[] input5 = {"2a:03", "5.00"};
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateRunInput(input5));

        // invalid delimiter
        String[] input6 = {"25-03", "5.00"};
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateRunInput(input6));

        // too many parts
        String[] input7 = {"25:03:04:22", "5.00"};
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateRunInput(input7));

        // invalid format test 1
        String[] input8 = {"1:2:3", "5.00"};
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateRunInput(input8));

        // invalid format
        String[] input9 = {"100:00:00", "5.00"};
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateRunInput(input9));
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
     * Tests the behaviour of empty strings being passed to validateGymInput.
     * Expects InsufficientInput exception to be thrown.
     */
    @Test
    void validateGymInput_emptyString_expectsInsufficientInputException() {
        String[] input = {"", null};
        assertThrows(CustomExceptions.InsufficientInput.class, () ->
                validation.validateGymInput(input));


    }

    /**
     * Tests the behaviour of invalid parameters being passed to validateGymInput.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateGymInput_invalidInput_expectsInvalidInputException() {
        // non integer number of sets
        String[] input1 = {"a", null};
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateGymInput(input1));

        // number of sets exceeds maximum allowed
        String[] input2 = {"51", null};
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateGymInput(input2));

        // number of sets below minimum allowed
        String[] input3 = {"-1", null};
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateGymInput(input3));
    }




    /**
     * Tests the behaviour of a valid start date being passed to validateDateAfterLatestPeriod.
     * Expects no exception to be thrown.
     */
    @Test
    void validateDateAfterLatestPeriodInput_validInput_noExceptionThrown() {
        LocalDate latestPeriodEndDate1 = null;
        String input1 =  "10-04-2024";
        assertDoesNotThrow(() ->
                validation.validateDateAfterLatestPeriodInput(input1, latestPeriodEndDate1));

        LocalDate latestPeriodEndDate2 = LocalDate.of(2024, 3, 9);
        String input2 = "11-04-2024";
        assertDoesNotThrow(() ->
                validation.validateDateAfterLatestPeriodInput(input2, latestPeriodEndDate2));
    }

    /**
     * Tests the behaviour of invalid start dates being passed to validateDateAfterLatestPeriod.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateDateAfterLatestPeriodInput_invalidDateInput_expectsInvalidExceptionThrown() {
        LocalDate latestPeriodEndDate = LocalDate.of(2024, 3, 9);

        //date is before latestPeriodEndDate
        String input1 = "09-02-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateDateAfterLatestPeriodInput(input1, latestPeriodEndDate));

        //date same as latestPeriodEndDate
        String input2 = "09-03-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateDateAfterLatestPeriodInput(input2, latestPeriodEndDate));
    }

    /**
     * Tests the behaviour of start dates being passed to validateStartDatesTally.
     * Expects no exception to be thrown.
     */
    @Test
    void validateStartDatesTally_validInput_noExceptionThrown() {
        Period period = new Period("01-01-2024");

        LocalDate latestPeriodEndDate1 = null;
        String[] input1 = {"01-01-2024", "05-01-2024"};
        assertDoesNotThrow(() ->
                validation.validateStartDatesTally(latestPeriodEndDate1, input1));

        LocalDate latestPeriodEndDate2 = LocalDate.of(2024,01,01);
        String[] input2 = {"01-01-2024", "05-02-2024"};
        assertDoesNotThrow(() ->
                validation.validateStartDatesTally(latestPeriodEndDate2, input2));
    }

    /**
     * Tests the behaviour of invalid start dates being passed to validateStartDatesTally.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    void validateStartDatesTally_invalidInput_expectsInvalidExceptionThrown() {
        Period period = new Period("01-01-2024");

        //start dates do not tally
        LocalDate latestPeriodEndDate1 = null;
        String[] input1 = {"01-01-2023", "05-01-2024"};
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateStartDatesTally(latestPeriodEndDate1, input1));

        //end date is missing from user input
        LocalDate latestPeriodEndDate2 = null;
        String[] input2 = {"01-01-2024", null};
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateStartDatesTally(latestPeriodEndDate2, input2));
    }

    /**
     * Tests the behaviour of a date that is not found the list being passed to validateDateNotPresent.
     * Expects no exception to be thrown.
     */
    @Test
    void validateDateNotPresent_validInput_noExceptionThrown() {
        Bmi bmi1 = new Bmi("1.75", "70.00", "02-02-2024");
        Bmi bmi2 = new Bmi("1.75", "71.00", "02-03-2024");

        //date not found in list
        String input1 = "03-03-2024";
        assertDoesNotThrow(() ->
                validation.validateDateNotPresent(input1));
    }

    /**
     * Tests the behaviour of a date that is found the list being passed to validateDateNotPresent.
     * Expects InvalidException to be thrown.
     */
    @Test
    void validateDateNotPresent_invalidInput_expectsInvalidExceptionThrown() {
        Bmi bmi1 = new Bmi("1.75", "70.00", "02-02-2024");
        Bmi bmi2 = new Bmi("1.75", "71.00", "02-03-2024");

        //date found in list
        String input1 = "02-02-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () ->
                validation.validateDateNotPresent(input1));
    }

}
