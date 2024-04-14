package health;

import constants.ErrorConstant;
import constants.HealthConstant;
import utility.Parser;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The {@code Appointment} class inherits from the {@code Health} class.
 * It contains information about the date, time, and description of the appointment.
 */
public class Appointment extends Health {

    private LocalDate date;
    private LocalTime time;
    private String description;
    private final Parser parser = new Parser();
    private final HealthList healthList = new HealthList();

    //@@author syj02

    /**
     * Constructor for {@code Appointment} object.
     *
     * @param stringDate A string representing the date of the appointment.
     * @param stringTime A string representing the time of the appointment.
     * @param description A string describing the appointment.
     */
    public Appointment(String stringDate, String stringTime, String description) {
        this.date = parser.parseDate(stringDate);
        this.time = parser.parseTime(stringTime);
        this.description = description;
        healthList.addAppointment(this);
    }

    /**
     * Retrieves the date of the appointment of LocalDate type.
     *
     * @return The date of appointment.
     */
    public LocalDate getDate() {
        assert date != null : ErrorConstant.NULL_DATE_ERROR;
        return this.date;
    }

    /**
     * Retrieves the time of the appointment of {@code LocalTime} type.
     *
     * @return The time of appointment.
     */
    public LocalTime getTime() {
        assert time != null : ErrorConstant.NULL_TIME_ERROR;
        return this.time;
    }

    /**
     * Retrieves the description of the appointment of {@code String} type.
     *
     * @return The description of appointment.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string representation of an {@code Appointment} object.
     *
     * @return A formatted string representing an {@code Appointment} object.
     */
    @Override
    public String toString() {
        return String.format(HealthConstant.PRINT_APPOINTMENT_FORMAT,
                getDate(),
                getTime(),
                getDescription());
    }
}
