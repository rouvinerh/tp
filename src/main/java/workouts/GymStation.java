package workouts;

import utility.CustomExceptions;
import constants.ErrorConstant;
import constants.UiConstant;
import constants.WorkoutConstant;


import java.util.ArrayList;

/**
 * Represents a GymStation object.
 */
public class GymStation {
    protected String stationName;
    protected ArrayList<GymSet> sets = new ArrayList<>();
    protected int numberOfSets;

    /**
     * Constructs a new GymStation object that contains the name, weight, number of repetitions and number of sets done
     * in one station.
     *
     * @param name         String name of the station
     * @param weightsList  Weight used.
     * @param repetition   Number of reps done.
     * @param numberOfSets Number of sets done.
     */
    protected GymStation(String name, ArrayList<Integer> weightsList, int repetition, int numberOfSets) {
        this.stationName = name;
        this.numberOfSets = numberOfSets;
        processSets(weightsList, repetition);
    }

    /**
     * Function which adds a GymSet object to GymStation.
     *
     * @param weightsList     The weight done for the particular set.
     * @param repetition The number of repetitions done for the particular set.
     */
    public void processSets(ArrayList<Integer> weightsList, int repetition) {
        for (int i = 0; i < numberOfSets; i++) {
            GymSet newSet = new GymSet(weightsList.get(i), repetition);
            sets.add(newSet);
        }
    }

    /**
     * Retrieves the station name for the GymStation object.
     *
     * @return String representing the name for the station.
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * Retrieves an ArrayList of gym sets for the GymStation object.
     *
     * @return The ArrayList of GymSet objects.
     */
    public ArrayList<GymSet> getSets() {
        return sets;
    }

    /**
     * Retrieves a specific GymSet using an index.
     *
     * @param index Index of the desired GymSet.
     * @return Desired GymSet object.
     */
    public GymSet getSpecificSet(int index) {
        return sets.get(index);
    }


    /**
     * Retrieves the number sets within the GymStation.
     *
     * @return The number of sets done.
     */
    public int getNumberOfSets() {
        return numberOfSets;
    }

    /**
     * Checks parameters from user input for adding a new GymStation.
     *
     * @param splitInput List of strings representing user input.
     * @throws CustomExceptions.InsufficientInput If there is not enough parameters specified.
     * @throws CustomExceptions.InvalidInput      If there is invalid input.
     */
    public static void addGymStation(Gym gym, String[] splitInput) throws
            CustomExceptions.InsufficientInput,
            CustomExceptions.InvalidInput {

        ArrayList<Integer> weightsArray = getValidatedWeightsArray(splitInput[1]);
        int setsInteger = Integer.parseInt(splitInput[2]);
        int repsInteger = Integer.parseInt(splitInput[3]);
        gym.addStation(splitInput[0], weightsArray, setsInteger, repsInteger);
    }

    /**
     * Retrieves the string representation of a GymStation object.
     *
     * @return A formatted string representing a GymStation object.
     */
    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder(String.format(WorkoutConstant.GYM_STATION_FORMAT,
                this.getStationName()) + String.format(WorkoutConstant.INDIVIDUAL_GYM_STATION_FORMAT,
                this.getNumberOfSets()));

        for (int i = 0; i < this.getNumberOfSets(); i++) {
            returnString.append(System.lineSeparator());
            returnString.append(String.format(WorkoutConstant.GYM_SET_INDEX_FORMAT, i+1 ,
                    this.getSets().get(i).toString()));
        }
        return returnString.toString();
    }

    private static ArrayList<Integer> getValidatedWeightsArray(String weightsString)
            throws CustomExceptions.InvalidInput {
        String[] weightsArray = weightsString.split(UiConstant.SPLIT_BY_COMMAS);
        ArrayList<Integer> validatedWeightsArray = new ArrayList<>();

        try{
            for(String weight: weightsArray){
                int weightInteger = Integer.parseInt(weight);
                if (weightInteger < 0){
                    throw new CustomExceptions.InvalidInput(ErrorConstant.GYM_WEIGHT_POSITIVE_ERROR);
                }
                validatedWeightsArray.add(weightInteger);
            }
        } catch (NumberFormatException e){
            throw new CustomExceptions.InvalidInput(ErrorConstant.GYM_WEIGHT_DIGIT_ERROR);
        }
        return validatedWeightsArray;
    }
}


