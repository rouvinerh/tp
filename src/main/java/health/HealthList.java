package health;

import storage.LogFile;
import utility.CustomExceptions;
import constants.ErrorConstant;
import constants.HealthConstant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import ui.Output;

/**
 * The HealthList class extends the ArrayList class.
 * It contains the individual lists of Bmi objects, Appointment objects and Period objects, and the functionalities to
 * add the objects to their respective lists, get the lists, and display the latest object and lists of objects.
 */
public class HealthList extends ArrayList<Health> {

    /**
     * LogFile for logging health-related activities.
     */
    static LogFile logFile = LogFile.getInstance();

    /**
     * The list of Bmi records.
     */
    private static final ArrayList<Bmi> BMIS = new ArrayList<>();

    /**
     * The list of Period records.
     */
    private static final ArrayList<Period> PERIODS = new ArrayList<>();

    /**
     * The list of Appointment records.
     */
    private static final ArrayList<Appointment> APPOINTMENTS = new ArrayList<>();

    protected HealthList() {

    }

    //@@author j013n3

    /**
     * Adds a Bmi object to the bmis list.
     *
     * @param bmi Bmi object.
     * @throws AssertionError If Bmi object is null.
     */
    protected void addBmi(Bmi bmi) {
        assert bmi != null : ErrorConstant.NULL_BMI_ERROR;
        BMIS.add(bmi);
        // bmi sorted from latest to earliest date
        BMIS.sort(Comparator.comparing(Bmi::getDate).reversed());
    }

    //@@author syj02

    /**
     * Adds a Period object to the periods list.
     *
     * @param period Period object to be added.
     * @throws AssertionError If period object is null.
     */
    protected void addPeriod(Period period) {
        assert period != null : ErrorConstant.NULL_PERIOD_ERROR;

        PERIODS.add(period);

        PERIODS.sort(Comparator.comparing(Period::getStartDate).reversed());

        int size = PERIODS.size();
        if (size > 1) {
            for (int i = size - 1 ; i > 0; i--) {
                Period newerPeriod = PERIODS.get(i -  1);
                Period olderPeriod = PERIODS.get(i);
                olderPeriod.setCycleLength(newerPeriod.getStartDate());
            }
        }
    }

    /**
     * Adds an Appointment to the list of Appointments whenever addAppointment is called.
     * Sorts all Appointment objects in the list by date and time of the appointments with
     * the earliest appointment at the top.
     *
     * @param appointment Appointment object.
     * @throws AssertionError If Appointment object is null.
     */
    protected void addAppointment(Appointment appointment) {
        assert appointment != null : ErrorConstant.NULL_APPOINTMENT_ERROR;
        APPOINTMENTS.add(appointment);
        APPOINTMENTS.sort(Comparator.comparing(Appointment::getDate).thenComparing(Appointment::getTime));
    }

    //@@author j013n3

    /**
     * Retrieves the list of bmis recorded of ArrayList type.
     *
     * @return The bmis array list.
     */
    public static ArrayList<Bmi> getBmis() {
        return BMIS;
    }

    /**
     * Retrieves the list of periods recorded of ArrayList type.
     *
     * @return The periods array list.
     */
    public static ArrayList<Period> getPeriods() {
        return PERIODS;
    }

    /**
     * Retrieves the list of appointments recorded of ArrayList type.
     *
     * @return The appointments array list.
     */
    public static ArrayList<Appointment> getAppointments() {
        return APPOINTMENTS;
    }

    /**
     * Retrieves the Period object at the specified index of Period type.
     *
     * @param index The index of the Period object.
     * @return The Period object at the specified index, or null if the index is out of bounds.
     */
    public static Period getPeriod(int index) {
        if (index < 0 || index >= PERIODS.size()) {
            return null;
        }
        return PERIODS.get(index);
    }

    /**
     * Retrieves the number of periods recorded of int type.
     *
     * @return The number of periods recorded.
     */
    public static int getPeriodSize() {
        return PERIODS.size();
    }

    //@@author l5_z

    /**
     * Retrieves size of bmis list of int type.
     *
     * @return Size of bmis list.
     */
    public static int getBmisSize() {
        return BMIS.size();
    }

    /**
     * Retrieves size of periods list of int type.
     *
     * @return Size of periods list.
     */
    public static int getPeriodsSize() {
        return PERIODS.size();
    }

    //@@l5_z

    /**
     * Deletes Bmi object based on index.
     *
     * @param index Index of the Bmi object to be deleted.
     * @throws CustomExceptions.OutOfBounds If the index of the Bmi object given does not exist.
     */
    public static void deleteBmi(int index) throws CustomExceptions.OutOfBounds {
        if (index < 0) {
            throw new CustomExceptions.OutOfBounds(ErrorConstant.BMI_EMPTY_ERROR);
        } else if (index >= BMIS.size()) {
            throw new CustomExceptions.OutOfBounds(ErrorConstant.INVALID_INDEX_DELETE_ERROR);
        }
        assert !BMIS.isEmpty() : ErrorConstant.EMPTY_BMI_LIST_ERROR;
        Bmi deletedBmi = BMIS.get(index);
        Output.printLine();
        System.out.printf((HealthConstant.LOG_DELETE_BMI_FORMAT) + "%n",
                deletedBmi.bmiValue,
                deletedBmi.date);
        Output.printLine();
        BMIS.remove(index);
        LogFile.writeLog(HealthConstant.BMI_REMOVED_MESSAGE_PREFIX + index, false);
    }

    /**
     * Deletes Period object based on index.
     *
     * @param index Index of the Period object to be deleted.
     * @throws CustomExceptions.OutOfBounds If the index of the Period object given does not exist.
     */
    public static void deletePeriod(int index) throws CustomExceptions.OutOfBounds {
        if (index < 0) {
            throw new CustomExceptions.OutOfBounds(ErrorConstant.PERIOD_EMPTY_ERROR);
        } else if(index >= PERIODS.size()) {
            throw new CustomExceptions.OutOfBounds(ErrorConstant.INVALID_INDEX_DELETE_ERROR);
        }
        assert !PERIODS.isEmpty() : ErrorConstant.EMPTY_PERIOD_LIST_ERROR;
        Period deletedPeriod = PERIODS.get(index);
        Output.printLine();
        System.out.printf((HealthConstant.LOG_DELETE_PERIOD_FORMAT) + "%n",
                deletedPeriod.getStartDate(),
                deletedPeriod.getEndDate());
        PERIODS.remove(index);
        Output.printLine();
        LogFile.writeLog(HealthConstant.PERIOD_REMOVED_MESSAGE_PREFIX + index, false);
    }

    //@@author syj02

    /**
     * Deletes Appointment object based on index.
     *
     * @param index Index of the Appointment object to be deleted.
     * @throws CustomExceptions.OutOfBounds If the index of the Appointment object given does not exist.
     */
    public static void deleteAppointment(int index) throws CustomExceptions.OutOfBounds {
        if (index < 0) {
            throw new CustomExceptions.OutOfBounds(ErrorConstant.APPOINTMENT_EMPTY_ERROR);
        } else if (index >= APPOINTMENTS.size()) {
            throw new CustomExceptions.OutOfBounds(ErrorConstant.INVALID_INDEX_DELETE_ERROR);
        }
        assert !APPOINTMENTS.isEmpty() : ErrorConstant.EMPTY_APPOINTMENT_LIST_ERROR;
        Appointment deletedAppointment = APPOINTMENTS.get(index);
        Output.printLine();
        System.out.printf((HealthConstant.LOG_DELETE_APPOINTMENT_FORMAT) + "%n",
                deletedAppointment.date,
                deletedAppointment.time,
                deletedAppointment.description);
        Output.printLine();
        APPOINTMENTS.remove(index);
        LogFile.writeLog(HealthConstant.APPOINTMENT_REMOVED_MESSAGE_PREFIX + index, false);
        if (!APPOINTMENTS.isEmpty()) {
            printAppointmentHistory();
        }
    }

    /**
     * Prints the latest Bmi object added.
     *
     * @throws AssertionError If bmis list is empty.
     */
    public static void printLatestBmi() throws CustomExceptions.OutOfBounds {
        if (BMIS.isEmpty()) {
            throw new CustomExceptions.OutOfBounds(ErrorConstant.BMI_EMPTY_ERROR);
        }
        assert !BMIS.isEmpty() : ErrorConstant.EMPTY_BMI_LIST_ERROR;
        System.out.println(BMIS.get(0));
    }

    /**
     * Prints the latest Period object added.
     *
     * @throws AssertionError If periods list is empty.
     */
    public static void printLatestPeriod() throws CustomExceptions.OutOfBounds {
        if (PERIODS.isEmpty()) {
            throw new CustomExceptions.OutOfBounds(ErrorConstant.PERIOD_EMPTY_ERROR);
        }
        assert !PERIODS.isEmpty() : ErrorConstant.EMPTY_PERIOD_LIST_ERROR;
        System.out.println(PERIODS.get(0));
    }

    /**
     * Prints the latest Appointment object added.
     *
     * @throws AssertionError If appointments list is empty.
     */
    public static void printLatestAppointment() throws CustomExceptions.OutOfBounds {
        if (APPOINTMENTS.isEmpty()) {
            throw new CustomExceptions.OutOfBounds(ErrorConstant.APPOINTMENT_EMPTY_ERROR);
        }
        assert !APPOINTMENTS.isEmpty() : ErrorConstant.EMPTY_APPOINTMENT_LIST_ERROR;
        int index = APPOINTMENTS.size() - 1;
        System.out.println(APPOINTMENTS.get(index));
    }

    /**
     * Prints all the Bmi entries recorded.
     *
     * @throws AssertionError If bmis list is empty.
     */
    public static void printBmiHistory() throws CustomExceptions.OutOfBounds {
        if (BMIS.isEmpty()) {
            throw new CustomExceptions.OutOfBounds(ErrorConstant.BMI_EMPTY_ERROR);
        }
        assert !BMIS.isEmpty() : ErrorConstant.EMPTY_BMI_LIST_ERROR;
        int index = 1;
        System.out.println(HealthConstant.BMI_HISTORY_HEADER);
        for (Bmi bmi : BMIS) {
            System.out.print(index + ". ");
            System.out.println(bmi);
            index += 1;
        }

    }

    //@@author j013n3

    /**
     * Prints all Period entries tracked.
     *
     * @throws AssertionError If periods list is empty.
     */
    public static void printPeriodHistory() throws CustomExceptions.OutOfBounds {
        if (PERIODS.isEmpty()) {
            throw new CustomExceptions.OutOfBounds(ErrorConstant.PERIOD_EMPTY_ERROR);
        }
        assert !PERIODS.isEmpty() : ErrorConstant.EMPTY_PERIOD_LIST_ERROR;
        int index = 1;
        System.out.println(HealthConstant.PERIOD_HISTORY_HEADER);
        for (Period period : PERIODS) {
            System.out.print(index + ". ");
            System.out.println(period);
            index += 1;
        }
    }

    //@@author syj02

    /**
     * Prints all Appointment entries tracked.
     *
     * @throws AssertionError If appointments list is empty.
     */
    public static void printAppointmentHistory() throws CustomExceptions.OutOfBounds {
        if (APPOINTMENTS.isEmpty()) {
            throw new CustomExceptions.OutOfBounds(ErrorConstant.APPOINTMENT_EMPTY_ERROR);
        }
        assert !APPOINTMENTS.isEmpty() : ErrorConstant.EMPTY_APPOINTMENT_LIST_ERROR;
        int index = 1;
        System.out.println(HealthConstant.APPOINTMENT_HISTORY_HEADER);
        for (Appointment appointment : APPOINTMENTS) {
            System.out.print(index + ". ");
            System.out.println(appointment);
            index += 1;
        }
    }

    //@@l5_z

    /**
     * Clears the periods, bmis, and appointments lists.
     *
     * @throws AssertionError If periods, bmis, and appointments lists are not empty.
     */
    public static void clearHealthLists() {
        PERIODS.clear();
        BMIS.clear();
        APPOINTMENTS.clear();
        assert BMIS.isEmpty() : ErrorConstant.BMI_LIST_UNCLEARED_ERROR;
        assert PERIODS.isEmpty() : ErrorConstant.PERIOD_LIST_UNCLEARED_ERROR;
        assert APPOINTMENTS.isEmpty() : ErrorConstant.APPOINTMENT_LIST_UNCLEARED_ERROR;
    }

    //@@author j013n3

    /**
     * Prints the last three Period objects added to the periods list.
     */
    public static void printLatestThreeCycles() {
        Output.printLine();
        int startIndex = 0;
        int endIndex = HealthConstant.LATEST_THREE_CYCLE_LENGTHS;
        assert startIndex >= 0 : ErrorConstant.START_INDEX_NEGATIVE_ERROR;

        for (int i = startIndex; i < endIndex; i++) {
            System.out.println(PERIODS.get(i));
        }

    }

    /**
     * Predicts the start date of the next period based on the average cycle length of the last three cycles.
     *
     * @return The predicted start date of the next period.
     * @throws AssertionError If periods lists is empty.
     * @throws CustomExceptions.OutOfBounds If period list is empty.
     */
    public static LocalDate predictNextPeriodStartDate() throws CustomExceptions.OutOfBounds {
        if (PERIODS.isEmpty()) {
            throw new CustomExceptions.OutOfBounds(ErrorConstant.PERIOD_EMPTY_ERROR);
        }
        assert !PERIODS.isEmpty() : ErrorConstant.EMPTY_PERIOD_LIST_ERROR;

        Period latestPeriod = PERIODS.get(0);
        return latestPeriod.nextCyclePrediction();
    }
}
