package workouts;

import utility.Constant;
import utility.CustomExceptions;

import java.util.ArrayList;

/**
 * Represents the WorkoutList object.
 */
public class WorkoutList extends ArrayList<Workout> {
    private static final ArrayList<Workout> workouts = new ArrayList<>();
    private static final ArrayList<Run> runs = new ArrayList<>();
    private static final ArrayList<Gym> gyms = new ArrayList<>();


    /**
     * Adds a workout to the list of workouts.
     *
     * @param workout Workout object to be added.
     */
    private static void addWorkout(Workout workout) {
        workouts.add(workout);
    }

    /**
     * Adds a run to the list of runs and workouts.
     *
     * @param run Run object to be added.
     */
    public static void addRun(Run run) {
        runs.add(run);
        addWorkout(run);
    }

    /**
     * Adds a gym to the list of gyms and workouts.
     *
     * @param gym Gym object to be added.
     */
    public static void addGym(Gym gym) {
        gyms.add(gym);
        addWorkout(gym);
    }

    /**
     * Returns a list of workouts based on the filter.
     *
     * @param filter can be "all", "run" or "gym".
     *               "all" returns all workouts.
     *               "run" returns only runs.
     *               "gym" returns only gym workouts.
     * @return ArrayList of workouts.
     */
    public static ArrayList<? extends Workout> getWorkouts(String filter)
            throws CustomExceptions.OutOfBounds,
            CustomExceptions.InvalidInput {

        filter = filter.toLowerCase();
        if(!filter.equals(Constant.ALL) && !filter.equals(Constant.RUN) && !filter.equals(Constant.GYM)) {
            throw new CustomExceptions.InvalidInput(Constant.INVALID_PRINT_HISTORY_FILTER);
        }
        if(filter.equals(Constant.RUN) && runs.isEmpty()){
            throw new CustomExceptions.OutOfBounds(Constant.NO_RUNS_FOUND);
        }
        if(filter.equals(Constant.ALL) && workouts.isEmpty()){
            throw new CustomExceptions.OutOfBounds(Constant.NO_HISTORY_FOUND);
        }
        if(filter.equals(Constant.GYM) && gyms.isEmpty()){
            throw new CustomExceptions.OutOfBounds(Constant.NO_GYMS_FOUND);
        }

        if(filter.equals(Constant.RUN)){
            return runs;
        } else if (filter.equals(Constant.GYM)) {
            return gyms;
        } else {
            return workouts;
        }

    }

    /**
     * Returns latest run.
     *
     * @return The latest Run object added.
     * @throws CustomExceptions.OutOfBounds If no runs are found in the list.
     */
    public static Run getLatestRun() throws CustomExceptions.OutOfBounds {
        if (runs.isEmpty()) {
            throw new CustomExceptions.OutOfBounds(Constant.NO_RUNS_FOUND);
        }
        return (Run) runs.get(runs.size() - 1);
    }

    public static Gym getLatestGym() throws CustomExceptions.OutOfBounds {
        if (gyms.isEmpty()) {
            throw new CustomExceptions.OutOfBounds(Constant.NO_GYMS_FOUND);
        }
        return (Gym) gyms.get(gyms.size() - 1);
    }

    /**
     * Returns the number of runs in the list.
     *
     * @return The number of runs.
     */
    public static int getRunSize() {
        return runs.size();
    }

    /**
     * Returns the number of gyms in the list.
     *
     * @return The number of gyms.
     */
    public static int getGymSize() {
        return gyms.size();
    }

    /**
     * Clears the workouts, runs and gyms ArrayLists.
     */
    public static void clearWorkoutsAndRun() {
        workouts.clear();
        runs.clear();
        gyms.clear();
    }
}
