package antiban.script.public_script.antiban_flax_picker.events;

import org.tribot.api2007.Player;
import antiban.api.game.timing.Waiting;
import antiban.priority_framework.PriorityEvent;
import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Objects;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSObject;

public class PickFlax implements PriorityEvent {

    private RSObject[] FLAX = null;

    @Override
    public int priority() {
        return 3;
    }

    @Override
    public boolean valid() {
        FLAX = Objects.findNearest(20, "Flax");
        return FLAX.length >= 1 && Player.getAnimation() == -1 && !Player.isMoving();
    }

    @Override
    public void execute() {
        if (!FLAX[0].isClickable()) {
            WebWalking.walkTo(FLAX[0], new Condition() {
                @Override
                public boolean active() {
                    Camera.turnToTile(FLAX[0]);
                    return FLAX[0].isClickable();
                }
            }, General.random(1000, 1200));
        } else {
            if (Clicking.click("Pick", FLAX[0])) {
                Waiting.waitForCondition(() -> Player.getAnimation() != -1 && !Player.isMoving(), General.random(1000, 1200));
            }

        }
    }

}
