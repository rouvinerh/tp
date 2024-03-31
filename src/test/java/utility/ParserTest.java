package utility;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ParserTest {

    /**
     * Tests the behaviour of the parseDate function with a correctly formatted string.
     */
    @Test
    void parseDate_correctDateInput_returnDate() {
        LocalDate result = Parser.parseDate("08-03-2024");
        LocalDate expected = LocalDate.of(2024, 3, 8);
        assertEquals(expected, result);
    }

    /**
     * Tests the behaviour of the parseDate function with an incorrectly formatted string.
     * Expects null to be returned.
     */
    @Test
    void parseDate_incorrectDateInput_returnNull () {
        String input = "2024-03-08";
        LocalDate result = Parser.parseDate(input);
        assertEquals(null, result);
    }

    /**
     * Tests the behaviour of correct parameters being passed to validateDate.
     * Expects the correct details to be returned as a list of strings.
     */
    @Test
    public void splitDelete_correctInput_returnsCorrectDeleteValues() throws CustomExceptions.InsufficientInput {
        String input = "/item:appointment /index:1";
        String[] expected = {"appointment", "1"};
        String[] result = Parser.splitDeleteInput(input);
        assertArrayEquals(expected, result);
    }

    /**
     * Tests the behaviour of insufficient parameters being passed to validateDate.
     * Expects InvalidInput exception to be thrown.
     */
    @Test
    public void splitDelete_missingParameter_throwsInsufficientParameterException() {
        String input = "/item:appointment";
        assertThrows(CustomExceptions.InsufficientInput.class, () -> Parser.splitDeleteInput(input));
    }


    //@@author j013n3
    /**
     * Tests the behaviour of a correctly formatted user input being passed into splitBmi.
     * Expects no exception to be thrown.
     */
    @Test
    void splitBmi_correctInput_returnsCorrectBmiValues() throws CustomExceptions.InsufficientInput,
            CustomExceptions.InvalidInput {
        String input = "/h:bmi /height:1.71 /weight:60.50 /date:19-03-2024";
        String[] expected = {"1.71", "60.50", "19-03-2024"};
        String[] result = Parser.splitBmiInput(input);
        assertArrayEquals(expected, result);
    }


    /**
     * Tests the behaviour of a string with missing parameter being passed into splitBmi.
     * Expects InsufficientInput exception to be thrown.
     */
    @Test
    void splitBmi_missingParameter_throwsInsufficientInputException() {
        String input = "/h:bmi /height:1.71 /date:19-03-2024";
        assertThrows(CustomExceptions.InsufficientInput.class, () -> Parser.splitBmiInput(input));
    }
    //@@author

    /**
     * Tests the behaviour of a correctly formatted string being passed into splitPeriod.
     * Expects no exception to be thrown.
     */
    @Test
    void splitPeriod_correctInput_noExceptionThrown() throws CustomExceptions.InvalidInput,
            CustomExceptions.InsufficientInput {
        String input = "/h:period /start:29-04-2023 /end:30-04-2023";
        String[] expected = {"29-04-2023", "30-04-2023"};
        String[] result = Parser.splitPeriodInput(input);
        assertArrayEquals(expected, result);
    }

    /**
     * Tests the behaviour of a string with a missing parameter being passed into splitPeriod.
     * Expects InsufficientInput exception to be thrown.
     */
    @Test
    void splitPeriod_missingParameter_throwsInsufficientInputException() {
        String input = "/h:period /start:29-04-2023";
        assertThrows(CustomExceptions.InsufficientInput.class, () -> Parser.splitPeriodInput(input));
    }

    /**
     * Tests the behaviour of a correctly formatted string being passed into splitAppointment.
     * Expects no exception to be thrown.
     */
    @Test
    void splitAppointment_correctInput_noExceptionThrown() throws CustomExceptions.InsufficientInput {
        String input = "/h:appointment /date:30-03-2024 /time:19:30 /description:test";
        String[] expected = {"30-03-2024", "19:30", "test"};
        String[] result = Parser.splitAppointmentDetails(input);
        assertArrayEquals(expected, result);
    }

    /**
     * Tests the behaviour of a correctly formatted string being passed into splitAppointment.
     * Expects InsufficientInput exception to be thrown.
     */
    @Test
    void splitAppointment_missingParameter_throwsInsufficientInputException() {
        String input = "/h:appointment /date:30-03-2024 /description:test";
        assertThrows(CustomExceptions.InsufficientInput.class, () -> Parser.splitAppointmentDetails(input));
    }

    /**
     * Tests the behaviour of the extractSubstringFromSpecificIndex with a correct flag.
     * Expects the 'bmi' string to be extracted.
     */
    @Test
    void extractSubstringFromSpecificIndex_correctFlag_returnsCorrectSubstring() {
        String test = "/h:bmi";
        String testDelimiter = "/h:";
        String result = Parser.extractSubstringFromSpecificIndex(test, testDelimiter);
        String expected = "bmi";
        assertEquals(expected, result);
    }
}
