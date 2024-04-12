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

    /**
     * Constructs a new Run object with the time and distance from user input.
     *
     * @param stringTime     The time taken for the run.
     * @param stringDistance The distance of the run.
     * @throws CustomExceptions.InvalidInput If there is invalid input.
     */
    public Run(String stringTime, String stringDistance) throws CustomExceptions.InvalidInput {
        times = checkRunTime(stringTime);
        distance = checkDistance(stringDistance);
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
        times = checkRunTime(stringTime);
        distance = Double.parseDouble(stringDistance);
        pace = calculatePace();
    }

    protected Double checkDistance(String stringDistance) throws CustomExceptions.InvalidInput {
        double runDistance = Double.parseDouble(stringDistance);
        if (runDistance > WorkoutConstant.MAX_RUN_DISTANCE) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.DISTANCE_TOO_LONG_ERROR);
        }

        if (runDistance <= WorkoutConstant.MIN_RUN_DISTANCE) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.ZERO_DISTANCE_ERROR);
        }
        return runDistance;
    }

    /**
     * Returns string format of time taken for run depending on {@code isHourPresent}
     * If there isn't an hour present, returns only mm:ss
     * If not returns hh:mm:ss
     *
     * @return Formatted string of the time for the run.
     */
    public String getTimes() {
        if (times[0] > 0) {
            int hours = times[0];
            int minutes = times[1];
            int seconds = times[2];
            return String.format(WorkoutConstant.TIME_WITH_HOURS_FORMAT, hours, minutes, seconds);

        } else {
            int minutes = times[1];
            int seconds = times[2];
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
    protected Integer[] checkRunTime(String inputTime) throws CustomExceptions.InvalidInput {
        String [] parts = inputTime.split(UiConstant.SPLIT_BY_COLON);
        int hours = WorkoutConstant.NO_HOURS_PRESENT;
        int minutes = 0;
        int seconds = 0;

        if (parts.length == WorkoutConstant.NUMBER_OF_PARTS_FOR_RUN_TIME) {
            minutes = Integer.parseInt(parts[0]);
            seconds = Integer.parseInt(parts[1]);
        } else if (parts.length == WorkoutConstant.NUMBER_OF_PARTS_FOR_RUN_TIME_WITH_HOURS) {
            hours = Integer.parseInt(parts[0]);
            minutes = Integer.parseInt(parts[1]);
            seconds = Integer.parseInt(parts[2]);
        }

        Integer[] runTimeParts = new Integer[]{hours, minutes, seconds};
        validateRunTime(runTimeParts);
        return runTimeParts;
    }

    private void validateRunTime(Integer[] runTimeParts) throws CustomExceptions.InvalidInput {
        int hours = runTimeParts[0];
        int minutes = runTimeParts[1];
        int seconds = runTimeParts[2];

        if (hours == UiConstant.MIN_HOURS) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_HOUR_ERROR);
        }

        // minutes can always be 00
        if (minutes > UiConstant.MAX_MINUTES) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_MINUTE_ERROR);
        }

        // seconds can never be > 59
        if (seconds > UiConstant.MAX_SECONDS) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_SECOND_ERROR);
        }
        if (hours == -1) {
            // if hours not present, minutes and seconds cannot be 00
            if (minutes == 0 && seconds == 0) {
                throw new CustomExceptions.InvalidInput(ErrorConstant.ZERO_TIME_ERROR);
            }
        }
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
    private int calculateTotalSeconds() {
        int totalSeconds;

        if (times[0] > 0) {
            totalSeconds = this.times[0] * UiConstant.NUM_SECONDS_IN_HOUR
                    + this.times[1] * UiConstant.NUM_SECONDS_IN_MINUTE
                    + this.times[2];
        } else {
            totalSeconds = this.times[1] * UiConstant.NUM_SECONDS_IN_MINUTE
                    + this.times[2];
        }
        return totalSeconds;
    }
}
