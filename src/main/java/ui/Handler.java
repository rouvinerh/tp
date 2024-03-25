package ui;

import health.Bmi;
import health.Health;
import health.HealthList;
import health.Period;
import utility.CustomExceptions;
import utility.ErrorConstant;
import utility.UiConstant;
import utility.HealthConstant;
import utility.WorkoutConstant;
import utility.Command;
import utility.Filters;
import workouts.WorkoutList;
import workouts.Gym;
import workouts.Run;
import java.util.Scanner;
import storage.LogFile;


/**
 * Represents user input parsing and handling
 * before providing feedback to the user.
 */
public class Handler {
    public static Scanner in;
    static LogFile logFile = LogFile.getInstance();

    /**
     * Processes user input and filters for valid command words from enum {@code Command},
     * then creates the relevant object based on details entered.
     *
     * @throws IllegalArgumentException If an error occurs during command processing.
     */
    public static void processInput() {
        while (in.hasNextLine()) {
            String userInput = in.nextLine();
            String instruction = userInput.toUpperCase().split(UiConstant.SPLIT_BY_WHITESPACE)[0];
            LogFile.writeLog("User Input: " + userInput, false);

            assert userInput != null : "Object cannot be null";

            try {
                Command command = Command.valueOf(instruction);
                switch (command) {
                case EXIT:
                    System.out.println(UiConstant.EXIT_MESSAGE);
                    return;

                case NEW:
                    handleExercise(userInput);
                    break;

                case HEALTH:
                    handleHealth(userInput);
                    break;

                case HISTORY:
                    handleHistory(userInput);
                    break;

                case LATEST:
                    handleLatest(userInput);
                    break;

                case DELETE:
                    handleDelete(userInput);
                    break;
                    // delete /item:gym/health/bmi /index:1

                case HELP:
                    Output.printHelp();
                    break;

                default:
                    break; // valueOf results in immediate exception for non-match with enum Command
                }
            } catch (IllegalArgumentException | CustomExceptions.InvalidInput e) {
                Output.printException(e, ErrorConstant.INVALID_COMMAND_ERROR);
            }
        }
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

    /**
     * Constructs either a new Run or Gym object based on the user input.
     *
     * @param userInput The user input string.
     */
    public static void handleExercise(String userInput) {
        try {
            String typeOfExercise = checkTypeOfExercise(userInput);
            if (typeOfExercise.equals(WorkoutConstant.RUN)) {
                String[] runDetails = Run.getRun(userInput);
                if (runDetails[0].isEmpty() || runDetails[1].isEmpty() || runDetails[2].isEmpty()
                        || runDetails[3].isEmpty()) {
                    throw new CustomExceptions.InvalidInput(ErrorConstant.UNSPECIFIED_PARAMETER_ERROR);
                }

                Run newRun = new Run(runDetails[2], runDetails[1], runDetails[3]);
                Output.printAddRun(newRun);

            } else if (typeOfExercise.equals(WorkoutConstant.GYM)) {
                int numberOfStations = getNumberOfGymStations(userInput);
                Gym gym = new Gym();
                getGymStation(numberOfStations, gym);
            }
        } catch (CustomExceptions.InvalidInput | CustomExceptions.InsufficientInput e) {
            Output.printException(e, e.getMessage());
        }
    }

    /**
     * Handles history command.
     * Show history of all exercises, run or gym.
     *
     * @param userInput The user input string.
     */
    public static void handleHistory(String userInput) {
        String filter = processHistoryAndLatestInput(userInput);
        if (filter != null) {
            Output.printHistory(filter);
        }
    }

    /**
     * Parses and validates user input for the delete command. Returns a list of parsed user input containing the
     * filter string and the index.
     *
     * @param userInput The user input string.
     * @return The filter string, set to either 'gym', 'run', 'bmi' or 'period'.
     */
    public static String[] parseDeleteInput(String userInput) {
        String[] parsedInputs = new String[2];
        try {
            String[] inputs = userInput.split(UiConstant.SPLIT_BY_SLASH);
            if (inputs.length != 3) {
                throw new CustomExceptions.InsufficientInput("Invalid command format. " +
                        "Usage: delete /item:filter /index:index");
            }

            String[] itemSplit = inputs[1].split(UiConstant.SPLIT_BY_COLON);
            if (itemSplit.length != 2 || !itemSplit[0].equalsIgnoreCase("item")) {
                throw new CustomExceptions.InvalidInput("Invalid item specification. " +
                        "Use /item:run/gym/period/bmi");
            }
            parsedInputs[0] = itemSplit[1].trim();

            String[] indexSplit = inputs[2].split(UiConstant.SPLIT_BY_COLON);
            if (indexSplit.length != 2 || !indexSplit[0].equalsIgnoreCase("index")) {
                throw new CustomExceptions.InvalidInput("Invalid index specification.");
            }

            try {
                Integer.parseInt(indexSplit[1].trim());
            } catch (NumberFormatException e) {
                throw new CustomExceptions.InvalidInput("Index must be a valid positive integer!");
            }

            parsedInputs[1] = indexSplit[1].trim();
            return parsedInputs;

        } catch (CustomExceptions.InvalidInput | CustomExceptions.InsufficientInput e) {
            return null;
        }
    }

    /**
     * Handles the delete command.
     * Deletes an item stored within PulsePilot.
     *
     * @param userInput The user input string.
     */
    public static void handleDelete(String userInput) throws CustomExceptions.InvalidInput {
        String[] parsedInputs = parseDeleteInput(userInput);
        if (parsedInputs != null) {
            Filters parsedFilter = Filters.valueOf(parsedInputs[0].toUpperCase());
            int index = Integer.parseInt(parsedInputs[1]) - 1;
            try {
                switch (parsedFilter) {
                case BMI:
                    HealthList.deleteBmi(index);
                    break;

                case PERIOD:
                    HealthList.deletePeriod(index);
                    break;

                case GYM:
                    WorkoutList.deleteGym(index);
                    break;

                case RUN:
                    WorkoutList.deleteRun(index);
                    break;

                default:
                    break;
                }
            } catch (CustomExceptions.OutOfBounds e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new CustomExceptions.InvalidInput("Invalid delete command input!");
        }

    }

    /**
     * Handles user input related to health data. Parses the user input to determine
     * the type of health data and processes it accordingly.
     *
     * @param userInput A string containing health data information of user.
     */
    public static void handleHealth(String userInput) {
        Output.printLine();
        try {
            String typeOfHealth = Health.checkTypeOfHealth(userInput);
            if (typeOfHealth.equals(HealthConstant.BMI)){
                String[] bmiDetails = Bmi.getBmi(userInput);

                if (bmiDetails[0].isEmpty()
                        || bmiDetails[1].isEmpty()
                        || bmiDetails[2].isEmpty()
                        || bmiDetails[3].isEmpty()) {
                    throw new CustomExceptions.InvalidInput(ErrorConstant.UNSPECIFIED_PARAMETER_ERROR);
                }

                Bmi newBmi = new Bmi(bmiDetails[1], bmiDetails[2], bmiDetails[3]);
                HealthList.addBmi(newBmi);
                System.out.println(HealthConstant.BMI_ADDED_MESSAGE_PREFIX
                        + bmiDetails[1]
                        + UiConstant.LINE
                        + bmiDetails[2]
                        + UiConstant.LINE
                        + bmiDetails[3]);
                System.out.println(newBmi);
            } else if (typeOfHealth.equals(HealthConstant.PERIOD)){
                String[] periodDetails = Period.getPeriod(userInput);

                if (periodDetails[0].isEmpty() || periodDetails[1].isEmpty() || periodDetails[2].isEmpty()) {
                    throw new CustomExceptions.InvalidInput(ErrorConstant.UNSPECIFIED_PARAMETER_ERROR);
                }

                Period newPeriod = new Period(periodDetails[1], periodDetails[2]);
                if (newPeriod.getStartDate().isAfter(newPeriod.getEndDate())) {
                    throw new CustomExceptions.InvalidInput(HealthConstant.PERIOD_START_MUST_BE_BEFORE_END);
                }

                HealthList.addPeriod(newPeriod);
                System.out.println(HealthConstant.PERIOD_ADDED_MESSAGE_PREFIX
                        + periodDetails[1]
                        + UiConstant.LINE
                        + periodDetails[2]);
                System.out.println(newPeriod);
            }
        } catch (CustomExceptions.InvalidInput | CustomExceptions.InsufficientInput e) {
            Output.printException(e, e.getMessage());
        }
        Output.printLine();
    }

    /**
     * Retrieves the number of gym stations in one Gym object from user input.
     *
     * @param input The user input string.
     * @throws CustomExceptions.InsufficientInput If the user input is insufficient.
     * @throws CustomExceptions.InvalidInput      If the user input is invalid or blank.
     */
    //@@author JustinSoh
    public static int getNumberOfGymStations(String input) throws CustomExceptions.InsufficientInput,
            CustomExceptions.InvalidInput {
        String numberOfStationString = extractSubstringFromSpecificIndex(input, WorkoutConstant.STATION_DELIMITER);
        assert Integer.parseInt(numberOfStationString) > 0 : ErrorConstant.NEGATIVE_VALUE_ERROR;
        return Integer.parseInt(numberOfStationString);
    }

    //@@author JustinSoh
    /**
     * Retrieves the gym station details and adds a GymStation object to Gym.
     *
     * @param numberOfStations The number of stations in one gym session.
     * @param gym The Gym object.
     */
    private static void getGymStation(int numberOfStations, Gym gym) {
        try{
            for (int i = 0; i < numberOfStations; i++) {
                Output.printGymStationPrompt(i + 1);
                String userInput = in.nextLine();
                String[] inputs = userInput.split(UiConstant.SPLIT_BY_SLASH);
                String[] validatedInputs = Gym.checkGymStationInput(inputs);
                Gym.addGymStationInput(validatedInputs, gym);
            }
            Output.printAddGym(gym);
        } catch (CustomExceptions.InsufficientInput | CustomExceptions.InvalidInput e) {
            Output.printException(e, e.getMessage());
        }
    }

    //@@author JustinSoh

    /**
     * Function validates and parses the user input for the history and latest commands.
     *
     * @param userInput String representing the user input.
     * @return The filter string, set to either 'gym', 'run', 'bmi' or 'period'.
     */
    public static String processHistoryAndLatestInput(String userInput) {
        try {
            String[] inputs = userInput.split(UiConstant.SPLIT_BY_SLASH);
            if (inputs.length != 2) {
                throw new CustomExceptions.InsufficientInput("Invalid command format. " +
                        "Usage: history/latest /view:filter");
            }

            String[] filterSplit = inputs[1].split(UiConstant.SPLIT_BY_COLON);
            if (filterSplit.length != 2 || !filterSplit[0].equalsIgnoreCase("view")) {
                throw new CustomExceptions.InvalidInput("Invalid filter or flag used!" +
                        "Use /view:run/gym/period/bmi");
            }
            return filterSplit[1];
        } catch (CustomExceptions.InvalidInput | CustomExceptions.InsufficientInput e) {
            System.err.println(e.getMessage());
            return null;
        }

    }

    /**
     * Prints the latest run, gym, BMI entry or Period tracked.
     *
     * @param userInput String representing user input.
     */
    public static void handleLatest(String userInput) {
        String filter = processHistoryAndLatestInput(userInput);
        if (filter != null) {
            Output.printLatest(filter);
        }
    }


    //@@author
    /**
     * Checks the type of exercise based on the user input.
     * Usage: to use this method whenever the user enters a new exercise.
     * Handles all the checks for input validity and sufficiency.
     * Can assume input is valid and sufficient if no exceptions are thrown.
     *
     * @param userInput The user input string.
     * @return The type of exercise {@code Constant.RUN} or {@code Constant.GYM}.
     * @throws CustomExceptions.InvalidInput If the user input is invalid or blank.
     * @throws CustomExceptions.InsufficientInput If the user input is insufficient.
     */
    public static String checkTypeOfExercise(String userInput) throws
            CustomExceptions.InvalidInput,
            CustomExceptions.InsufficientInput {
        String[] userInputs = userInput.split(UiConstant.SPLIT_BY_SLASH);

        if (userInputs.length < 2) {
            throw new CustomExceptions.InvalidInput(WorkoutConstant.INVALID_INPUT_FOR_EXERCISE);
        }

        String exerciseType = userInputs[WorkoutConstant.EXERCISE_TYPE_INDEX].trim();

        if (exerciseType.isBlank()){
            throw new CustomExceptions.InvalidInput(WorkoutConstant.BLANK_INPUT_FOR_EXERCISE);
        }

        exerciseType = exerciseType.toLowerCase();
        boolean isRun = exerciseType.equals(WorkoutConstant.RUN_INPUT);
        boolean isGym = exerciseType.equals(WorkoutConstant.GYM_INPUT);
        if(!isRun && !isGym){
            throw new CustomExceptions.InvalidInput(WorkoutConstant.INVALID_INPUT_FOR_EXERCISE);
        }

        if (isRun && userInputs.length < 5) {
            throw new CustomExceptions.InsufficientInput(WorkoutConstant.INSUFFICIENT_PARAMETERS_FOR_RUN);
        }

        if (isGym && userInputs.length < 3) {
            throw new CustomExceptions.InsufficientInput(WorkoutConstant.INSUFFICIENT_PARAMETERS_FOR_GYM);
        }


        if (isRun){
            return WorkoutConstant.RUN;
        } else {
            return WorkoutConstant.GYM;
        }
    }

    /**
     * Get user's name, and print profile induction messages.
     */
    public static void userInduction() {
        String name = in.nextLine();
        System.out.println("Welcome aboard, Captain " + name);
        LogFile.writeLog("Name entered: " + name, false);
        Output.printLine();

        System.out.println("Tips: Enter 'help' to view the pilot manual!");
        System.out.println("Initiating FTL jump sequence...");

        LogFile.writeLog("Name Entered: " + name, false);
        System.out.println("FTL jump completed.");
    }

    /**
     * Initialise scanner to read user input.
     */
    public static void initialiseScanner(){
        in = new Scanner(System.in);
        assert in != null : "Object cannot be null";
    }

    /**
     * Close scanner to stop reading user input.
     */
    public static void destroyScanner(){
        in.close();
    }

    /**
     * Initializes PulsePilot by printing a welcome message, loading tasks from storage,
     * and returning the tasks list.
     */
    public static void initialiseBot() {
        Output.printWelcomeBanner();
        initialiseScanner();
        LogFile.writeLog("Started bot", false);
        // Yet to implement : Check for existing save, if not, make a new one
        // Yet to implement : int status = Storage.load();
        int status = 1;
        Output.printGreeting(1);

        if (status == 1) {
            userInduction();
        }
        System.out.println("Terminal primed. Command inputs are now accepted...");
        Output.printLine();
    }

    /**
     * Terminates PulsePilot by saving tasks to storage, printing a goodbye message,
     * and indicating the filename where tasks are saved.
     */
    public static void terminateBot() {
        // Yet to implement : Storage.saveTasks(tasks);
        Output.printGoodbyeMessage();
        // Yet to implement : Reply.printReply("Saved tasks as: " + Constant.FILE_NAME);
        LogFile.writeLog("Bot exited gracefully", false);
        destroyScanner();
        System.exit(0);
    }
}
