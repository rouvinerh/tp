package health;

import utility.Parser;
import constants.ErrorConstant;
import constants.UiConstant;
import constants.HealthConstant;

import java.time.LocalDate;

/**
 * The {@code Bmi} class inherits from the {@code Health} class.
 * It contains information about the height, weight, and bmi value of the user, and provides functionalities
 * to calculate and categories the BMI values.
 */
public class Bmi extends Health {
    private double bmiValue;

    private LocalDate date;

    private HealthList healthList = new HealthList();
    private double height;
    private double weight;
    private Parser parser = new Parser();


    //@@author j013n3
    /**
     * Constructor for {@code Bmi} object.
     *
     * @param height A string representing the user's height.
     * @param weight A string representing the user's weight.
     * @param date A string representing the date of the record.
     * @throws AssertionError If height or weight values are not positive.
     */
    public Bmi(String height, String weight, String date) {
        this.height = Double.parseDouble(height);
        this.weight = Double.parseDouble(weight);

        assert this.height > HealthConstant.MIN_HEIGHT && this.weight > HealthConstant.MIN_WEIGHT
                : ErrorConstant.NEGATIVE_VALUE_ERROR;

        this.date = parser.parseDate(date);

        this.bmiValue = calculateBmiValue();
        healthList.addBmi(this);
    }

    /**
     * Retrieves height recorded in {@code Bmi} object of {@code String} type.
     *
     * @return The height recorded in the {@code Bmi} object.
     */
    public String getHeight() {
        return String.format(HealthConstant.TWO_DECIMAL_PLACE_FORMAT, height);
    }

    /**
     * Retrieves weight recorded in {@code Bmi} object of {@code String} type.
     *
     * @return The weight recorded in the {@code Bmi} object.
     */
    public String getWeight() {
        return String.format(HealthConstant.TWO_DECIMAL_PLACE_FORMAT, weight);
    }

    /**
     * Retrieves BMI value recorded in {@code Bmi} object of {@code String} type.
     *
     * @return The BMI value recorded in the {@code Bmi} object.
     */
    public String getBmiValueString() {
        return String.format(HealthConstant.TWO_DECIMAL_PLACE_FORMAT, bmiValue);
    }

    /**
     * Retrieves BMI value recorded in {@code Bmi} object of {@code Double} type.
     *
     * @return The BMI value recorded in the {@code Bmi} object.
     */
    public Double getBmiValueDouble() {
        return this.bmiValue;
    }

    /**
     * Prints the BMI category based on the calculated {@code bmiValue}.
     *
     * @param bmiValue The Bmi value to categorize.
     * @return A string presenting the Bmi category.
     * @throws AssertionError If calculated value is not positive.
     */
    public static String getBmiCategory(double bmiValue) {
        assert bmiValue > HealthConstant.MIN_BMI: ErrorConstant.NEGATIVE_BMI_ERROR;
        if (bmiValue < HealthConstant.UNDERWEIGHT_BMI_THRESHOLD) {
            return HealthConstant.UNDERWEIGHT_MESSAGE;
        } else if (bmiValue < HealthConstant.NORMAL_BMI_THRESHOLD) {
            return HealthConstant.NORMAL_WEIGHT_MESSAGE;
        } else if (bmiValue < HealthConstant.OVERWEIGHT_BMI_THRESHOLD) {
            return HealthConstant.OVERWEIGHT_MESSAGE;
        } else if (bmiValue < HealthConstant.OBESE_BMI_THRESHOLD) {
            return HealthConstant.OBESE_MESSAGE;
        } else {
            return HealthConstant.SEVERELY_OBESE_MESSAGE;
        }
    }

    /**
     * Retrieves date recorded in {@code Bmi} object of {@code LocalDate} type.
     *
     * @return The date recorded in the {@code Bmi} object.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Calculates {@code bmiValue} based on the height and weight recorded.
     *
     * @return The calculated Bmi value.
     * @throws AssertionError If calculated value is not positive.
     */
    private double calculateBmiValue() {
        double bmi = Math.round((weight / (Math.pow(height, UiConstant.POWER_OF_TWO))) * UiConstant.ROUNDING_FACTOR)
                / UiConstant.ROUNDING_FACTOR;
        assert bmi > HealthConstant.MIN_BMI: ErrorConstant.NEGATIVE_BMI_ERROR;
        return bmi;
    }

    /**
     * Returns the string presentation of a {@code Bmi} object.
     *
     * @return A formatted string representing a {@code Bmi} object.
     */
    @Override
    public String toString() {
        return String.format(HealthConstant.PRINT_BMI_FORMAT,
                this.getDate(),
                this.calculateBmiValue(),
                getBmiCategory(bmiValue));
    }
}
