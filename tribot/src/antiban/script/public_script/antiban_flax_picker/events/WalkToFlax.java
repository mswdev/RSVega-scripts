package antiban.script.public_script.antiban_flax_picker.events;

import antiban.priority_framework.PriorityEvent;
import antiban.script.public_script.antiban_flax_picker.data.Location;
import org.tribot.api.General;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Objects;
import org.tribot.api2007.WebWalking;

public class WalkToFlax implements PriorityEvent {

    @Override
    public int priority() {
        return 2;
    }

    @Override
    public boolean valid() {
        return Objects.findNearest(20, "Flax").length <= 0;
    }

    @Override
    public void execute() {
        WebWalking.walkTo(Location.FLAX_FIELD.getLocation(), new Condition() {
            @Override
            public boolean active() {
                return Objects.findNearest(20, "Flax").length >= 1;
            }
        }, General.random(1000, 1200));
    }

}
