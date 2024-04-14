package health;

import java.time.LocalDate;

/**
 * The Health class represents a Health object to track user's health information.
 */
public class Health {

    private LocalDate date = null;

    public Health() {
    }

    /**
     * Retrieves the date of Health object of {@code LocalDate} type.
     *
     * @return The date of the Health object.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns a string containing the date of the {@code Health} object.
     *
     * @return A formatted string representing a {@code Health} object.
     */
    @Override
    public String toString(){
        return getDate().toString();
    }
}
