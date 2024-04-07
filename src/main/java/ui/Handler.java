package ui;

import health.HealthList;
import storage.DataFile;
import utility.CustomExceptions;
import constants.ErrorConstant;
import constants.UiConstant;
import constants.HealthConstant;
import constants.WorkoutConstant;
import utility.Filters.Command;
import utility.Filters.DeleteFilters;
import utility.Filters.HealthFilters;
import utility.Parser;
import utility.Filters.WorkoutFilters;
import workouts.WorkoutList;

import java.util.Scanner;
import storage.LogFile;

/**
 * Represents user input parsing and handling before providing feedback to the user.
 */
public class Handler {

    static LogFile logFile = LogFile.getInstance();
    public Scanner in;
    public Parser parser;
    public DataFile dataFile;


    public Handler(){
        in = new Scanner(System.in);
        parser = new Parser(in);
        dataFile = new DataFile();
    }

    public Handler(String input){
        in = new Scanner(input); // use for JUnit Testing
        parser = new Parser(in);
        dataFile = new DataFile();
    }

    /**
     * Processes user input and filters for valid command words from enum {@code Command},
     * then creates the relevant object based on details entered.
     *
     * @throws IllegalArgumentException If an error occurs during command processing.
     */
    public void processInput() {
        while (in.hasNextLine()) {
            String userInput = in.nextLine();
            String instruction = userInput.toUpperCase().split(UiConstant.SPLIT_BY_WHITESPACE)[0];
            LogFile.writeLog("User Input: " + userInput, false);
            assert userInput != null : "Object cannot be null";

            try {
                Command command = Command.valueOf(instruction);
                switch (command) {
                case EXIT:
                    LogFile.writeLog("User ran command: exit", false);
                    System.out.println(UiConstant.EXIT_MESSAGE);
                    return;

                case WORKOUT:
                    handleWorkout(userInput);
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

                case HELP:
                    Output.printHelp();
                    break;

                default:
                    break; // valueOf results in immediate exception for non-match with enum Command
                }
            } catch (CustomExceptions.InvalidInput e) {
                Output.printException(e.getMessage());
            } catch (IllegalArgumentException e) {
                Output.printException(ErrorConstant.INVALID_COMMAND_ERROR);
            }
        }
    }

    //@@author JustinSoh

    /**
     * Handles workout command.
     * Adds a Run or Gym object to PulsePilot.
     *
     * @param userInput The user input string.
     */
    public void handleWorkout(String userInput) {
        try {
            String typeOfWorkout = parser.extractSubstringFromSpecificIndex(userInput,
                    WorkoutConstant.EXERCISE_FLAG);
            WorkoutFilters filter = WorkoutFilters.valueOf(typeOfWorkout.toUpperCase());
            switch(filter) {
            case RUN:
                parser.parseRunInput(userInput);
                break;

            case GYM:
                parser.parseGymInput(userInput);
                break;

            default:
                break;
            }
        } catch (CustomExceptions.InvalidInput | CustomExceptions.InsufficientInput e) {
            Output.printException(e.getMessage());
        } catch (IllegalArgumentException e) {
            Output.printException("Invalid workout type! Please input either /e:run or /e:gym!");
        }
    }

    /**
     * Handles history command.
     * Show history of all exercises, run or gym.
     *
     * @param userInput The user input string.
     */
    public void handleHistory(String userInput) {
        String filter = parser.parseHistoryAndLatestInput(userInput);
        if (filter != null) {
            Output.printHistory(filter);
        }
    }

    /**
     * Handles the delete command.
     * Deletes an item stored within PulsePilot.
     *
     * @param userInput The user input string.
     * @throws CustomExceptions.InvalidInput If the user input is invalid.
     */
    public void handleDelete(String userInput) throws CustomExceptions.InvalidInput {
        String[] parsedInputs = parser.parseDeleteInput(userInput);
        if (parsedInputs == null) {
            return;
        }
        try {
            DeleteFilters filter = DeleteFilters.valueOf(parsedInputs[0].toUpperCase());
            int index = Integer.parseInt(parsedInputs[1]) - 1;
            switch (filter) {
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

            case APPOINTMENT:
                HealthList.deleteAppointment(index);
                break;

            default:
                break;
            }
        } catch (CustomExceptions.OutOfBounds e) {
            Output.printException(e.getMessage());
        }
    }

    //@@author syj02
    /**
     * Handles user input related to health data. Parses the user input to determine
     * the type of health data and processes it accordingly.
     *
     * @param userInput A string containing health data information of user.
     */
    public void handleHealth(String userInput) {
        try {
            String typeOfHealth = parser.extractSubstringFromSpecificIndex(userInput, HealthConstant.HEALTH_FLAG);
            HealthFilters filter = HealthFilters.valueOf(typeOfHealth.toUpperCase());
            switch(filter) {
            case BMI:
                parser.parseBmiInput(userInput);
                break;

            case PERIOD:
                parser.parsePeriodInput(userInput);
                break;

            case PREDICTION:
                parser.parsePredictionInput();
                break;

            case APPOINTMENT:
                parser.parseAppointmentInput(userInput);
                break;

            default:
                break;
            }
        } catch (CustomExceptions.InvalidInput |  CustomExceptions.InsufficientInput e) {
            Output.printException(e.getMessage());
        } catch (IllegalArgumentException e) {
            Output.printException(ErrorConstant.INVALID_HEALTH_INPUT_ERROR);
        }
    }

    //@@author JustinSoh

    /**
     * Prints the latest run, gym, BMI entry or Period tracked.
     *
     * @param userInput String representing user input.
     */
    public void handleLatest(String userInput) {
        String filter = parser.parseHistoryAndLatestInput(userInput);
        if (filter != null) {
            Output.printLatest(filter);
        }
    }

    //@@author

    /**
     * Get user's name, and print profile induction messages.
     */
    public void userInduction() {
        String name = this.in.nextLine();
        DataFile.userName = name;
        System.out.println("Welcome aboard, Captain " + name);
        Output.printLine();

        System.out.println("Tips: Enter 'help' to view the pilot manual!");
        System.out.println("Initiating FTL jump sequence...");

        // DataFile.saveName(name);
        LogFile.writeLog("Name Entered: " + name, false);

        System.out.println("FTL jump completed.");
    }

    /**
     * Initialise scanner to read user input.
     */
    public void initialiseScanner() {
        in = new Scanner(System.in);
        assert this.in != null : "Object cannot be null";
    }

    /**
     * Close scanner to stop reading user input.
     */
    public void destroyScanner() {
        if (this.in != null) {
            assert this.in != null : "Object cannot be null";
            this.in.close();
        }
    }

    /**
     * Initializes PulsePilot by printing a welcome message, loading tasks from storage,
     * and returning the tasks list.
     */
    public void initialiseBot() {
        Output.printWelcomeBanner();
        LogFile.writeLog("Started bot", false);

        int status = dataFile.loadDataFile();

        if (status == 0) {
            try {
                dataFile.readDataFile(); // File read
                Output.printGreeting(status, DataFile.userName);
            } catch (CustomExceptions.FileReadError e) {
                Output.printException(e.getMessage());
            }
        } else {
            Output.printGreeting(status, DataFile.userName);
            userInduction();
        }

        System.out.println("Terminal primed. Command inputs are now accepted...");
        Output.printLine();
    }

    /**
     * Terminates PulsePilot by saving tasks to storage, printing a goodbye message,
     * and indicating the filename where tasks are saved.
     */
    public void terminateBot() {
        LogFile.writeLog("User terminating PulsePilot", false);
        try {
            LogFile.writeLog("Attempting to save data file", false);


            dataFile.saveDataFile(DataFile.userName, HealthList.getBmis(), HealthList.getAppointments(),
                    HealthList.getPeriods(), WorkoutList.getWorkouts());
            LogFile.writeLog("File saved", false);
        } catch (CustomExceptions.FileWriteError e) {
            LogFile.writeLog("File write error", true);
            Output.printException(e.getMessage());
        }
        Output.printGoodbyeMessage();
        // Yet to implement : Reply.printReply("Saved tasks as: " + Constant.FILE_NAME);
        LogFile.writeLog("Bot exited gracefully", false);
        destroyScanner();
        System.exit(0);
    }

}
