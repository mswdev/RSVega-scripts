package antiban.api.util;

import org.tribot.api.General;

public class Logging {

    private static final String DEVELOPER = "Antiban";

    /**
     * Prints the String to the Bot Debug with the header "[DEVELOPER ONLY]: ".
     *
     * @param string The String to print.
     */
    public static void botDebug(String string) {
        if (General.getTRiBotUsername().equals(DEVELOPER))
            System.out.println("[DEVELOPER ONLY]: " + string);
    }

    /**
     * Prints the String to the Client Debug with the header "[DEVELOPER ONLY]: ".
     *
     * @param string The String to print.
     */
    public static void clientDebug(String string) {
        if (General.getTRiBotUsername().equals(DEVELOPER))
            General.println("[DEVELOPER ONLY]: " + string);
    }

    /**
     * Prints the String to the Client Debug with the header "ERROR: ".
     *
     * @param string The String to print.
     */
    public static void error(String string) {
        General.println("[ERROR): " + string);
    }

    /**
     * Prints the String to the Client Debug with the header "WARNING: ".
     *
     * @param string The String to print.
     */
    public static void warning(String string) {
        General.println("[WARNING]: " + string);
    }

    /**
     * Prints the String to the Client Debug with the header "[STATUS]: ".
     *
     * @param string The String to print.
     */
    public static void status(String string) {
        General.println("[STATUS]: " + string);
    }

}
