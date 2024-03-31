package utility;

import org.junit.jupiter.api.Test;
import workouts.Gym;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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

    @Test
    void parseGymFileInput_correctInput_returnsGymObject() {
        String input = "gym:2:11-11-1997:bench press:4:10:10,20,30,40:squats:2:5:20,30";
        String input2 = "gym:2:NA:bench press:4:10:10,20,30,40:squats:2:5:20,30";

        try{
            Gym gymOutput = Parser.parseGymFileInput(input);
            Gym gymOutput2 = Parser.parseGymFileInput(input2);
            // make sure that there is two gym station created
            assertEquals(2, gymOutput.getStations().size());
            // make sure that the date is correct
            assertEquals("1997-11-11", gymOutput.getDate().toString());
            assertEquals(null, gymOutput2.getDate());
            // make sure the gym exercise names are correct
            assertEquals("bench press", gymOutput.getStationByIndex(0).getStationName());
            assertEquals("squats", gymOutput.getStationByIndex(1).getStationName());
            // make sure the number of sets are correct
            assertEquals(4, gymOutput.getStationByIndex(0).getNumberOfSets());
            assertEquals(2, gymOutput.getStationByIndex(1).getNumberOfSets());
            // make sure the reps of each stations are correct
            assertEquals(10, gymOutput.getStationByIndex(0).getSets().get(0).getRepetitions());
            assertEquals(5, gymOutput.getStationByIndex(1).getSets().get(0).getRepetitions());
            // make sure te weights of each stations are correct
            assertEquals(10, gymOutput.getStationByIndex(0).getSets().get(0).getWeight());
            assertEquals(20, gymOutput.getStationByIndex(0).getSets().get(1).getWeight());
            assertEquals(30, gymOutput.getStationByIndex(0).getSets().get(2).getWeight());
            assertEquals(40, gymOutput.getStationByIndex(0).getSets().get(3).getWeight());
            assertEquals(20, gymOutput.getStationByIndex(1).getSets().get(0).getWeight());
            assertEquals(30, gymOutput.getStationByIndex(1).getSets().get(1).getWeight());



        } catch (Exception e) {
            fail("Should not throw an exception");
        }
    }



}
