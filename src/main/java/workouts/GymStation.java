package workouts;

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
     * @param name                String name of the station
     * @param numberOfSets        Number of sets done.
     * @param numberOfRepetitions Number of reps done.
     * @param weightsList         Weight used.
     */
    protected GymStation(String name, int numberOfSets, int numberOfRepetitions, ArrayList<Integer> weightsList) {
        this.stationName = name;
        this.numberOfSets = numberOfSets;
        processSets(weightsList, numberOfRepetitions);
    }

    /**
     * Function which adds a GymSet object to GymStation.
     *
     * @param weightsList     The weight done for the particular set.
     * @param numberOfRepetitions The number of repetitions done for the particular set.
     */
    public void processSets(ArrayList<Integer> weightsList, int numberOfRepetitions) {
        for (int i = 0; i < numberOfSets; i++) {
            GymSet newSet = new GymSet(weightsList.get(i), numberOfRepetitions);
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
     * Retrieves the number sets within the GymStation.
     *
     * @return The number of sets done.
     */
    public int getNumberOfSets() {
        return numberOfSets;
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

    /**
     * Retrieves the string representation of a GymStation object with a specified delimiter
     * E.g. toRepString(",") returns "1,2,3"
     * E.g. toRepString(":") returns "1:2:3"
     * @param delimiter The delimiter to separate the repetitions.
     * @return A formatted string representing a GymStation object with the specified delimiter.
     */
    public String toRepString(String delimiter) {
        StringBuilder repString = new StringBuilder();
        for (int i = 0; i < sets.size(); i++) {
            String currentRep = String.valueOf(sets.get(i).getNumberOfRepetitions());
            repString.append(currentRep);
            if (i != sets.size() - 1) {
                repString.append(delimiter);
            }
        }
        return repString.toString();
    }

    /**
     * Retrieves the string representation of a GymStation object with a specified delimiter
     * E.g. toWeightString(",") returns "10,20,30"
     * E.g. toWeightString(":") returns "10:20:30"
     * @param delimiter The delimiter to separate the weights.
     * @return A formatted string representing a GymStation object with the specified delimiter.
     */
    public String toWeightString(String delimiter){
        StringBuilder weightString = new StringBuilder();
        for (int i = 0; i < sets.size(); i++) {
            String currentRep = String.valueOf(sets.get(i).getWeight());
            weightString.append(currentRep);
            if (i != sets.size() - 1) {
                weightString.append(delimiter);
            }
        }
        return weightString.toString();
    }

    /**
     * Retrieves the string representation of a GymStation object for writing into a file.
     * Formats the string in the following format
     * "[Exercise Name]:[Number of Sets]:[Repetitions]:[Weights1, Weight2,Weight3 ...]"
     * @return A formatted string representing a GymStation object with the format above.
     */
    public String toFileString(){
        StringBuilder fileString = new StringBuilder();
        String stationName = getStationName();
        String numOfSets = String.valueOf(getNumberOfSets());
        String gymRepString = toRepString(UiConstant.SPLIT_BY_COMMAS).split(UiConstant.SPLIT_BY_COMMAS)[0];
        String gymWeightString = toWeightString(UiConstant.SPLIT_BY_COMMAS);
        fileString.append(stationName);
        fileString.append(UiConstant.SPLIT_BY_COLON);
        fileString.append(numOfSets);
        fileString.append(UiConstant.SPLIT_BY_COLON);
        fileString.append(gymRepString);
        fileString.append(UiConstant.SPLIT_BY_COLON);
        fileString.append(gymWeightString);
        return fileString.toString();
    }


}


