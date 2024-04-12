package workouts;


import utility.CustomExceptions;
import constants.ErrorConstant;
import constants.UiConstant;
import constants.WorkoutConstant;

/**
 * Represents a Run object that extends the Workout class.
 * It takes in the {@code time} and {@code distance} of the run as input.
 * It also calculates the pace of the run based on the time and distance.
 * It also formats the time and distance into a readable format.
 */
public class Run extends Workout {
    //@@author rouvinerh
    private final Integer[] times;
    private final double distance;
    private final String pace;
    private boolean isHourPresent;


    /**
     * Constructs a new Run object with the time and distance from user input.
     *
     * @param stringTime     The time taken for the run.
     * @param stringDistance The distance of the run.
     * @throws CustomExceptions.InvalidInput If there is invalid input.
     */
    public Run(String stringTime, String stringDistance) throws CustomExceptions.InvalidInput {
        times = splitRunTime(stringTime);
        distance = Double.parseDouble(stringDistance);
        pace = calculatePace();
    }

    /**
     * Overloaded constructor that takes in time, distance and the optional date parameter from user input.
     *
     * @param stringTime     The time taken for the run.
     * @param stringDistance The distance of the run.
     * @param stringDate     The date of the run.
     * @throws CustomExceptions.InvalidInput If there is invalid input.
     */
    public Run(String stringTime, String stringDistance, String stringDate) throws CustomExceptions.InvalidInput {
        super(stringDate);
        times = splitRunTime(stringTime);
        distance = Double.parseDouble(stringDistance);
        pace = calculatePace();
    }

    /**
     * Returns string format of time taken for run depending on {@code isHourPresent}
     * If there isn't an hour present, returns only mm:ss
     * If not returns hh:mm:ss
     *
     * @return Formatted string of the time for the run.
     */
    public String getTimes() {
        if (isHourPresent()) {
            int hours = times[WorkoutConstant.RUN_TIME_FIRST_PART];
            int minutes = times[WorkoutConstant.RUN_TIME_SECOND_PART];
            int seconds = times[WorkoutConstant.RUN_TIME_THIRD_PART];
            return String.format(WorkoutConstant.TIME_WITH_HOURS_FORMAT, hours, minutes, seconds);

        } else {
            int minutes = times[WorkoutConstant.RUN_TIME_FIRST_PART];
            int seconds = times[WorkoutConstant.RUN_TIME_SECOND_PART];
            return String.format(WorkoutConstant.TIME_WITHOUT_HOURS_FORMAT, minutes, seconds);
        }
    }

    /**
     * Retrieves the run distance in two decimal format.
     *
     * @return Run distance.
     */
    public String getDistance() {
        return String.format(WorkoutConstant.TWO_DECIMAL_PLACE_FORMAT, distance);
    }
    /**
     * Retrieves run pace.
     *
     * @return Run pace.
     */
    public String getPace() {
        return pace;
    }

    /**
     * Returns true if hours are present in the time taken for the run.
     *
     * @return true if hours are present, false otherwise.
     */
    public boolean isHourPresent() {
        return isHourPresent;
    }

    /**
     * Sets the boolean variable {@code isHourPresent} to true if hours are present in the time taken for the run.
     * This is set in the {@code splitRunTime()} method which is called during the construction of the Run object.
     *
     * @param hourPresent boolean variable representing if hours are present in the time taken for the run.
     */
    public void setHourPresent(boolean hourPresent) {
        isHourPresent = hourPresent;
    }

    //@@author JustinSoh

    /**
     * Retrieves the string representation of a Run object.
     *
     * @return A formatted string representing a Run object.
     */
    @Override
    public String toString() {
        String printedDate = super.getDate();
        return String.format(WorkoutConstant.RUN_DATA_FORMAT, WorkoutConstant.RUN,
                getTimes(), getDistance(), getPace(), printedDate);
    }

    /**
     * Retrieves the string representation of a Run object when printing all history.
     * Uses {@code WorkoutConstant.HISTORY_WORKOUTS_DATA_FORMAT} to format the string.
     * Ensures that the format of the string is consistent when printing gym and run objects.
     * @return a formatted string representing a Run object.
     */
    public String getFormatForAllHistory() {
        String printedDate = super.getDate();
        return String.format(WorkoutConstant.HISTORY_WORKOUTS_DATA_FORMAT,
                WorkoutConstant.RUN,
                printedDate,
                getDistance(),
                getTimes(),
                getPace()
        );

    }

    //@@author rouvinerh
    // Protected Methods

    /**
     * Method parses the time format in either hh:mm:ss or mm:ss.
     * Sets {@code isHourPresent} variable to true if hours have been specified.
     * Otherwise, set to false.
     *
     * @param inputTime String variable representing time taken in either hh:mm:ss or mm:ss format
     * @return A list of integers representing the hours (if present), minutes and seconds.
     * @throws CustomExceptions.InvalidInput if the input time is not in the correct format.
     */
    protected Integer[] splitRunTime(String inputTime) throws CustomExceptions.InvalidInput {

        String[] stringTimeParts = inputTime.split(WorkoutConstant.COLON);
        int inputLength = stringTimeParts.length;
        Integer[] integerTimes = new Integer[inputLength];

        if (inputLength == UiConstant.MAX_RUNTIME_ARRAY_LENGTH) {
            setHourPresent(true);
            integerTimes[0] = Integer.parseInt(stringTimeParts[WorkoutConstant.RUN_TIME_FIRST_PART]);
            integerTimes[1] = Integer.parseInt(stringTimeParts[WorkoutConstant.RUN_TIME_SECOND_PART]);
            integerTimes[2] = Integer.parseInt(stringTimeParts[WorkoutConstant.RUN_TIME_THIRD_PART]);
        } else if (inputLength == UiConstant.MIN_RUNTIME_ARRAY_LENGTH) {
            setHourPresent(false);
            integerTimes[0] = Integer.parseInt(stringTimeParts[WorkoutConstant.RUN_TIME_FIRST_PART]);
            integerTimes[1] = Integer.parseInt(stringTimeParts[WorkoutConstant.RUN_TIME_SECOND_PART]);
        } else {
            throw new CustomExceptions.InvalidInput(WorkoutConstant.INVALID_RUN_TIME);
        }
        return integerTimes;
    }

    /**
     * Method calculates the pace of the run, and formats it into M:SS/km.
     *
     *
     * @return Formatted string the pace of the run.
     * @throws CustomExceptions.InvalidInput If the total time taken or pace calculated is too large or small.
     */
    protected String calculatePace() throws CustomExceptions.InvalidInput {
        int totalSeconds = calculateTotalSeconds();
        if (totalSeconds <= WorkoutConstant.MIN_RUN_TIME_IN_SECONDS) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.ZERO_RUN_TIME_ERROR);
        }

        if (totalSeconds >= WorkoutConstant.MAX_RUN_TIME_IN_SECONDS) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.MAX_RUN_TIME_ERROR);
        }

        double paceInDecimal = ((double) totalSeconds / this.distance) / UiConstant.NUM_SECONDS_IN_MINUTE;

        if (paceInDecimal > WorkoutConstant.MAX_PACE) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.MAX_PACE_ERROR);
        }
        if (paceInDecimal < WorkoutConstant.MIN_PACE) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.MIN_PACE_ERROR);
        }

        int minutes = (int) paceInDecimal;
        double remainingSeconds = paceInDecimal - minutes;
        int seconds = (int) Math.round(remainingSeconds * UiConstant.NUM_SECONDS_IN_MINUTE);

        return String.format(WorkoutConstant.RUN_PACE_FORMAT, minutes, seconds);
    }

    /**
     * Returns the total seconds based on the {@code times} taken for the run.
     *
     *
     * @return The total number of seconds in the run.
     */
    protected int calculateTotalSeconds() {
        int totalSeconds;

        if (isHourPresent()) {
            totalSeconds = this.times[WorkoutConstant.RUN_TIME_FIRST_PART] * UiConstant.NUM_SECONDS_IN_HOUR
                    + this.times[WorkoutConstant.RUN_TIME_SECOND_PART] * UiConstant.NUM_SECONDS_IN_MINUTE
                    + this.times[WorkoutConstant.RUN_TIME_THIRD_PART];
        } else {
            totalSeconds = this.times[WorkoutConstant.RUN_TIME_FIRST_PART] * UiConstant.NUM_SECONDS_IN_MINUTE
                    + this.times[WorkoutConstant.RUN_TIME_SECOND_PART];
        }
        return totalSeconds;
    }
}
