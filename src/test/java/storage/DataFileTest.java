//@@author L5-Z
package storage;
import constants.UiConstant;
import health.Appointment;
import health.Bmi;
import health.HealthList;
import health.Period;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.CustomExceptions;
import workouts.Gym;
import workouts.Run;
import workouts.Workout;
import workouts.WorkoutList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataFileTest {
    private  final String testDataFilePath = "./test_data.txt";
    private  final String testHashFilePath = "./test_hash.txt";
    private  final String originalDataFilePath = "./pulsepilot_data.txt";
    private  final String originalHashFilePath = "./pulsepilot_hash.txt";

    @BeforeEach
    void setUp() {
        // Set the file paths to the test files
        UiConstant.dataFilePath = testDataFilePath;
        UiConstant.saveFile = new File(testDataFilePath);
        UiConstant.hashFilePath = testHashFilePath;
    }

    @AfterEach
    void tearDown() {
        // Delete the test files after each test
        new File(testDataFilePath).delete();
        new File(testHashFilePath).delete();

        // Reset the file paths
        UiConstant.dataFilePath = originalDataFilePath;
        UiConstant.saveFile = new File(originalDataFilePath);
        UiConstant.hashFilePath = originalHashFilePath;
    }

    private void cleanup(){
        WorkoutList.clearWorkoutsRunGym();
        HealthList.clearHealthLists();
    }
    private void assertDataFileContents(String name, ArrayList<Bmi> bmiArrayList,
                                        ArrayList<Appointment> appointmentArrayList,
                                        ArrayList<Period> periodArrayList,
                                        ArrayList<Workout> workoutArrayList) {
        try {
            List<String> lines = Files.readAllLines(Path.of(testDataFilePath));
            assertEquals("NAME:" + name, lines.get(0));

            int index = 1;
            for (Bmi bmi : bmiArrayList) {
                assertEquals("BMI:" + bmi.getHeight() + ":" + bmi.getWeight() + ":" + bmi.getBmiValue() + ":" +
                        bmi.getDate(), lines.get(index++));
            }

            for (Appointment appointment : appointmentArrayList) {
                assertEquals("APPOINTMENT:" + appointment.getDate() + ":" + appointment.getTime() + ":" +
                        appointment.getDescription(), lines.get(index++));
            }

            for (Period period : periodArrayList) {
                assertEquals("PERIOD:" + period.getStartDate() + ":" + period.getEndDate() + ":" +
                        period.getPeriodLength(), lines.get(index++));
            }

            for (Workout workout : workoutArrayList) {
                if (workout instanceof Run) {
                    Run run = (Run) workout;
                    assertEquals("RUN:" + run.getDistance() + ":" + run.getTimes() + ":" + run.getDate(),
                            lines.get(index++));
                } else if (workout instanceof Gym) {
                    Gym gym = (Gym) workout;
                    assertEquals(gym.toFileString(), lines.get(index++));
                }
            }
        } catch (IOException e) {
            fail("Error reading data file: " + e.getMessage());
        }
    }


    @Test
    void saveDataFile_validData_writesCorrectly() throws IOException, CustomExceptions.FileWriteError,
            CustomExceptions.InvalidInput {
        // Arrange
        String name = "John Doe";
        ArrayList<Bmi> bmiArrayList = new ArrayList<>(Arrays.asList(
                new Bmi("1.70", "70.00", "01-04-2023"),
                new Bmi("1.80", "80.00", "15-04-2023")
        ));
        ArrayList<Appointment> appointmentArrayList = new ArrayList<>(Arrays.asList(
                new Appointment("01-05-2023", "10:00", "Dentist Appointment"),
                new Appointment("15-05-2023", "14:30", "Doctor's Checkup")
        ));
        ArrayList<Period> periodArrayList = new ArrayList<>(Arrays.asList(
                new Period("01-03-2023", "05-03-2023"),
                new Period("01-04-2023", "04-04-2023")
        ));


        ArrayList<Double> array1 = new ArrayList<>(Arrays.asList(10.0,20.0,30.0,40.0));
        ArrayList<Double> array2 = new ArrayList<>(Arrays.asList(20.0,30.0,40.0,50.0));
        Gym newGym = new Gym("11-11-1997");
        Gym newGym2 = new Gym();
        newGym.addStation("bench press", 4, 4, array1);
        newGym.addStation("squats", 4, 3, array2);
        newGym2.addStation("bench press", 4, 4, array1);
        newGym2.addStation("squats", 4, 3, array2);

        ArrayList<Workout> workoutArrayList = new ArrayList<>(List.of(
                new Run("00:30:00", "5.0", "01-04-2023"),
                newGym,
                newGym2
        ));

        // Act
        DataFile dataFile = new DataFile();
        int status = dataFile.loadDataFile();
        dataFile.saveDataFile(name, bmiArrayList, appointmentArrayList, periodArrayList, workoutArrayList);

        // Assert
        List<String> lines = Files.readAllLines(Path.of(testDataFilePath));

        if (!lines.isEmpty()) {
            assertEquals("NAME:John Doe", lines.get(0));
            assertEquals("BMI:1.70:70.00:24.22:01-04-2023", lines.get(1));
            assertEquals("BMI:1.80:80.00:24.69:15-04-2023", lines.get(2));
            assertEquals("APPOINTMENT:01-05-2023:10.00:Dentist Appointment", lines.get(3));
            assertEquals("APPOINTMENT:15-05-2023:14.30:Doctor's Checkup", lines.get(4));
            assertEquals("PERIOD:01-03-2023:05-03-2023:5", lines.get(5));
            assertEquals("PERIOD:01-04-2023:04-04-2023:4", lines.get(6));
            assertEquals("RUN:5.00:00.30.00:01-04-2023", lines.get(7));
            assertEquals("GYM:2:11-11-1997:bench press:4:4:10.0,20.0,30.0," +
                    "40.0:squats:4:3:20.0,30.0,40.0,50.0", lines.get(8));
            assertEquals("GYM:2:NA:bench press:4:4:10.0,20.0,30.0,40.0:squats:4:3:20.0,30.0,40.0,50.0",
                    lines.get(9));
        } else {
            fail("Data file is empty");
        }
    }

    @Test
    void saveDataFile_emptyData_writesCorrectly() throws IOException, CustomExceptions.FileWriteError {
        // Arrange
        String name = "Jane Doe";
        ArrayList<Bmi> bmiArrayList = new ArrayList<>();
        ArrayList<Appointment> appointmentArrayList = new ArrayList<>();
        ArrayList<Period> periodArrayList = new ArrayList<>();
        ArrayList<Workout> workoutArrayList = new ArrayList<>();

        // Act
        DataFile dataFile = new DataFile();
        int status = dataFile.loadDataFile();
        dataFile.saveDataFile(name, bmiArrayList, appointmentArrayList, periodArrayList, workoutArrayList);

        // Assert
        assertTrue(new File(testDataFilePath).exists());
        assertTrue(new File(testHashFilePath).exists());
        assertTrue(new File(testHashFilePath).length() != 0);
        assertDataFileContents(name, bmiArrayList, appointmentArrayList, periodArrayList, workoutArrayList);
    }

    @Test
    void loadDataFile_nonExistentFile_createsNew() throws CustomExceptions.FileCreateError,
            CustomExceptions.InvalidInput, CustomExceptions.FileWriteError {
        // Arrange
        String name = "John Doe";
        ArrayList<Bmi> bmiArrayList = new ArrayList<>(Arrays.asList(
                new Bmi("1.70", "70.00", "01-04-2023"),
                new Bmi("1.80", "80.00", "15-04-2023")
        ));
        ArrayList<Appointment> appointmentArrayList = new ArrayList<>(Arrays.asList(
                new Appointment("01-05-2023", "10:00", "Dentist Appointment"),
                new Appointment("15-05-2023", "14:30", "Doctor's Checkup")
        ));
        ArrayList<Period> periodArrayList = new ArrayList<>(Arrays.asList(
                new Period("01-03-2023", "05-03-2023"),
                new Period("01-04-2023", "04-04-2023")
        ));
        ArrayList<Workout> workoutArrayList = new ArrayList<>(Arrays.asList(
                new Run("00:30:00", "5.0", "01-04-2023")
        ));

        // Act
        DataFile dataFile = new DataFile();
        int status = dataFile.loadDataFile();
        dataFile.saveDataFile(name, bmiArrayList, appointmentArrayList, periodArrayList, workoutArrayList);

        // Assert
        assertEquals(UiConstant.FILE_NOT_FOUND, status);
        assertTrue(new File(testDataFilePath).exists());
        assertTrue(new File(testHashFilePath).exists());
    }

    @Test
    void generateFileHash_validFile_returnsCorrectHash() throws NoSuchAlgorithmException, IOException,
            CustomExceptions.InvalidInput, CustomExceptions.FileWriteError {
        // Arrange
        String name = "John Doe";
        ArrayList<Bmi> bmiArrayList = new ArrayList<>(Arrays.asList(
                new Bmi("1.70", "70.00", "01-04-2023"),
                new Bmi("1.80", "80.00", "15-04-2023")
        ));
        ArrayList<Appointment> appointmentArrayList = new ArrayList<>(Arrays.asList(
                new Appointment("01-05-2023", "10:00", "Dentist Appointment"),
                new Appointment("15-05-2023", "14:30", "Doctor's Checkup")
        ));
        ArrayList<Period> periodArrayList = new ArrayList<>(Arrays.asList(
                new Period("01-03-2023", "05-03-2023"),
                new Period("01-04-2023", "04-04-2023")
        ));
        ArrayList<Workout> workoutArrayList = new ArrayList<>(Arrays.asList(
                new Run("00:30:00", "5.0", "01-04-2023")
        ));

        File dataFileName = new File(testDataFilePath);
        DataFile dataFile = new DataFile();
        dataFile.saveDataFile(name, bmiArrayList, appointmentArrayList, periodArrayList, workoutArrayList);

        // Act
        String hash = dataFile.generateFileHash(dataFileName);

        // Assert
        assertNotNull(hash);
        assertFalse(hash.isEmpty());
    }

    @Test
    void loadDataFile_existingFile_readsCorrectly() throws CustomExceptions.FileReadError,
            CustomExceptions.FileWriteError {
        // Arrange
        String name = "John Doe";
        ArrayList<Bmi> bmiArrayList = new ArrayList<>(Arrays.asList(
                new Bmi("1.80", "80.00", "15-04-2023"),
                new Bmi("1.70", "70.00", "01-04-2023")
        ));
        ArrayList<Appointment> appointmentArrayList = new ArrayList<>(Arrays.asList(
                new Appointment("01-05-2025", "10:00", "Dentist Appointment"),
                new Appointment("15-05-2025", "14:30", "Doctor's Checkup")
        ));

        // Has additional elements added to ArrayList and will thus be skipped
        ArrayList<Period> periodArrayList = new ArrayList<>(Arrays.asList(
        ));
        ArrayList<Workout> workoutArrayList = new ArrayList<>(Arrays.asList(
        ));

        DataFile dataFile = new DataFile();
        int status = dataFile.loadDataFile();
        dataFile.saveDataFile(name, bmiArrayList, appointmentArrayList, periodArrayList, workoutArrayList);

        // Act
        cleanup();
        dataFile.readDataFile();


        // Assert
        assertEquals(name, DataFile.userName);
        assertEquals(Arrays.toString(bmiArrayList.toArray()), Arrays.toString(HealthList.getBmis().toArray()));
        
        assertEquals(Arrays.toString(appointmentArrayList.toArray()),
                Arrays.toString(HealthList.getAppointments().toArray()));
        assertEquals(Arrays.toString(periodArrayList.toArray()),
                Arrays.toString(HealthList.getPeriods().toArray()));
        assertEquals(Arrays.toString(workoutArrayList.toArray()),
                Arrays.toString(WorkoutList.getWorkouts().toArray()));
    }

}
