package helper;

import constants.ErrorConstant;
import constants.HealthConstant;
import constants.UiConstant;

/**
 * Helper class to help with testing
 * Contains methods to help create the output string for testing
 * Methods that starts with add are used when objects of that type is created
 * Methods that starts with latest are used when the latest object is printed
 * Methods that starts with error are used when an error are thrown
 */
public class TestHelper {
    /**
     * Helper method to help create the output string when BMI is added
     * @param weight weight of the user
     * @param height height of the user
     * @param date date of the user in the format yyyy-mm-dd
     * @param bmiValue bmi value of the user in 2 decimal places
     * @param bmiCategory the category (use the constants in HealthConstant)
     * @return String representing the output
     */
    public static String addBmiOutputString (String weight,
                                             String height,
                                             String date,
                                             double bmiValue,
                                             String bmiCategory){

        StringBuilder outputString = new StringBuilder();
        outputString.append(UiConstant.PARTITION_LINE);
        outputString.append(System.lineSeparator());
        outputString.append(HealthConstant.BMI_ADDED_MESSAGE_PREFIX);
        outputString.append(height);
        outputString.append(UiConstant.LINE);
        outputString.append(weight);
        outputString.append(UiConstant.LINE);
        outputString.append(date);
        outputString.append(System.lineSeparator());
        outputString.append(String.format(HealthConstant.PRINT_BMI_FORMAT,
                date, bmiValue, bmiCategory));
        outputString.append(System.lineSeparator());
        outputString.append(UiConstant.PARTITION_LINE);
        outputString.append(System.lineSeparator());
        return outputString.toString();
    }

    /**
     * Helper method to help create the latestString for Period
     * @param date date of the bmi in the format yyyy-mm-dd
     * @param bmiValue bmi value of the user in 2 decimal places
     * @param bmiMessage the category (use the constants in HealthConstant)
     * @return String representing the output
     */
    public static String latestBmiOutputString(String date, double bmiValue, String bmiMessage ) {
        return UiConstant.PARTITION_LINE +
                System.lineSeparator() +
                date +
                System.lineSeparator() +
                "Your BMI is " +
                String.format("%.2f" , bmiValue) +
                System.lineSeparator() +
                bmiMessage +
                System.lineSeparator() +
                UiConstant.PARTITION_LINE +
                System.lineSeparator();
    }

    /**
     * Helper method to help create the output string when Period is added
     *
     * @param startDate    start date of the period in the format yyyy-mm-dd
     * @param endDate      end date of the period in the format yyyy-mm-dd
     * @param periodLength length of the period in days
     * @return String representing the output
     */
    public static String addPeriodOutputString (String startDate,
                                          String endDate,
                                          int periodLength){

        StringBuilder outputString = new StringBuilder();
        outputString.append(UiConstant.PARTITION_LINE);
        outputString.append(System.lineSeparator());
        outputString.append(HealthConstant.PERIOD_ADDED_MESSAGE_PREFIX);
        outputString.append(startDate);
        outputString.append(UiConstant.LINE);
        outputString.append(endDate);
        outputString.append(System.lineSeparator());
        outputString.append(String.format(HealthConstant.PRINT_PERIOD_FORMAT,
                startDate,
                endDate ,
                periodLength,
                HealthConstant.DAYS_MESSAGE));
        outputString.append(System.lineSeparator());
        if (!HealthConstant.PERIOD_TOO_LONG_MESSAGE.isBlank()) {
            outputString.append(ErrorConstant.COLOR_HEADING +
                    HealthConstant.PERIOD_TOO_LONG_MESSAGE +
                    ErrorConstant.COLOR_ENDING);
            outputString.append(System.lineSeparator());
        }
        outputString.append(UiConstant.PARTITION_LINE);
        outputString.append(System.lineSeparator());


        return outputString.toString();
    }

    /**
     * Helper method to help create the latestString for Period
     * @param startDate start date of the period in the format yyyy-mm-dd
     * @param endDate end date of the period in the format yyyy-mm-dd
     * @param periodLength length of the period in days
     * @param period the "days" message (use the constants in HealthConstant)
     * @return String representing the output
     */
    public static String latestPeriodOutputString(String startDate, String endDate, int periodLength, String period) {
        return UiConstant.PARTITION_LINE +
                System.lineSeparator() +
                String.format(HealthConstant.PRINT_PERIOD_FORMAT,
                        startDate,
                        endDate,
                        periodLength,
                        period) +
                System.lineSeparator() +
                UiConstant.PARTITION_LINE +
                System.lineSeparator();

    }

    /**
     * Helper method to help create the output string when Appointment is added
     * @param date date of the appointment in the format yyyy-mm-dd
     * @param time time of the appointment in the format hh:mm
     * @param description description of the appointment
     * @return String representing the output
     */
    public static String addAppointmentString(String date, String time, String description) {
        StringBuilder outputString = new StringBuilder();
        outputString.append(UiConstant.PARTITION_LINE);
        outputString.append(System.lineSeparator());
        outputString.append(HealthConstant.APPOINTMENT_ADDED_MESSAGE_PREFIX);
        outputString.append(date);
        outputString.append(UiConstant.LINE);
        outputString.append(time);
        outputString.append(UiConstant.LINE);
        outputString.append(description);
        outputString.append(System.lineSeparator());
        outputString.append(String.format(HealthConstant.PRINT_APPOINTMENT_FORMAT, date, time, description));
        outputString.append(System.lineSeparator());
        outputString.append(UiConstant.PARTITION_LINE);
        outputString.append(System.lineSeparator());
        return outputString.toString();
    }

    /**
     * Helper method to help create the latestString for Appointment
     * @param date date of the appointment in the format yyyy-mm-dd
     * @param time time of the appointment in the format hh:mm
     * @param description description of the appointment
     * @return String representing the output
     */
    public static String latestAppointmentOutputString(String date, String time, String description) {
        return UiConstant.PARTITION_LINE +
                System.lineSeparator() +
                String.format(HealthConstant.PRINT_APPOINTMENT_FORMAT, date, time, description) +
                System.lineSeparator() +
                UiConstant.PARTITION_LINE +
                System.lineSeparator();
    }

    public static String errorInvalidInputString(String errorString) {
        return ErrorConstant.COLOR_HEADING +
                "Exception Caught!" +
                System.lineSeparator() +
                ErrorConstant.COLOR_HEADING +
                ErrorConstant.INVALID_INPUT_HEADER +
                errorString +
                ErrorConstant.COLOR_ENDING +
                ErrorConstant.COLOR_ENDING +
                System.lineSeparator();
    }

    public static String errorOutOfBoundsString(String errorString) {
        return ErrorConstant.COLOR_HEADING +
                "Exception Caught!" +
                System.lineSeparator() +
                ErrorConstant.COLOR_HEADING +
                ErrorConstant.OUT_OF_BOUND_HEADER +
                errorString +
                ErrorConstant.COLOR_ENDING +
                ErrorConstant.COLOR_ENDING +
                System.lineSeparator();
    }

    /**
     * Used to get the invalid exception string for invalid command (without the exception header)
     * @param errorString to be printed
     * @return String
     */
    public static String errorInvalidCommandString(String errorString) {
        return ErrorConstant.COLOR_HEADING +
                "Exception Caught!" +
                System.lineSeparator() +
                errorString +
                ErrorConstant.COLOR_ENDING +
                System.lineSeparator();
    }


    public static String errorInsufficientInput(String errorString){
        return ErrorConstant.COLOR_HEADING +
                "Exception Caught!" +
                System.lineSeparator() +
                ErrorConstant.COLOR_HEADING +
                ErrorConstant.INSUFFICIENT_INPUT_HEADER +
                errorString +
                ErrorConstant.COLOR_ENDING +
                ErrorConstant.COLOR_ENDING +
                System.lineSeparator();
    }

    public static String printGreetingsFoundString(String name){
        StringBuilder output = new StringBuilder();
        output.append(UiConstant.FILE_FOUND_MESSAGE);
        output.append(name);
        output.append(System.lineSeparator());
        output.append(UiConstant.SUCCESSFUL_LOAD);
        output.append(System.lineSeparator());
        output.append(UiConstant.PARTITION_LINE);
        output.append(System.lineSeparator());
        return output.toString();
    }

    public static String printGreetingNotFoundString(String name){
        StringBuilder output = new StringBuilder();
        output.append(UiConstant.FILE_MISSING_MESSAGE);
        output.append(System.lineSeparator());
        output.append(UiConstant.PARTITION_LINE);
        output.append(System.lineSeparator());
        return output.toString();
    }
}
