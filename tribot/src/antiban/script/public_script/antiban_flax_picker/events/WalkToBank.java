package antiban.script.public_script.antiban_flax_picker.events;

import antiban.priority_framework.PriorityEvent;
import org.tribot.api.General;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.WebWalking;

public class WalkToBank implements PriorityEvent {

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public boolean valid() {
        return !Banking.isInBank() && Inventory.isFull();
    }

    @Override
    public void execute() {
        WebWalking.walkToBank(new Condition() {
            @Override
            public boolean active() {
                return Banking.isInBank();
            }
        }, General.random(1000, 1200));
    }

}
