package utility;

/**
 * Class representing the filters used for PulsePilot.
 */
public class Filters {
    //@@author L5-Z
    public enum Command {
        WORKOUT,
        HISTORY,
        LATEST,
        HEALTH,
        DELETE,
        HELP,
        EXIT
    }
    //@@author
    public enum DeleteFilters {
        RUN,
        GYM,
        PERIOD,
        BMI,
        APPOINTMENT,
    }

    public enum HealthFilters {
        PERIOD,
        BMI,
        APPOINTMENT,
        PREDICTION,
    }

    public enum WorkoutFilters {
        RUN,
        GYM,
    }

    public enum HistoryAndLatestFilters {
        RUN,
        GYM,
        PERIOD,
        BMI,
        APPOINTMENT,
        WORKOUTS
    }

    //@@author L5-Z
    public enum DataType {
        BMI,
        APPOINTMENT,
        PERIOD,
        GYM,
        RUN
    }

}
