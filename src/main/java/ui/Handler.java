package ui;

import health.Bmi;
import health.Health;
import health.HealthList;
import utility.Command;
import utility.Constant;
import utility.CustomExceptions;
import workouts.Gym;
import workouts.Run;

import java.util.Scanner;

import storage.LogFile;


/**
 * Represents user input parsing and handling
 * before providing feedback to the user.
 */
public class Handler {
    LogFile logFile = LogFile.getInstance();

    /**
     * Processes user input and filters for valid command words from enum {@code Command},
     * then creates the relevant {@code Task} object based on details entered.
     *
     * @throws IllegalArgumentException If an error occurs during command processing.
     */
    public static void processInput() {
        Scanner in = new Scanner(System.in);


        while (in.hasNextLine()) {
            String userInput = in.nextLine();
            String instruction = userInput.toUpperCase().split(" ")[0];
            LogFile.writeLog("User Input: " + userInput, false);

            try {
                Command command = Command.valueOf(instruction);
                switch (command) {

                case EXIT:
                    return;
                case NEW:

                    handleExercise(userInput);

                    break;
                case HISTORY:

                    handleHistory(userInput);

                    break;
                case LATEST:

                    handleLatest(userInput);

                    break;
                case HEALTH:

                    handleHealth(userInput);

                    break;
                case HELP:

                    Output.printHelp();

                    break;

                default:
                    // Yet to implement : throw new CustomException();
                }
            } catch (IllegalArgumentException e) {
                // Yet to implement : Reply.printException(e, Constant.INVALID_COMMAND);
                // Yet to implement : } catch (CustomException e) {
                // Yet to implement : Reply.printException(e);

            }


        }
    }


    /**
     * Checks the type of exercise based on the user input.
     * Usage: to use this method whenever the user enters a new exercise.
     * Handles all the checks for input validity and sufficiency.
     * Can assume input is valid and sufficient if no exceptions are thrown.
     *
     * @param userInput The user input string.
     * @return The type of exercise {@code Constant.RUN} or {@code Constant.GYM}.
     * @throws CustomExceptions.InvalidInput      If the user input is invalid or blank.
     * @throws CustomExceptions.InsufficientInput If the user input is insufficient.
     */
    public static String checkTypeOfExercise(String userInput) throws
            CustomExceptions.InvalidInput,
            CustomExceptions.InsufficientInput {
        String[] userInputs = userInput.split("/"); // Constant.SPLIT_BY_SLASH = "/"

        String exerciseType = userInputs[Constant.EXERCISE_TYPE_INDEX].trim(); // Constant.EXERCISE_TYPE_INDEX = 1

        if (exerciseType.isBlank()) {
            throw new CustomExceptions.InvalidInput(Constant.BLANK_INPUT_FOR_EXERCISE);
        }

        exerciseType = exerciseType.toLowerCase();


        boolean isRun = exerciseType.equals(Constant.RUN_INPUT);
        boolean isGym = exerciseType.equals(Constant.GYM_INPUT);
        if (!isRun && !isGym) {
            throw new CustomExceptions.InvalidInput(Constant.INVALID_INPUT_FOR_EXERCISE);
        }


        if (isRun && userInputs.length < 5) {
            throw new CustomExceptions.InsufficientInput(Constant.INSUFFICIENT_PARAMETERS_FOR_RUN);
        }

        if (isGym && userInputs.length < 3) {
            throw new CustomExceptions.InsufficientInput(Constant.INSUFFICIENT_PARAMETERS_FOR_GYM);
        }


        if (isRun) {
            return Constant.RUN;
        } else {
            return Constant.GYM;
        }
    }

    /**
     * Constructs a new {@code }  object based on the user input.
     *
     * @param userInput The user input string.
     */
    public static void handleExercise(String userInput) {
        try {
            String typeOfExercise = checkTypeOfExercise(userInput);
            if (typeOfExercise.equals(Constant.RUN)) {
                String[] runDetails = getRun(userInput);
                if (runDetails[0].isEmpty() || runDetails[1].isEmpty() || runDetails[2].isEmpty()
                        || runDetails[3].isEmpty()) {
                    throw new CustomExceptions.InvalidInput("Missing parameter(s)");
                }

                new Run(runDetails[2], runDetails[1], runDetails[3]);
                System.out.println("Added: run | " + runDetails[1] + " | " + runDetails[2] + " | " + runDetails[3]);

            } else if (typeOfExercise.equals(Constant.GYM)) {
                // Yet to implement : handleGym(userInput);
                getGym(userInput);
            }
        } catch (CustomExceptions.InvalidInput | CustomExceptions.InsufficientInput e) {
            System.out.println(e.getMessage());
            // throw new CustomExceptions.InvalidInput(Constant.UNSPECIFIED_PARAMETER);
        }

    }

    public static void handleHistory(String userInput) {
        Output.printHistory("all");
    }

    public static void handleLatest(String userInput) {
        // if asked to show latest run
        Output.printLatestRun();
    }

    public static void handleHealth(String userInput) {
        try {
            String typeOfHealth = Health.checkTypeOfHealth(userInput);
            if (typeOfHealth.equals(Constant.BMI)) {
                String[] bmiDetails = Bmi.getBmi(userInput);

                if (bmiDetails[0].isEmpty() || bmiDetails[1].isEmpty() || bmiDetails[2].isEmpty()) {
                    throw new CustomExceptions.InvalidInput("Missing parameter(s)");
                }
                Bmi newBmi = new Bmi(bmiDetails[1], bmiDetails[2]);
                HealthList.addBmi(newBmi);
                System.out.println("Added: bmi | " + bmiDetails[1] + " | " + bmiDetails[2]);
                System.out.println(newBmi);
            } else if (typeOfHealth.equals(Constant.PERIOD)) {
                // Yet to implement
            }
        } catch (CustomExceptions.InvalidInput | CustomExceptions.InsufficientInput e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Called when a user inputs a new gym
     * Creates a gym object and prompts the user to enter details of the gym workouts and adds that to the gym object .
     *
     * @param input The user input string.
     * @throws CustomExceptions.InsufficientInput If the user input is insufficient.
     * @throws CustomExceptions.InvalidInput      If the user input is invalid or blank.
     */
    public static void getGym(String input) throws CustomExceptions.InsufficientInput, CustomExceptions.InvalidInput {
        // input that I will get is something like new /e: gym /n: 2
        // use get index method that alfattih will push later
        int numberOfStations = 2; // assuming i parsed the user initial input
        Gym gym = new Gym();
        for (int i = 0; i < numberOfStations; i++) {
            Output.printGymStationPrompt(i + 1);
            Scanner in = new Scanner(System.in);
            String userInput = in.nextLine();
            checkAndAddGymStationInput(userInput, gym);
        }
    }

    private static void checkAndAddGymStationInput(String userInput, Gym gym) throws CustomExceptions.InsufficientInput, CustomExceptions.InvalidInput {
        // probably change to alfa method
        String[] inputs = userInput.split(Constant.SPLIT_BY_SLASH); // Constant.SPLIT_BY_SLASH = "/"

        if (inputs.length != Constant.LENGTH_OF_GYM_STATION_INPUTS) {
            throw new CustomExceptions.InsufficientInput(Constant.INSUFFICIENT_PARAMETERS_FOR_GYM_STATION);
        }

        if (inputs[Constant.INDEX_OF_STATION_NAME].isBlank() ||
                inputs[Constant.INDEX_OF_STATION_SETS].isBlank() ||
                inputs[Constant.INDEX_OF_STATION_REPS].isBlank() ||
                inputs[Constant.INDEX_OF_STATION_WEIGHTS].isBlank()) {
            throw new CustomExceptions.InvalidInput(Constant.BLANK_INPUT_FOR_GYM_STATION);
        }

        String exerciseName = inputs[Constant.INDEX_OF_STATION_NAME].trim();

        try {

            int numberOfSets = Integer.parseInt(inputs[Constant.INDEX_OF_STATION_SETS]);
            int repetition = Integer.parseInt(inputs[Constant.INDEX_OF_STATION_REPS]);
            int weights = Integer.parseInt(inputs[Constant.INDEX_OF_STATION_WEIGHTS]);
            gym.addStation(exerciseName, weights, numberOfSets, repetition);
        } catch (NumberFormatException e) {
            throw new CustomExceptions.InvalidInput(Constant.NUMERIC_INPUT_REQUIRED_GYM_STATION);
        }
    }

    /**
     * Parses a string containing run information, extracts the command, distance and end time before returning
     * an array of strings containing the information.
     *
     * @param input A string containing the Run information in the format "new /e:run /d:DISTANCE /t:TIME [/date:DATE]".
     * @return An array of strings containing the extracted command, distance, time taken and date(if given).
     */
    public static String[] getRun(String input) throws CustomExceptions.InvalidInput {

        String[] results = new String[4]; // Constant.RUN_PARAMETERS = 4


        if (!input.contains("/e") || !input.contains("/d") || !input.contains("/t")) {
            throw new CustomExceptions.InvalidInput(Constant.UNSPECIFIED_PARAMETER);
        }


        int indexE = input.indexOf("/e");
        int indexD = input.indexOf("/d");
        int indexT = input.indexOf("/t");
        int indexDate = input.indexOf("/date");

        String command = input.substring(indexE + 3, indexD).trim(); // Constant.RUN_E_OFFSET , "/e:" = 3
        assert !command.isEmpty() : "Command should not be empty";

        String dSubstring = input.substring(indexD + 3, indexT).trim(); // Constant.RUN_D_OFFSET , "/d:" = 3
        assert !dSubstring.isEmpty() : "Distance should not be empty";
        assert dSubstring.matches("\\d+(\\.\\d+)?") : "Distance should be a valid numeric value (assuming KM)";
        String tSubstring = input.substring(indexT + 3, indexDate).trim(); // Constant.RUN_T_OFFSET , "/t:" = 3
        assert !tSubstring.isEmpty() : "Time should not be empty";
        assert tSubstring.matches("\\d{2}:\\d{2}:\\d{2}") : "Time should be in the format HH:MM:SS";

        String dateSubstring = input.substring(indexDate + 6).trim(); // Constant.RUN_DATE_OFFSET , "/date:" = 6


        if (command.isEmpty() || dSubstring.isEmpty() || tSubstring.isEmpty()) {
            //throw new CustomException(Constant.UNSPECIFIED_PARAMETER);
        }


        results[0] = command;
        results[1] = dSubstring;
        results[2] = tSubstring;
        results[3] = dateSubstring;

        return results;
    }

    /**
     * Initializes PulsePilot by printing a welcome message, loading tasks from storage,
     * and returning the tasks list.
     */
    public static void initialiseBot() {
        Output.printWelcomeBanner();
        LogFile.writeLog("Started bot", false);
        // Yet to implement : Check for existing save, if not, make a new one
        // Yet to implement : int status = Storage.load();
        int status = 1;
        if (status == 1) {
            Output.printGreeting(1);
            Scanner in = new Scanner(System.in);
            String name = in.nextLine();
            System.out.println("Welcome aboard, " + name);
            LogFile.writeLog("Name entered: " + name, false);
        }
    }

    /**
     * Terminates PulsePilot by saving tasks to storage, printing a goodbye message,
     * and indicating the filename where tasks are saved.
     */
    public static void terminateBot() {
        LogFile.writeLog("Bot exited gracefully", false);
        // Yet to implement : Storage.saveTasks(tasks);
        // Yet to implement : Reply.printGoodbyeMessage();
        // Yet to implement : Reply.printReply("Saved tasks as: " + Constant.FILE_NAME);
        System.exit(0);
    }
}

