package workouts;

import constants.WorkoutConstant;

/**
 * Represents a GymSet object.
 */
public class GymSet {
    int weight;
    int numberOfRepetitions;

    /**
     * Constructs a new GymSet object using the weight and reps for 1 set of a gym station.
     *
     * @param weight The weight done for the set.
     * @param numberOfRepetitions The number of reps done for the set.
     */
    public GymSet(int weight, int numberOfRepetitions){
        this.weight = weight;
        this.numberOfRepetitions = numberOfRepetitions;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getNumberOfRepetitions() {
        return numberOfRepetitions;
    }

    /**
     * Retrieves a string representation of a GymSet object.
     *
     * @return A formatted string representing a GymSet, inclusive of the number of repetitions and weight done.
     */
    @Override
    public String toString() {
        return String.format(WorkoutConstant.GYM_SET_FORMAT, this.numberOfRepetitions, this.weight);
    }
}

