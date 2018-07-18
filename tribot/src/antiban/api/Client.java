package antiban.api;

import org.tribot.script.ScriptManifest;

public class Client {

    /**
     * Returns the script manifest of the class.
     *
     * @param c The class.
     * @return The script manifest; null there is no manifest.
     */
    public static ScriptManifest getManifest(Class<?> c) {
        if (c == null || !c.isAnnotationPresent(ScriptManifest.class))
            return null;

        return c.getAnnotation(ScriptManifest.class);
    }

}
