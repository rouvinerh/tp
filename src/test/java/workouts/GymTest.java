package workouts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.CustomExceptions;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class GymTest {

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void cleanup() {
        WorkoutList.clearWorkoutsRunGym();
    }

    /**
     * Tests the behavior of adding a new station to the gym.
     * Verifies whether the newly added station is correctly reflected in {@Code GymSet}.
     * Expected Behaviour is to add stations and sets to the gym.
     */
    @Test
    void addStation_validInput_expectAddedStation() {
        Gym newGym = new Gym();
        try{
            ArrayList<Integer> array1 = new ArrayList<>(Arrays.asList(1));
            ArrayList<Integer> array2 = new ArrayList<>(Arrays.asList(1,2));
            ArrayList<Integer> array3 = new ArrayList<>(Arrays.asList(1,2,3));

            newGym.addStation("Exercise 1", 1, 10, array1);
            newGym.addStation("Exercise 2", 2, 20, array2);
            assertEquals(2, newGym.getStations().size());

            newGym.addStation("Exercise 3", 3, 30, array3);
            ArrayList<GymStation> stations = newGym.getStations();
            assertEquals(3, stations.size());

            for(int i = 0; i < stations.size(); i++){
                String stationName = stations.get(i).getStationName();
                ArrayList<GymSet> sets = stations.get(i).getSets();
                int numberOfSets = sets.size();

                if (i == 0){
                    assertEquals("Exercise 1", stationName);
                    assertEquals(1, numberOfSets );
                    for(int j = 0; j < sets.size(); j++){
                        assertEquals(array1.get(j), sets.get(j).getWeight());
                        assertEquals(10, sets.get(j).getNumberOfRepetitions());
                    }


                } else if (i == 1){
                    assertEquals("Exercise 2", stationName);
                    assertEquals(2, numberOfSets );
                    for(int j = 0; j < sets.size(); j++){
                        assertEquals(array2.get(j), sets.get(j).getWeight());
                        assertEquals(20, sets.get(j).getNumberOfRepetitions());
                    }

                } else if (i == 2){
                    assertEquals("Exercise 3", stationName);
                    assertEquals(3, numberOfSets );
                    for(int j = 0; j < sets.size(); j++){
                        assertEquals(array3.get(j), sets.get(j).getWeight());
                        assertEquals(30, sets.get(j).getNumberOfRepetitions());
                    }
                }
            }

        } catch (Exception e) {
            fail("Should not throw an exception");
        }
    }


    @Test
    void getStations() {
    }

    @Test
    void getStationByIndex() {
    }

    @Test
    void addGymStationInput() {
    }

    @Test
    void checkGymStationInput() {
    }

    @Test
    void toFileString_correctInput_expectedCorrectString(){
        String expected1 = "gym:2:1997-11-11:bench press:4:4:10,20,30,40:squats:4:3:20,30,40,50";
        String expected2WithNoDate = "gym:2:NA:bench press:4:4:10,20,30,40:squats:4:3:20,30,40,50";
        ArrayList<Integer> array1 = new ArrayList<>(Arrays.asList(10,20,30,40));
        ArrayList<Integer> array2 = new ArrayList<>(Arrays.asList(20,30,40,50));
        Gym newGym = new Gym("11-11-1997");
        Gym newGym2 = new Gym();

        try{
            newGym.addStation("bench press", 4, 4, array1);
            newGym.addStation("squats", 4, 3, array2);
            newGym2.addStation("bench press", 4, 4, array1);
            newGym2.addStation("squats", 4, 3, array2);
            String output = newGym.toFileString();
            String output2 = newGym2.toFileString();
            assertEquals(expected1, output);
            assertEquals(expected2WithNoDate, output2);

        } catch (CustomExceptions.InvalidInput e){
            fail("Should not throw an exception");
        }
    }


}
