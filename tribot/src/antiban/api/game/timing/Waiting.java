package antiban.api.game.timing;

import org.tribot.api.General;
import org.tribot.api.Timing;

public class Waiting {

    /**
     * Returns true if the condition is true before the timeout; false otherwise.
     *
     * @param condition The condition to check.
     * @param timeout   How many milliseconds to wait before returning false.
     * @return True if the condition is true before the timeout; false otherwise.
     */
    public static boolean waitForCondition(Condition condition, long timeout) {
        final long time = Timing.currentTimeMillis() + timeout;
        while (!condition.active()) {
            if (org.tribot.api.Timing.currentTimeMillis() >= time)
                return false;

            General.sleep(100);
        }
        return true;
    }

}
