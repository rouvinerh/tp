package utility;

import constants.ErrorConstant;
import storage.LogFile;
/**
 * Represents a custom exception class designed for PulsePilot to handle errors during command processing.
 */
public class CustomExceptions extends Exception {

    //@@author JustinSoh
    /**
     * Prints the error for an OutOfBounds error, and logs it in the log file as an error.
     */
    public static class OutOfBounds extends Exception {
        public OutOfBounds(String message) {
            super(ErrorConstant.COLOR_HEADING + "Out of Bounds Error: " + message  + ErrorConstant.COLOR_ENDING);
            LogFile.writeLog("Out of Bounds Error:" + message, true);
        }
    }

    /**
     * Prints the error for an InvalidInput error, and logs it in the log file as an error.
     */
    public static class InvalidInput extends Exception {
        public InvalidInput(String message) {
            super(ErrorConstant.COLOR_HEADING + "Invalid Input Error: " + message + ErrorConstant.COLOR_ENDING);
            LogFile.writeLog("Invalid Input Error:" + message, true);
        }
    }

    //@@author L5-Z
    /**
     * Prints the error for an FileReadError error, and logs it in the log file as an error.
     */
    public static class FileReadError extends Exception{
        public FileReadError(String message) {
            super(ErrorConstant.COLOR_HEADING + "File Read Error: " + message + ErrorConstant.COLOR_ENDING);
            LogFile.writeLog("File Read Error:" + message, true);
        }
    }

    /**
     * Prints the error for an FileWriteError error, and logs it in the log file as an error.
     */
    public static class FileWriteError extends Exception{
        public FileWriteError(String message) {
            super( ErrorConstant.COLOR_HEADING + "File Write Error: " + message + ErrorConstant.COLOR_ENDING);
            LogFile.writeLog("File Write Error:" + message, true);
        }
    }

    /**
     * Prints the error for an FileCreateError error, and logs it in the log file as an error.
     */
    public static class FileCreateError extends Exception{
        public FileCreateError(String message) {
            super(ErrorConstant.COLOR_HEADING + "File Create Error: " + message + ErrorConstant.COLOR_ENDING);
            LogFile.writeLog("File Create Error:" + message, true);
        }
    }

    //@@author JustinSoh
    /**
     * Prints the error for an InsufficientInput error, and logs it in the log file as an error.
     */
    public static class InsufficientInput extends Exception {
        public InsufficientInput(String message) {
            super(ErrorConstant.COLOR_HEADING + "Insufficient Input Error: " + message + ErrorConstant.COLOR_ENDING);
            LogFile.writeLog("Insufficient Input Error:" + message, true);
        }
    }
}
