package sphiinx.api.util;

public class Logging {

    /**
     * Logs the specified message to the console with the class name from where it's called.
     * //todo Replace this with a new system.
     *
     * @param c       The object from which we're calling this method.
     * @param debug   if this is a message specifically for testing builds of the script
     * @param message the message to log
     */
    public static void log(Object c, boolean debug, String message) {
        final String DEBUG_MESSAGE = "[CLASS]: " + c.getClass().getSimpleName() + " - ";
        if (debug) {
            System.out.println(DEBUG_MESSAGE + message);
        } else {
            System.out.println(message);
        }
    }

}

