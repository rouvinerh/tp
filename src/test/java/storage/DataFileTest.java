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
import workouts.WorkoutLists;

import java.io.File;
import java.io.FileWriter;
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
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DataFileTest {
    private final String testDataFilePath = "./test_data.txt";
    private final String testHashFilePath = "./test_hash.txt";
    private final String originalDataFilePath = "./pulsepilot_data.txt";
    private final String originalHashFilePath = "./pulsepilot_hash.txt";

    /**
     * Sets up the test environment by setting the file paths to the test files.
     */
    @BeforeEach
    void setUp() {
        // Set the file paths to the test files
        UiConstant.dataFilePath = testDataFilePath;
        UiConstant.saveFile = new File(testDataFilePath);
        UiConstant.hashFilePath = testHashFilePath;
    }

    /**
     * Tears down the test environment by deleting the test files and resetting the file paths.
     */
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

    /**
     * Cleans up the WorkoutList and HealthList before each test.
     */
    private void cleanup(){
        WorkoutLists.clearWorkoutsRunGym();
        HealthList.clearHealthLists();
    }

    /**
     * Asserts that the contents of the data file match the expected values.
     *
     * @param name                 the user's name
     * @param bmiArrayList         the list of BMI entries
     * @param appointmentArrayList the list of appointments
     * @param periodArrayList      the list of periods
     * @param workoutArrayList     the list of workouts
     */
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


    /**
     * Tests the saveDataFile method with valid data.
     * Verifies that the data file is written correctly.
     */
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


        Gym newGym = new Gym("11-11-1997");
        Gym newGym2 = new Gym();

        try {
            newGym.addStation("bench press", "4", "4", "10.0,20.0,30.0,40.0");
            newGym.addStation("squats", "4", "3", "20.0,30.0,40.0,50.0");
            newGym2.addStation("bench press", "4", "4", "10.0,20.0,30.0,40.0");
            newGym2.addStation("squats", "4", "3", "20.0,30.0,40.0,50.0");
        } catch (CustomExceptions.InvalidInput | CustomExceptions.InsufficientInput e) {
            fail("Should not throw an exception");
        }


        ArrayList<Workout> workoutArrayList = new ArrayList<>(List.of(
                new Run("30:00", "5.00", "01-04-2023"),
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
            assertEquals("RUN:5.00:30.00:01-04-2023", lines.get(7));
            assertEquals("GYM:2:11-11-1997:bench press:4:4:10.0,20.0,30.0," +
                    "40.0:squats:4:3:20.0,30.0,40.0,50.0", lines.get(8));
            assertEquals("GYM:2:NA:bench press:4:4:10.0,20.0,30.0,40.0:squats:4:3:20.0,30.0,40.0,50.0",
                    lines.get(9));
        } else {
            fail("Data file is empty");
        }
    }

    /**
     * Tests the saveDataFile method with empty data.
     * Verifies that the data file is written correctly.
     */
    @Test
    void saveDataFile_emptyData_writesCorrectly() throws CustomExceptions.FileWriteError {
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

    /**
     * Tests the loadDataFile method when the data file does not exist.
     * Verifies that a new file is created.
     */
    @Test
    void loadDataFile_nonExistentFile_createsNew() throws
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
                new Run("30:00", "5.00", "01-04-2023")
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

    /**
     * Tests the generateFileHash method with a valid file.
     * Verifies that the correct hash is generated.
     */
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
                new Run("30:00", "5.00", "01-04-2023")
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

    /**
     * Tests the loadDataFile method with an existing file.
     * Verifies that the data is loaded correctly.
     */
    @Test
    void loadDataFile_existingFile_readsCorrectly() throws CustomExceptions.FileReadError,
            CustomExceptions.FileWriteError, CustomExceptions.InvalidInput {
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
                new Period ("08-05-2023"),
                new Period("01-04-2023", "07-04-2023")


        ));
        Gym gym1 = new Gym();
        try {
            gym1.addStation("Squat Press", "1", "50", "1.0");
        } catch (CustomExceptions.InvalidInput | CustomExceptions.InsufficientInput e) {
            fail("Should not throw an exception");
        }
        ArrayList<Workout> workoutArrayList = new ArrayList<>(Arrays.asList(
                new Run("40:10", "10.32", "15-03-2024"),
                new Run("40:10", "10.32"),
                gym1
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
                Arrays.toString(WorkoutLists.getWorkouts().toArray()));
    }

    /**
     * Tests the verifyIntegrity method with an invalid file.
     * Expects a FileCreateError exception to be thrown.
     */
    @Test
    void verifyIntegrity_invalidFileName_expectsFileCreateErrorException() {
        File testFile = new File("");
        DataFile dataFile = new DataFile();
        assertThrows(CustomExceptions.FileCreateError.class, () -> dataFile.verifyIntegrity(testFile));
    }

    /**
     * Tests the readHashFromFile method with a valid hash file.
     * Verifies that the correct hash is read.
     */
    @Test
    void readHashFromFile_validHashFile_returnsCorrectHash() throws IOException {
        // Arrange
        String expectedHash = "abc123def456";
        File hashFile = new File(testHashFilePath);
        try (FileWriter writer = new FileWriter(hashFile)) {
            writer.write(expectedHash);
        }

        // Act
        DataFile dataFile = new DataFile();
        String actualHash = dataFile.readHashFromFile(hashFile);

        // Assert
        assertEquals(expectedHash, actualHash);
    }

    /**
     * Tests the processName method with an invalid username.
     * Expects an InvalidInput exception to be thrown.
     */
    @Test
    void processName_invalidUsername_throwsInvalidInputException() {
        // Arrange
        String invalidUsername = "John~Doe123";

        // Act and Assert
        DataFile dataFile = new DataFile();
        assertThrows(CustomExceptions.InvalidInput.class, () -> dataFile.processName(invalidUsername));
    }

    /**
     * Tests the processAppointment method with missing appointment details.
     * Expects an InvalidInput exception to be thrown.
     */
    @Test
    void processAppointment_missingAppointmentDetails_throwsInvalidInputException() {
        // Arrange
        String[] input = {"APPOINTMENT", "01-05-2025", "10:00", "invalid_description~"};

        // Act and Assert
        DataFile dataFile = new DataFile();
        assertThrows(CustomExceptions.InvalidInput.class, () -> dataFile.processAppointment(input));
    }

    /**
     * Tests the processPeriod method with invalid period input.
     * Expects an InvalidInput exception to be thrown.
     */
    @Test
    void processPeriod_invalidPeriodInput_throwsInvalidInputException() {
        // Arrange
        String[] input = {"PERIOD", "01-04-2023", "invalid_date"};

        // Act and Assert
        DataFile dataFile = new DataFile();
        assertThrows(CustomExceptions.InvalidInput.class, () -> dataFile.processPeriod(input));
    }

    /**
     * Tests the processBmi method with invalid BMI input.
     * Expects an InvalidInput exception to be thrown.
     */
    @Test
    void processBmi_invalidBmiInput_throwsInvalidInputException() {
        // Arrange
        String[] input = {"BMI", "invalid_height", "70.00", "24.22", "01-04-2023"};

        // Act and Assert
        DataFile dataFile = new DataFile();
        assertThrows(CustomExceptions.InvalidInput.class, () -> dataFile.processBmi(input));
    }

    /**
     * Tests the processRun method with invalid run input.
     * Expects an InvalidInput exception to be thrown.
     */
    @Test
    void processRun_invalidRunInput_throwsInvalidInputException() {
        // Arrange
        String[] input = {"RUN", "invalid_distance", "00:30:00", "01-04-2023"};

        // Act and Assert
        DataFile dataFile = new DataFile();
        assertThrows(CustomExceptions.InvalidInput.class, () -> dataFile.processRun(input));
    }

    /**
     * Tests the processGym method with invalid gym input.
     * Expects an InvalidInput exception to be thrown.
     */
    @Test
    void processGym_invalidGymInput_throwsInvalidInputException() {
        // Arrange
        String rawInput = "GYM:2:11-11-1997:bench press:" +
                "4:4:10.0,20.0,30.0,40.0:invalid_station:4:3:20.0,30.0,40.0,50.0";

        // Act and Assert
        DataFile dataFile = new DataFile();
        assertThrows(CustomExceptions.InvalidInput.class, () -> dataFile.processGym(rawInput));
    }

    /**
     * Tests the processFail method.
     * Verifies that the error is logged and the files are deleted.
     */
    @Test
    void processFail_logsErrorAndDeletesFiles() {
        // Arrange
        DataFile dataFile = new DataFile();
        String errorString = "Test error string";
        String dataFilePath = testDataFilePath;
        String hashFilePath = testHashFilePath;

        // Act
        dataFile.processFail(errorString);

        // Assert
        // Check if the error was logged
        String logContent = LogFile.readLogContent();
        assertTrue(logContent.contains(errorString));

        // Check if the data file was deleted
        assertFalse(new File(dataFilePath).exists());

        // Check if the hash file was deleted
        assertFalse(new File(hashFilePath).exists());
    }
}
