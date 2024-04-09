package ui;

import constants.ErrorConstant;
import constants.UiConstant;
import constants.WorkoutConstant;
import constants.HealthConstant;
import utility.CustomExceptions;

import workouts.Gym;
import workouts.GymStation;
import workouts.Run;
import workouts.Workout;
import workouts.WorkoutList;
import health.HealthList;
import health.Bmi;
import health.Period;
import health.Appointment;
import utility.Filters.HistoryAndLatestFilters;

import java.util.ArrayList;

/**
 * The Output class handles printing various messages, data, and ASCII art for the user interface.
 */
public class Output {

    //@@author L5-Z
    /**
     * Prints a horizontal line.
     */
    public static void printLine() {
        System.out.println(UiConstant.PARTITION_LINE);
    }

    /**
     * Prints the help message.
     */
    public void printHelp() {
        printLine();
        System.out.println("Commands List:");
        System.out.println();
        System.out.println("workout /e:run /d:DISTANCE /t:TIME [/date:DATE] - Add a new run");
        System.out.println("workout /e:gym /n:NUMBER_OF_STATIONS [/date:DATE] - Add a new gym workout");
        System.out.println("health /h:bmi /height:HEIGHT /weight:WEIGHT /date:DATE - Add new BMI data");
        System.out.println("health /h:period /start:START_DATE [/end:END_DATE] - Add new period data");
        System.out.println("health /h:prediction - Predicts next period's start date");
        System.out.println("health /h:appointment /date:DATE /time:TIME /description:DESCRIPTION" +
                " - Add new appointment data");
        System.out.println("history /item:[run/gym/bmi/period] - " +
                "Shows history of runs/gyms/bmi records/periods tracked/appointment records");
        System.out.println("latest /item:[run/gym/bmi/period] - " +
                "Shows latest entry of runs/gyms/bmi records/periods tracked/appointment records");
        System.out.println("help - Show this help message");
        System.out.println("exit - Exit the program");
        printLine();
    }

    /**
     * Prints an ASCII Art depicting the word 'PulsePilot'.
     */
    public void printArt() {
        System.out.println(" _              _");
        System.out.println("|_)    |  _  _ |_) o  |  _ _|_");
        System.out.println("|  |_| | _> (/_|   |  | (_) |_");
    }

    //@@author JustinSoh
    /**
     * Prints the gym station prompt.
     *
     * @param stationNumber Integer representing the current gym station index.
     */
    public void printGymStationPrompt(int stationNumber) {
        printLine();
        System.out.println("Please enter the details of station "
                + stationNumber
                + ". (Format: " + WorkoutConstant.STATION_GYM_FORMAT + ")");
        printLine();
    }

    /**
     * Returns the formatted string for printing runs.
     *
     * @param index          The index of the run.
     * @param currentWorkout The current Workout object within the list.
     * @return A string
     */
    private String getFormattedRunWithIndex(int index, Workout currentWorkout) {
        return String.format(WorkoutConstant.RUN_DATA_INDEX_FORMAT, index, currentWorkout);
    }

    /**
     * Prints the text header when adding a new Run.
     *
     * @param newRun The new Run object added.
     */
    public void printAddRun(Run newRun) {
        printLine();
        System.out.println(WorkoutConstant.ADD_RUN);
        System.out.println(WorkoutConstant.RUN_HEADER);
        System.out.println(newRun);
        printLine();
    }

    //@@author j013n3
    /**
     * Prints the message when a new Bmi is added.
     *
     * @param newBmi The new Bmi object added.
     */
    public void printAddBmi(Bmi newBmi) {
        printLine();
        System.out.println(HealthConstant.BMI_ADDED_MESSAGE_PREFIX
                + newBmi.getHeight()
                + UiConstant.LINE
                + newBmi.getWeight()
                + UiConstant.LINE
                + newBmi.getDate());
        System.out.println(newBmi);
        printLine();
    }

    /**
     * Prints the message when a new Period is added.
     *
     * @param newPeriod The new Period object added.
     */
    public void printAddPeriod(Period newPeriod) {
        printLine();
        System.out.println(HealthConstant.PERIOD_ADDED_MESSAGE_PREFIX
                + newPeriod.getStartDate()
                + UiConstant.LINE
                + (newPeriod.getEndDate() == null ? ErrorConstant.NO_DATE_SPECIFIED_ERROR : newPeriod.getEndDate()));
        System.out.println(newPeriod);
        if (newPeriod.getEndDate() != null) {
            printPeriodWarning(newPeriod);
        }
        printLine();
    }

    /**
     * Prints the message when period length is not within the healthy range.
     *
     * @param newPeriod The new Period object added.
     */
    public void printPeriodWarning(Period newPeriod) {
        if (newPeriod.getPeriodLength() < 2 || newPeriod.getPeriodLength() > 7) {
            System.out.println("\u001b[31m" + HealthConstant.PERIOD_TOO_LONG_MESSAGE + "\u001b[0m");
        }
    }

    //@@author syj02
    /**
     * Prints the message when a new Appointment is added.
     *
     * @param newAppointment The new Appointment object added.
     */
    public void printAddAppointment(Appointment newAppointment) {
        printLine();
        System.out.println(HealthConstant.APPOINTMENT_ADDED_MESSAGE_PREFIX
                + newAppointment.getDate()
                + UiConstant.LINE
                + newAppointment.getTime()
                + UiConstant.LINE
                + newAppointment.getDescription());
        System.out.println(newAppointment);
        printLine();
    }

    //@@author JustinSoh
    /**
     * Prints the text header when adding a new Gym.
     *
     * @param gym The new Gym object added.
     */
    public void printAddGym(Gym gym) {
        printLine();
        System.out.println(WorkoutConstant.ADD_GYM);
        printGymStats(gym);
        printLine();
    }

    /**
     * Prints all Workout objects (Run and Gym) based on the time it was added.
     * The list is sorted in descending order. (Latest one first)
     *
     * @throws CustomExceptions.OutOfBounds  If index is out of bounds.
     * @throws CustomExceptions.InvalidInput If user input is invalid.
     */
    protected void printWorkoutHistory() throws CustomExceptions.OutOfBounds, CustomExceptions.InvalidInput {
        printLine();
        System.out.println(WorkoutConstant.HISTORY_WORKOUTS_HEADER);
        System.out.println(WorkoutConstant.HISTORY_WORKOUTS_HEADER_FORMAT);

        ArrayList<? extends Workout> workoutList = WorkoutList.getWorkouts(WorkoutConstant.ALL);
        for (int i = 0; i < workoutList.size(); i++) {
            Workout workout = workoutList.get(i);
            if (workout instanceof Run) {
                Run run = (Run) workout;
                System.out.printf((WorkoutConstant.HISTORY_WORKOUTS_DATA_HEADER_FORMAT) + "%n",
                        (i + 1), run.getFormatForAllHistory());
            } else {
                Gym gym = (Gym) workout;
                int numberOfStation = gym.getStations().size();
                for (int j = 0; j < numberOfStation; j++) {
                    String gymString;
                    if (j == 0) {
                        gymString = String.format(WorkoutConstant.HISTORY_WORKOUTS_DATA_HEADER_FORMAT,
                                (i + 1), gym.getHistoryFormatForSpecificGymStation(j));
                    } else {
                        gymString = String.format(WorkoutConstant.HISTORY_WORKOUTS_DATA_HEADER_FORMAT,
                                "", gym.getHistoryFormatForSpecificGymStation(j));
                    }
                    System.out.println(gymString);
                }
            }
        }
        printLine();
    }

    /**
     * Prints all the Run objects added to the list.
     *
     * @throws CustomExceptions.OutOfBounds  If index is out of bounds.
     * @throws CustomExceptions.InvalidInput If user input is invalid.
     */
    protected void printRunHistory() throws CustomExceptions.OutOfBounds, CustomExceptions.InvalidInput {
        printLine();
        System.out.println("Your run history:");
        ArrayList<? extends Workout> workoutList = WorkoutList.getWorkouts(WorkoutConstant.RUN);
        String runHeader = String.format(WorkoutConstant.RUN_HEADER_INDEX_FORMAT);
        System.out.println(runHeader);

        for (int i = 0; i < workoutList.size(); i++) {
            int index = i + 1;
            Workout currentWorkout = workoutList.get(i);
            String output = getFormattedRunWithIndex(index, currentWorkout);
            System.out.println(output);
        }
        printLine();
    }

    /**
     * Prints all the stations within a specified Gym object.
     *
     * @param gym The Gym object containing the GymStation objects to be printed.
     */
    protected void printGymStats(Gym gym) {
        ArrayList<GymStation> allStations = gym.getStations();
        for (int i = 0; i < allStations.size(); i++) {
            System.out.printf("Station %d %s%n", i + 1, allStations.get(i).toString());
        }
    }

    /**
     * Prints all the information for all Gym objects within the list.
     *
     * @throws CustomExceptions.OutOfBounds  If index is out of bounds.
     * @throws CustomExceptions.InvalidInput If user input is invalid.
     */
    protected void printGymHistory() throws CustomExceptions.OutOfBounds, CustomExceptions.InvalidInput {
        printLine();
        System.out.println("Your gym history:");
        ArrayList<? extends Workout> workoutList = WorkoutList.getWorkouts(WorkoutConstant.GYM);
        for (int i = 0; i < workoutList.size(); i++) {
            int index = i + 1;
            Gym currentWorkout = (Gym) workoutList.get(i);
            System.out.println("Gym Session " + index + currentWorkout);
            printGymStats(currentWorkout);
            if (i != workoutList.size() - 1) {
                printLine();
            }
        }
        printLine();
    }

    //@@author j013n3
    /**
     * Prints all Bmi objects recorded.
     *
     * @throws CustomExceptions.OutOfBounds  If there is access to a Bmi object that does not exist.
     * @throws CustomExceptions.InvalidInput If there is invalid input.
     */
    protected void printBmiHistory() throws CustomExceptions.OutOfBounds, CustomExceptions.InvalidInput {
        printLine();
        try {
            HealthList.showBmiHistory();
        } catch (CustomExceptions.OutOfBounds e) {
            System.out.println(e.getMessage());
        }
        printLine();
    }

    /**
     * Prints all Period objects recorded.
     *
     * @throws CustomExceptions.OutOfBounds  If there is access to a Period object that does not exist.
     * @throws CustomExceptions.InvalidInput If there is invalid input.
     */
    protected void printPeriodHistory() throws CustomExceptions.OutOfBounds, CustomExceptions.InvalidInput {
        printLine();
        try {
            HealthList.showPeriodHistory();
        } catch (CustomExceptions.OutOfBounds e) {
            System.out.println(e.getMessage());
        }
        printLine();
    }

    //@@author syj02
    /**
     * Prints all Appointment objects recorded.
     *
     * @throws CustomExceptions.OutOfBounds  If there is access to an Appointment object that does not exist.
     * @throws CustomExceptions.InvalidInput If there is invalid input.
     */
    protected void printAppointmentHistory() throws CustomExceptions.OutOfBounds, CustomExceptions.InvalidInput {
        printLine();
        try {
            HealthList.showAppointmentList();
        } catch (CustomExceptions.OutOfBounds e) {
            System.out.println(e.getMessage());
        }
        printLine();
    }

    //@@author rouvinerh
    /**
     * Prints the latest Run recorded.
     */
    protected void printLatestRun() {
        printLine();
        try {

            Workout latestRun = WorkoutList.getLatestRun();
            String latestRunString = getFormattedRunWithIndex(WorkoutList.getRunSize(), latestRun);
            System.out.println("Your latest run:");
            System.out.println(WorkoutConstant.RUN_HEADER_INDEX_FORMAT);
            System.out.println(latestRunString);

        } catch (CustomExceptions.OutOfBounds e) {
            System.out.println(e.getMessage());
        }
        printLine();
    }

    /**
     * Prints the latest Gym recorded.
     */
    protected void printLatestGym() {
        printLine();
        try {
            Gym latestGym = WorkoutList.getLatestGym();
            int index = WorkoutList.getGymSize();
            System.out.println("Your latest gym:");
            System.out.println("Gym Session " + index + latestGym);
            printGymStats(latestGym);
        } catch (CustomExceptions.OutOfBounds e) {
            System.out.println(e.getMessage());
        }
        printLine();
    }

    /**
     * Prints the latest BMI entry recorded.
     */
    protected void printLatestBmi() {
        printLine();
        try {
            HealthList.showCurrentBmi();
        } catch (CustomExceptions.OutOfBounds e) {
            System.out.println(e.getMessage());
        }
        printLine();
    }

    /**
     * Prints the latest Period entry recorded.
     */
    protected void printLatestPeriod() {
        printLine();
        try {
            HealthList.showLatestPeriod();
        } catch (CustomExceptions.OutOfBounds e) {
            System.out.println(e.getMessage());
        }
        printLine();
    }

    /**
     * Prints the latest Appointment entry recorded.
     */
    protected void printEarliestAppointment(){
        printLine();
        try {
            HealthList.showEarliestAppointment();
        } catch (CustomExceptions.OutOfBounds e) {
            System.out.println(e.getMessage());
        }
        printLine();
    }

    /**
     * Handler function to print the latest entry of Run, Gym, Period, or BMI objects recorded.
     *
     * @param filter String used to determine the latest Run, Gym, Period, or BMI objects is to be printed.
     */
    public void printLatest(String filter) {
        try {
            HistoryAndLatestFilters parsedFilter = HistoryAndLatestFilters.valueOf(filter.toUpperCase());
            switch (parsedFilter) {
            case RUN:
                printLatestRun();
                break;

            case GYM:
                printLatestGym();
                break;

            case BMI:
                printLatestBmi();
                break;

            case PERIOD:
                printLatestPeriod();
                break;

            case APPOINTMENT:
                printEarliestAppointment();
                break;

            default:
                throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_LATEST_FILTER_ERROR);
            }
        } catch (CustomExceptions.InvalidInput e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handler function to print all entries of Run, Gym, Period, or BMI objects recorded.
     *
     * @param filter String used to determine if all Run, Gym, Period, or BMI objects are to be printed.
     */
    public void printHistory(String filter) {
        try {
            HistoryAndLatestFilters parsedFilter = HistoryAndLatestFilters.valueOf(filter.toUpperCase());
            switch (parsedFilter) {
            case WORKOUTS:
                printWorkoutHistory();
                break;
            case RUN:
                printRunHistory();
                break;

            case GYM:
                printGymHistory();
                break;

            case BMI:
                printBmiHistory();
                break;

            case PERIOD:
                printPeriodHistory();
                break;

            case APPOINTMENT:
                printAppointmentHistory();
                break;

            default:
                break;
            }
        } catch (CustomExceptions.OutOfBounds | CustomExceptions.InvalidInput e) {
            printException(e.getMessage());
        }
    }

    //@@author JustinSoh
    /**
     * Prints a specified message and the exception error message.
     *
     * @param message The custom message to be printed.
     */
    public void printException(String message) {
        System.err.println("Exception Caught!" + System.lineSeparator() + message);
    }

    //@@author L5-Z
    /**
     * Prints the welcome banner for PulsePilot.
     */
    public void printWelcomeBanner() {
        printLine();
        printArt();
        System.out.println("Engaging orbital thrusters...");
        System.out.println("PulsePilot on standby");
        printLine();
    }

    /**
     * Checks whether storage file is present, and prints corresponding message.
     *
     * @param status Integer representing whether the storage file has been loaded. If set to 0, file is present. Else,
     *               file is not present.
     */
    public void printGreeting(int status, String name) {
        if (status == 0) {
            System.out.println("Welcome back, Captain " + name);
            System.out.println(UiConstant.SUCCESSFUL_LOAD);
        } else {
            System.out.println(UiConstant.MISSING_FILE);
        }
        printLine();
    }

    /**
     * Prints the goodbye message for PulsePilot.
     */
    public void printGoodbyeMessage() {
        printLine();
        System.out.println("PulsePilot successful touchdown");
        System.out.println("See you soon, Captain!");
        printLine();
    }
}
