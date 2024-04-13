package seedu.pulsepilot;

import ui.Handler;

/**
 * Main class representing the entry-point for PulsePilot.
 */
public class PulsePilot {
    /**
     * Main entry-point for PulsePilot.
     */
    public static void main(String[] args) {

        Handler handler = new Handler();
        handler.initialiseBot();
        handler.processInput();
        handler.terminateBot();
    }
}
