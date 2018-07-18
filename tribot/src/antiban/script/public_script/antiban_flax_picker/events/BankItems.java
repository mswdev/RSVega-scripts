package antiban.script.public_script.antiban_flax_picker.events;

import antiban.api.game.timing.Waiting;
import antiban.priority_framework.PriorityEvent;
import org.tribot.api.General;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;

public class BankItems implements PriorityEvent {

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public boolean valid() {
        return Banking.isInBank() && (Inventory.isFull() || Inventory.getCount("Flax") > 0);
    }

    @Override
    public void execute() {
        if (Banking.isBankScreenOpen()) {
            if (Banking.depositAll() > 0)
                Waiting.waitForCondition(() -> Inventory.getCount("Flax") <= 0, General.random(1000, 1200));
        } else {
            Banking.openBank();
        }
    }

}
