package workouts;

import constants.ErrorConstant;
import storage.LogFile;
import utility.CustomExceptions;
import constants.UiConstant;
import constants.WorkoutConstant;
import utility.Validation;

import java.util.ArrayList;

/**
 * Represents a Gym object that extends the Workout class.
 * A gym object can have multiple GymStation objects.
 *
 *
 */
public class Gym extends Workout {
    //@@author JustinSoh

    private final ArrayList<GymStation> stations = new ArrayList<>();
    /**
     * Constructs a new Gym Object
     * When a new Gym object is created, it is automatically added to a list of workouts.
     * This is done through the super() call in the constructor.
     *
     */
    public Gym() {
        super();
        super.addIntoWorkoutList(this);
    }

    /**
     * Overloaded constructor that takes the optional date parameter.
     * It also takes in the date parameter specified.
     *
     * @param stringDate String representing the date parameter specified.
     */
    public Gym(String stringDate) {
        super(stringDate);
        workout.addIntoWorkoutList(this);
    }

    /**
     * Adds station to an ArrayList of GymStation object.
     *
     * @param name Name of the gym station.
     * @param numberOfSet Number of sets done.
     * @param numberOfRepetitions Number of repetitions done.
     * @param weightsList Weights used for the station.
     */
    public void addStation(String name,
                           String numberOfSet,
                           String numberOfRepetitions,
                           String weights)
            throws CustomExceptions.InsufficientInput,
            CustomExceptions.InvalidInput {
        String validExerciseName  = validateGymStationName(name);
        int validNumberOfSets = validateNumberOfSets(numberOfSet);
        int validNumberOfReps = validateNumberOfRepetitions(numberOfRepetitions);
        ArrayList<Double> validWeights = processWeightsArray(weights);

        checkIfNumberOfWeightsMatchesSets(validWeights, validNumberOfSets);
        GymStation newStation = new GymStation(validExerciseName, validNumberOfSets, validNumberOfReps, validWeights);
        appendIntoStations(newStation);
        LogFile.writeLog("Added Gym Station: " + validExerciseName, false);
    }

    /**
     * Gets the list of GymStation objects.
     *
     * @return An ArrayList of GymStation objects.
     */
    public ArrayList<GymStation> getStations() {
        return stations;
    }

    /**
     * Retrieves the GymStation object by index.
     *
     * @param index Index of the GymStation object.
     * @return GymStation object.
     * @throws CustomExceptions.OutOfBounds If the index is out of bounds.
     */
    public GymStation getStationByIndex(int index) throws CustomExceptions.OutOfBounds {
        boolean isIndexValid = Validation.validateIndexWithinBounds(index, 0,  stations.size());
        if (!isIndexValid) {
            throw new CustomExceptions.OutOfBounds(ErrorConstant.INVALID_INDEX_SEARCH_ERROR);
        }
        return stations.get(index);
    }

    /**
     * Retrieves the string representation of a Gym object.
     *
     * @return A formatted string representing the Gym object, inclusive of the date and gym stations done.
     */

    @Override
    public String toString() {
        return String.format(" (Date: %s)", super.getDate());
    }


    /**
     * Retrieves the string representation of a Gym object.
     * Used for the formatting of the Gym Object before writing into a file.
     *
     * @return StringBuilder Object that contains the formatted string.
     */
    private StringBuilder formatFileString(){
        StringBuilder fileString = new StringBuilder();
        String type = WorkoutConstant.GYM.toUpperCase();
        String numOfStation = String.valueOf(stations.size());
        String date = super.getDateForFile();
        fileString.append(type);
        fileString.append(UiConstant.SPLIT_BY_COLON);
        fileString.append(numOfStation);
        fileString.append(UiConstant.SPLIT_BY_COLON);
        fileString.append(date);
        fileString.append(UiConstant.SPLIT_BY_COLON);
        return fileString;
    }

    /**
     * Converts the Gym Object into the String format for writing into a file.
     * The format that this output is
     *  gym:NUM_STATIONS:DATE:STATION1_NAME:NUM_SETS:REPS:WEIGHT1,WEIGHT2,WEIGHT3,WEIGHT4
     *  :STATION2_NAME:NUM_SETS:REPS:WEIGHT1,WEIGHT2,WEIGHT3,WEIGHT4 ....
     *  Example: "gym:2:1997-11-11:bench press:4:4,4,4,4:10,20,30,40:squats:4:3,3,3,3:20,30,40,50"
     *  Can refer to GymTest {@code toFileString_correctInput_expectedCorrectString()} for more examples
     *
     * @return A formatted string in the format specified above.
     */
    public String toFileString(){

        StringBuilder fileString = formatFileString();
        ArrayList<GymStation> stations = getStations();
        for (GymStation station : stations) {
            fileString.append(station.toFileString());
            if (stations.indexOf(station) != stations.size() - 1) {
                fileString.append(UiConstant.SPLIT_BY_COLON);
            }
        }
        return fileString.toString();
    }

    /**
     * Used when printing all the workouts. This method takes in two parameters {@code isFirstIteration} and {@code i}
     * @param index indicates which particular gymStation is being queried.
     * @return A string representing the history format for gym.
     */
    public String getHistoryFormatForSpecificGymStation(int index) {

        String gymDate = super.getDate();

        // Get the string format for a specific gym station
        GymStation station = getStations().get(index);
        String gymStationString = station.getStationName();
        String gymSetString = String.valueOf(station.getNumberOfSets());

        // If it is first iteration, includes dashes for irrelevant field
        if (index == 0){
            return String.format(WorkoutConstant.HISTORY_WORKOUTS_DATA_FORMAT,
                    WorkoutConstant.GYM, gymDate,
                    gymStationString,
                    gymSetString,
                    UiConstant.DASH
            );
        } else {
            // if it is not, then leave it blank
            return String.format(WorkoutConstant.HISTORY_WORKOUTS_DATA_FORMAT,
                    UiConstant.EMPTY_STRING,
                    UiConstant.EMPTY_STRING,
                    gymStationString,
                    gymSetString,
                    UiConstant.DASH
            );

        }
    }

    // Private methods

    private void appendIntoStations(GymStation station) {
        stations.add(station);
    }


    // Validation Functions
    /**
     * Validates the weight string such that it only has numbers.
     *
     * @param weightsString The string representing the weights in the format "weight1,weight2,weight3..."
     * @return ArrayList of integers representing the weights in the format [weight1, weight2, weight3 ...]
     * @throws CustomExceptions.InvalidInput If an invalid weights string is passed in.
     */
    protected ArrayList<Double> processWeightsArray(String weightsString)
            throws CustomExceptions.InvalidInput {

        validateWeightString(weightsString);


        String[] weightsArray = weightsString.split(UiConstant.SPLIT_BY_COMMAS);
        ArrayList<Double> validatedWeightsArray = new ArrayList<>();


        for (String weight: weightsArray){
            boolean isValidWeight = validateWeight(weight);
            if (isValidWeight){
                validatedWeightsArray.add(Double.parseDouble(weight));
            }
        }
        return validatedWeightsArray;
    }

    protected String validateGymStationName(String exerciseName) throws CustomExceptions.InvalidInput,
            CustomExceptions.InsufficientInput {

        validateExerciseNameNotEmpty(exerciseName);
        validateExerciseNamePattern(exerciseName);
        validateExerciseNameLength(exerciseName);
        return exerciseName;
    }

    protected int validateNumberOfSets(String numberOfSets) throws CustomExceptions.InvalidInput {
        boolean isSetsValid = Validation.validateIntegerIsPositive(numberOfSets);
        if (!isSetsValid) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_SETS_POSITIVE_DIGIT_ERROR);
        }

        return Integer.parseInt(numberOfSets);
    }


    protected int validateNumberOfRepetitions(String numberOfRepetitions) throws CustomExceptions.InvalidInput {

        boolean isRepsValid = Validation.validateIntegerIsPositive(numberOfRepetitions);
        if (!isRepsValid) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_REPS_POSITIVE_DIGIT_ERROR);
        }

        return Integer.parseInt(numberOfRepetitions);
    }


    protected boolean validateWeight(String weight) throws CustomExceptions.InvalidInput {
        try{
            double weightDouble = Double.parseDouble(weight);

            validateWeightIsPositive(weightDouble);
            validateWeightDoesNotExceedMax(weightDouble);
            validateWeightIsMultiple(weightDouble);

            return true;
        } catch (NumberFormatException e){
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_WEIGHTS_VALUE_ERROR);
        }
    }


    private void validateExerciseNameNotEmpty(String exerciseName) throws CustomExceptions.InsufficientInput {
        if (exerciseName.isEmpty()) {
            throw new CustomExceptions.InsufficientInput(ErrorConstant.EMPTY_GYM_STATION_NAME_ERROR);
        }
    }

    private void validateExerciseNamePattern(String exerciseName) throws CustomExceptions.InvalidInput {
        if (!exerciseName.matches(UiConstant.VALID_GYM_STATION_NAME_REGEX)) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_GYM_STATION_NAME_ERROR);
        }
    }

    private void validateExerciseNameLength(String exerciseName) throws CustomExceptions.InvalidInput {
        if (exerciseName.length() > WorkoutConstant.MAX_GYM_STATION_NAME_LENGTH) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.GYM_STATION_NAME_LENGTH_ERROR);
        }
    }


    private void validateWeightIsPositive(double weight) throws CustomExceptions.InvalidInput {
        if (weight < WorkoutConstant.MIN_GYM_WEIGHT){
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_WEIGHTS_ARRAY_FORMAT_ERROR);
        }
    }

    private void validateWeightDoesNotExceedMax(double weight) throws CustomExceptions.InvalidInput {
        if (weight > WorkoutConstant.MAX_GYM_WEIGHT) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_WEIGHT_MAX_ERROR);
        }
    }

    private void validateWeightIsMultiple(double weight) throws CustomExceptions.InvalidInput {
        if (weight % WorkoutConstant.WEIGHT_MULTIPLE != 0 ){
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_WEIGHTS_VALUE_ERROR);
        }
    }

    private void checkIfNumberOfWeightsMatchesSets(ArrayList<Double> weights, int numberOfSets) throws CustomExceptions.InvalidInput {
        if (weights.size() != numberOfSets){
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_WEIGHTS_NUMBER_ERROR);
        }
    }

    private void validateWeightString(String weightsString) throws CustomExceptions.InvalidInput {
        if (weightsString.isBlank()) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_WEIGHTS_EMPTY_ERROR);
        }

        if (!weightsString.matches(UiConstant.VALID_WEIGHTS_ARRAY_REGEX)) {
            throw new CustomExceptions.InvalidInput(ErrorConstant.INVALID_WEIGHTS_ARRAY_FORMAT_ERROR);
        }
    }
}
