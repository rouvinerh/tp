package utility;

import constants.ErrorConstant;
import constants.HealthConstant;
import constants.UiConstant;
import constants.WorkoutConstant;

import java.time.LocalDate;

/**
 * Represents the validation class used to validate all inputs for PulsePilot.
 */
public class Validation {
    /**
     * Validates that the input date string is correctly formatted in DD-MM-YYYY.
     *
     * @param date The string date from user input.
     * @throws CustomExceptions.InvalidInput If there are invalid date inputs.
     */
    public static void validateDateInput(String date) throws CustomExceptions.InvalidInput {
        if (!date.matches(UiConstant.VALID_DATE_REGEX)) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_DATE_ERROR);
        }
        String[] parts = date.split(UiConstant.DASH);
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);

        if (day < UiConstant.MIN_DAY || day > UiConstant.MAX_DAY) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_DAY_ERROR);
        }

        if (month < UiConstant.MIN_MONTH || month > UiConstant.MAX_MONTH) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_MONTH_ERROR);
        }
    }

    /**
     * Validates the delete input details.
     *
     * @param deleteDetails A list containing the details for the delete command.
     * @throws CustomExceptions.InvalidInput If the details specified are invalid.
     * @throws CustomExceptions.InsufficientInput If empty strings are used.
     */
    public static void validateDeleteInput(String[] deleteDetails) throws CustomExceptions.InvalidInput,
            CustomExceptions.InsufficientInput {
        if (deleteDetails[0].isEmpty() || deleteDetails[1].isEmpty()) {
            throw new CustomExceptions.InsufficientInput(ErrorConstant.INSUFFICIENT_DELETE_PARAMETERS_ERROR);
        }
        validateFilter(deleteDetails[0].toLowerCase());

        if (!deleteDetails[1].matches(UiConstant.VALID_POSITIVE_INTEGER_REGEX)) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_INDEX_ERROR);
        }
    }

    /**
     * Validates whether the filter string is either 'run', 'gym', 'bmi', 'period' or 'appointment'.
     *
     * @param filter The filter string to be checked.
     * @throws CustomExceptions.InvalidInput If the filter string is none of them.
     */
    public static void validateFilter(String filter) throws CustomExceptions.InvalidInput {
        if (filter.equals(WorkoutConstant.RUN)
                || filter.equals(WorkoutConstant.GYM)
                || filter.equals(HealthConstant.BMI)
                || filter.equals(HealthConstant.PERIOD)
                || filter.equals(HealthConstant.APPOINTMENT)
                || filter.equals(WorkoutConstant.ALL)) {
            return;
        }
        throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_ITEM_ERROR
                + System.lineSeparator()
                + ErrorConstant.CORRECT_FILTER_ITEM_FORMAT);
    }

    /**
     * Validates Bmi details entered.
     *
     * @param bmiDetails List of strings representing BMI details.
     * @throws CustomExceptions.InvalidInput If there are any errors in the details entered.
     */
    public static void validateBmiInput(String[] bmiDetails) throws CustomExceptions.InvalidInput,
            CustomExceptions.InsufficientInput {
        if (bmiDetails[0].isEmpty()
                || bmiDetails[1].isEmpty()
                || bmiDetails[2].isEmpty()) {
            throw new CustomExceptions.InsufficientInput(ErrorConstant.INSUFFICIENT_BMI_PARAMETERS_ERROR);
        }

        if (!bmiDetails[0].matches(UiConstant.VALID_TWO_DP_NUMBER_REGEX) ||
                !bmiDetails[1].matches(UiConstant.VALID_TWO_DP_NUMBER_REGEX)) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.HEIGHT_WEIGHT_INPUT_ERROR);
        }
        validateDateInput(bmiDetails[2]);
        LocalDate date = Parser.parseDate(bmiDetails[2]);
        if (date.isAfter(LocalDate.now())) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.DATE_IN_FUTURE_ERROR);
        }

    }

    /**
     * Validates Period details entered.
     *
     * @param periodDetails List of strings representing Period details.
     * @throws CustomExceptions.InvalidInput If there are any errors in the details entered.
     */
    public static void validatePeriodInput(String[] periodDetails) throws CustomExceptions.InvalidInput,
            CustomExceptions.InsufficientInput {
        if (periodDetails[0].isEmpty() || periodDetails[1].isEmpty()) {
            throw new CustomExceptions.InsufficientInput(ErrorConstant.INSUFFICIENT_PERIOD_PARAMETERS_ERROR);
        }

        try {
            validateDateInput(periodDetails[0]);
        } catch (CustomExceptions.InvalidInput e) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_START_DATE_ERROR
                    + System.lineSeparator()
                    + e.getMessage());
        }
        try {
            validateDateInput(periodDetails[1]);
        } catch (CustomExceptions.InvalidInput e) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_END_DATE_ERROR
                    + System.lineSeparator()
                    + e.getMessage());
        }

        LocalDate startDate = Parser.parseDate(periodDetails[0]);
        LocalDate endDate = Parser.parseDate(periodDetails[1]);
        if (startDate.isAfter(LocalDate.now())) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.START_DATE_IN_FUTURE_ERROR);
        }
        if (startDate.isAfter(endDate)) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.PERIOD_END_BEFORE_START_ERROR);
        }
    }

    //@@author JustinSoh
    /**
     * TO DO.
     * @param runDetails
     * @throws CustomExceptions.InvalidInput
     * @throws CustomExceptions.InsufficientInput
     */
    public static void validateRunInput(String[] runDetails) throws CustomExceptions.InvalidInput,
            CustomExceptions.InsufficientInput {
        if (runDetails[0].isEmpty() || runDetails[1].isEmpty()) {
            throw new CustomExceptions.InsufficientInput("Insufficient parameters for run! " +
                    "Example input: /e:run /d:5.25 /t:25:23 [/date:DATE]");
        }
        validateRunTimeInput(runDetails[0]);
        if (!runDetails[1].matches(UiConstant.VALID_TWO_DP_NUMBER_REGEX)) {
            throw new CustomExceptions.InvalidInput("Distance is a 2 decimal point positive number!");
        }

        if (runDetails[2] != null) {
            validateDateInput(runDetails[2]);
            LocalDate date = Parser.parseDate(runDetails[2]);
            if (date.isAfter(LocalDate.now())) {
                throw new CustomExceptions.InvalidInput("Date specified cannot be in the future");
            }
        }

    }

    public static void validateGymInput(String[] gymDetails) throws CustomExceptions.InvalidInput,
            CustomExceptions.InsufficientInput {
        if (gymDetails[0].isEmpty()) {
            throw new CustomExceptions.InsufficientInput("Insufficient parameters for gym!" +
                    "Example input: /e:gym /n:2 [/date:DATE]");
        }

        if (!gymDetails[0].matches(UiConstant.VALID_POSITIVE_INTEGER_REGEX)) {
            throw new CustomExceptions.InvalidInput("Number of stations is a positive number!");
        }

        if (gymDetails[1] != null) {
            validateDateInput(gymDetails[1]);
            LocalDate date = Parser.parseDate(gymDetails[1]);
            if (date.isAfter(LocalDate.now())) {
                throw new CustomExceptions.InvalidInput("Date specified cannot be in the future");
            }
        }


    }

    //@@author

    /**
     * Validates the time used in HH:MM format.
     *
     * @param time String representing the time to check.
     * @throws CustomExceptions.InvalidInput If time is formatted wrongly.
     */
    public static void validateTimeInput(String time) throws CustomExceptions.InvalidInput {
        if (!time.matches(UiConstant.VALID_TIME_REGEX)) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_TIME_ERROR);
        }
        String [] parts = time.split(UiConstant.SPLIT_BY_COLON);
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        if (hours < UiConstant.MIN_HOURS || hours > UiConstant.MAX_HOURS) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_HOURS_ERROR);
        }
        if (minutes < UiConstant.MIN_MINUTES || minutes > UiConstant.MAX_MINUTES) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_MINUTES_ERROR);
        }
    }

    //@@author JustinSoh
    /**
     * Validates the time used in HH:MM format.
     *
     * @param time String representing the time to check.
     * @throws CustomExceptions.InvalidInput If time is formatted wrongly.
     */
    public static void validateRunTimeInput(String time) throws CustomExceptions.InvalidInput {
        if (!time.matches(UiConstant.VALID_TIME_REGEX) &&
                !time.matches(UiConstant.VALID_TIME_WITH_HOURS_REGEX)) {
            throw new CustomExceptions.InvalidInput("Invalid time format. " +
                    "Format is HH:MM:SS or MM:SS with integers");
        }
        String [] parts = time.split(":");
        int hours = WorkoutConstant.NO_HOURS_PRESENT;
        int minutes;
        int seconds;

        if (parts.length == 2) {
            minutes = Integer.parseInt(parts[0]);
            seconds = Integer.parseInt(parts[1]);
        } else if (parts.length == 3) {
            hours = Integer.parseInt(parts[0]);
            minutes = Integer.parseInt(parts[1]);
            seconds = Integer.parseInt(parts[2]);
        } else {
            throw new CustomExceptions.InvalidInput("Invalid time format. Format is HH:MM:SS or MM:SS with integers");
        }
        if (minutes < 1 || minutes > 60) {
            throw new CustomExceptions.InvalidInput("Minutes must be a positive integer between 01 and 59.");
        }

        if (seconds < 1 || seconds > 60) {
            throw new CustomExceptions.InvalidInput("Minutes must be a positive integer between 01 and 59.");
        }

        if (hours == 0) {
            throw new CustomExceptions.InvalidInput("Hours cannot be 0. Use MM:SS instead");
        }
    }
    //@@author
    /**
     * Validates Appointment details entered.
     *
     * @param appointmentDetails List of strings representing Appointment details.
     * @throws CustomExceptions.InvalidInput If there are any errors in the details entered.
     */
    public static void validateAppointmentDetails(String[] appointmentDetails)
            throws CustomExceptions.InvalidInput, CustomExceptions.InsufficientInput {
        if (appointmentDetails[0].isEmpty()
                || appointmentDetails[1].isEmpty()
                || appointmentDetails[2].isEmpty()) {
            throw new CustomExceptions.InsufficientInput( ErrorConstant
                    .INSUFFICIENT_APPOINTMENT_PARAMETERS_ERROR);
        }
        validateDateInput(appointmentDetails[0]);
        validateTimeInput(appointmentDetails[1]);

        if (appointmentDetails[2].length() > HealthConstant.MAX_DESCRIPTION_LENGTH) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.DESCRIPTION_LENGTH_ERROR);
        }
        if (!appointmentDetails[2].matches(UiConstant.VALID_APPOINTMENT_DESCRIPTION_REGEX)) {
            throw new CustomExceptions.InvalidInput("Appointment description can only " +
                    "contain alphanumeric characters and spaces!");
        }
    }

    //@@author JustinSoh
    public static void validateExerciseName(String exerciseName) throws CustomExceptions.InvalidInput,
            CustomExceptions.InsufficientInput {
        if (exerciseName.isEmpty()) {
            throw new CustomExceptions.InsufficientInput("Exercise name cannot be blank!");
        }
        String validNameRegex = "^[A-Za-z\\s]+$";
        if (!exerciseName.matches(validNameRegex)) {
            throw new CustomExceptions.InvalidInput("Exercise name can only have letters!");
        }

        if (exerciseName.length() > 40) {
            throw new CustomExceptions.InvalidInput("Exercise name cannot bne more than 40 characters!");
        }
    }

    public static String[] splitAndValidateGymStationInput(String input) throws CustomExceptions.InvalidInput,
            CustomExceptions.InsufficientInput {
        String exerciseName = input.split(UiConstant.SPLIT_BY_SLASH)[WorkoutConstant.STATION_NAME_INDEX].trim();
        validateExerciseName(exerciseName);

        String sets = Parser.extractSubstringFromSpecificIndex(input, WorkoutConstant.SPLIT_BY_SETS);
        if (!sets.matches(UiConstant.VALID_POSITIVE_INTEGER_REGEX)) {
            throw new CustomExceptions.InvalidInput("Number of sets must be a positive integer!");
        }

        String reps = Parser.extractSubstringFromSpecificIndex(input, WorkoutConstant.SPLIT_BY_REPS);
        if (!reps.matches(UiConstant.VALID_POSITIVE_INTEGER_REGEX)) {
            throw new CustomExceptions.InvalidInput("Number of reps must be a positive integer!");
        }

        String weights = Parser.extractSubstringFromSpecificIndex(input, WorkoutConstant.SPLIT_BY_WEIGHTS);
        if (!weights.contains(UiConstant.SPLIT_BY_COMMAS)) {
            throw new CustomExceptions.InvalidInput("Enter the weight done for each set separated by commas!");
        }
        String[] weightsArray = weights.split(UiConstant.SPLIT_BY_COMMAS);
        if (weightsArray.length == 0) {
            throw new CustomExceptions.InvalidInput("Weights array cannot be empty");
        }

        if (weightsArray.length != Integer.parseInt(sets)) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.GYM_WEIGHTS_INCORRECT_NUMBER_ERROR);
        }

        String[] results = new String[4];
        results[0] = exerciseName;
        results[1] = weights;
        results[2] = sets;
        results[3] = reps;
        return results;
    }
}
