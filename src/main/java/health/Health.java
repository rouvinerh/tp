package health;

import java.time.LocalDate;

/**
 * Represents a Health object to track user's health information.
 */
public class Health {
    /**
     * The date of Health object.
     */
    protected LocalDate date = null;

    /**
     * Constructor for Health object.
     */
    public Health() {
    }

    /**
     * Retrieves the date of Health object.
     *
     * @return The date of the Health object of LocalDate type.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns a string containing the date of the Health object.
     *
     * @return A string of Health object.
     */
    @Override
    public String toString(){
        return getDate().toString();
    }
}
