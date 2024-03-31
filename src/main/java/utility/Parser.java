package utility;

import constants.ErrorConstant;
import constants.HealthConstant;
import constants.UiConstant;
import ui.Output;

import health.Appointment;
import health.Bmi;
import health.HealthList;
import health.Period;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the parser used to parse and split input for PulsePilot.
 */
public class Parser {

    /**
     * Parses and converts String date to a LocalDate variable.
     * @param date String representing the date.
     * @return LocalDate variable representing the date.
     *
     * @throws DateTimeParseException If there is an error parsing the date.
     */
    public static LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate formattedDate = null;
        try {
            formattedDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            Output.printException(ErrorConstant.PARSING_DATE_ERROR);
        }
        return formattedDate;
    }

    /**
     * Parses and converts String time to a LocalDate variable.
     * @param stringTime String representing the time.
     * @return LocalTime variable representing the time.
     *
     * @throws DateTimeParseException If there is an error parsing the time.
     */
    public static LocalTime parseTime(String stringTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime formattedTime = null;
        try {
            formattedTime = LocalTime.parse(stringTime, formatter);
        } catch (DateTimeParseException e) {
            Output.printException(ErrorConstant.PARSING_TIME_ERROR);
        }
        return formattedTime;
    }

    /**
     * Splits user input for Delete command into item and index.
     *
     * @param input A user-provided string.
     * @return An array of strings containing the extracted delete command parameters.
     * @throws CustomExceptions.InsufficientInput If not enough parameters are specified.
     */
    public static String[] splitDeleteInput(String input) throws CustomExceptions.InsufficientInput {
        String[] results = new String[UiConstant.NUM_DELETE_PARAMETERS];
        if (!input.contains(UiConstant.ITEM_FLAG) || !input.contains(UiConstant.INDEX_FLAG)) {
            throw new CustomExceptions.InsufficientInput(ErrorConstant.INSUFFICIENT_DELETE_PARAMETERS_ERROR);
        }
        results[0] = extractSubstringFromSpecificIndex(input, UiConstant.ITEM_FLAG);
        results[1] = extractSubstringFromSpecificIndex(input, UiConstant.INDEX_FLAG);
        return results;
    }

    /**
     * Parses and validates user input for the delete command. Returns a list of parsed user input
     * containing the filter string and the index to delete.
     *
     * @param userInput The user input string.
     * @return A list of strings containing the filter string and index to delete.
     */
    public static String[] parseDeleteInput(String userInput) {
        try {
            String[] deleteDetails = splitDeleteInput(userInput);
            Validation.validateDeleteInput(deleteDetails);
            return deleteDetails;
        } catch (CustomExceptions.InvalidInput | CustomExceptions.InsufficientInput e) {
            Output.printException(e.getMessage());
            return null;
        }

    }

    //@@author JustinSoh
    /**
     * Function validates and parses the user input for the history and latest commands.
     *
     * @param userInput String representing the user input.
     * @return The filter string, set to either 'gym', 'run', 'bmi' or 'period'.
     */
    public static String parseHistoryAndLatestInput(String userInput) {
        try {
            String type = extractSubstringFromSpecificIndex(userInput, UiConstant.ITEM_FLAG);

            if (type.isBlank()) {
                throw new CustomExceptions.InsufficientInput(ErrorConstant.INVALID_HISTORY_FILTER_ERROR);
            }
            Validation.validateFilter(type.toLowerCase());
            return type.toLowerCase();
        } catch (CustomExceptions.InvalidInput | CustomExceptions.InsufficientInput e) {
            Output.printException(e.getMessage());
            return null;
        }
    }

    /**
     * Parses input for Bmi command. Adds Bmi object to HealthList if valid.
     *
     * @param userInput The user input string.
     */
    public static void parseBmiInput(String userInput) throws CustomExceptions.InvalidInput,
            CustomExceptions.InsufficientInput {
        String[] bmiDetails = splitBmiInput(userInput);
        Validation.validateBmiInput(bmiDetails);
        Bmi newBmi = new Bmi(bmiDetails[0], bmiDetails[1], bmiDetails[2]);
        HealthList.addBmi(newBmi);
        Output.printAddBmi(newBmi);
    }

    //@@author syj02
    /**
     * Split user input for Bmi command, height, weight and date.
     *
     * @param input A user-provided string.
     * @return An array of strings containing the extracted Bmi parameters.
     * @throws CustomExceptions.InvalidInput If the user input is invalid.
     */
    public static String[] splitBmiInput(String input) throws CustomExceptions.InvalidInput,
            CustomExceptions.InsufficientInput {
        String [] results = new String[HealthConstant.NUM_BMI_PARAMETERS];
        if (!input.contains(HealthConstant.HEIGHT_FLAG)
                || !input.contains(HealthConstant.WEIGHT_FLAG)
                || !input.contains(HealthConstant.DATE_FLAG)) {
            throw new CustomExceptions.InsufficientInput(ErrorConstant.INSUFFICIENT_BMI_PARAMETERS_ERROR);
        }
        results[0] = extractSubstringFromSpecificIndex(input, HealthConstant.HEIGHT_FLAG);
        results[1] = extractSubstringFromSpecificIndex(input, HealthConstant.WEIGHT_FLAG);
        results[2] = extractSubstringFromSpecificIndex(input, HealthConstant.DATE_FLAG);
        return results;
    }
    //@@author

    /**
     * Parses input for Period command. Adds Period object to HealthList if valid.
     *
     * @param userInput The user input string.
     */
    public static void parsePeriodInput(String userInput) throws CustomExceptions.InvalidInput,
            CustomExceptions.InsufficientInput {
        String[] periodDetails = splitPeriodInput(userInput);
        Validation.validatePeriodInput(periodDetails);
        Period newPeriod = new Period(periodDetails[0], periodDetails[1]);
        HealthList.addPeriod(newPeriod);
        Output.printAddPeriod(newPeriod);
    }

    /**
     * Split user input into Period command, start date and end date.
     *
     * @param input A user-provided string.
     * @return An array of strings containing the extracted Period parameters.
     * @throws CustomExceptions.InsufficientInput If the user input is invalid or blank.
     */
    public static String[] splitPeriodInput(String input) throws CustomExceptions.InsufficientInput {
        String [] results = new String[HealthConstant.NUM_PERIOD_PARAMETERS];

        if (!input.contains(HealthConstant.START_FLAG)
                || !input.contains(HealthConstant.END_FLAG)) {
            throw new CustomExceptions.InsufficientInput(ErrorConstant.INSUFFICIENT_PERIOD_PARAMETERS_ERROR);
        }
        results[0] = extractSubstringFromSpecificIndex(input, HealthConstant.START_FLAG);
        results[1] = extractSubstringFromSpecificIndex(input, HealthConstant.END_FLAG);
        return results;
    }

    /**
     * Parses input for Prediction command.
     * Prints period prediction if possible.
     *
     * @throws CustomExceptions.InsufficientInput If prediction cannot be made.
     */
    public static void parsePredictionInput() throws CustomExceptions.InsufficientInput {
        if (HealthList.getPeriodSize() >= HealthConstant.MIN_SIZE_FOR_PREDICTION) {
            HealthList.printLatestThreeCycles();
            LocalDate nextPeriodStartDate = HealthList.predictNextPeriodStartDate();
            Period.printNextCyclePrediction(nextPeriodStartDate);
        } else {
            throw new CustomExceptions.InsufficientInput(ErrorConstant.UNABLE_TO_MAKE_PREDICTIONS_ERROR);
        }
    }

    /**
     * Split user input into Appointment command, date, time and description.
     *
     * @param input A user-provided string.
     * @return An array of strings containing the extracted Appointment parameters.
     * @throws CustomExceptions.InsufficientInput If the user input is invalid or blank.
     */
    public static String[] splitAppointmentDetails(String input)
            throws CustomExceptions.InsufficientInput {
        String [] results = new String[HealthConstant.NUM_APPOINTMENT_PARAMETERS];
        if (!input.contains(HealthConstant.DATE_FLAG)
                || !input.contains(HealthConstant.TIME_FLAG)
                || !input.contains(HealthConstant.DESCRIPTION_FLAG)) {
            throw new CustomExceptions.InsufficientInput(ErrorConstant.INSUFFICIENT_APPOINTMENT_PARAMETERS_ERROR);
        }
        results[0] = extractSubstringFromSpecificIndex(input, HealthConstant.DATE_FLAG);
        results[1] = extractSubstringFromSpecificIndex(input, HealthConstant.TIME_FLAG);
        results[2] = extractSubstringFromSpecificIndex(input, HealthConstant.DESCRIPTION_FLAG);
        return results;
    }

    /**
     * Parses input for Appointment command. Adds Appointment object to HealthList if valid.
     *
     * @param userInput The user input string.
     */
    public static void parseAppointmentInput(String userInput) throws CustomExceptions.InvalidInput,
            CustomExceptions.InsufficientInput {
        String[] appointmentDetails = splitAppointmentDetails(userInput);
        Validation.validateAppointmentDetails(appointmentDetails);
        Appointment newAppointment = new Appointment(appointmentDetails[0],
                appointmentDetails[1],
                appointmentDetails[2]);
        HealthList.addAppointment(newAppointment);
        Output.printAddAppointment(newAppointment);
    }

    /**
     * Extracts a substring from the given input string based on the provided delimiter.
     *
     * @param input     The input string from which to extract the substring.
     * @param delimiter The delimiter to search for in the input string.
     * @return The extracted substring, or an empty string if the delimiter is not found.
     */
    public static String extractSubstringFromSpecificIndex(String input, String delimiter) {
        int index = input.indexOf(delimiter);
        if (index == -1 || index == input.length() - delimiter.length()) {
            return "";
        }
        int startIndex = index + delimiter.length();
        int endIndex = input.indexOf("/", startIndex);
        if (endIndex == -1) {
            endIndex = input.length();
        }
        return input.substring(startIndex, endIndex).trim();
    }
}
