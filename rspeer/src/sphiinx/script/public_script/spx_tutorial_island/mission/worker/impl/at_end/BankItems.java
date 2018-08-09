package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.at_end;

import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import sphiinx.script.public_script.spx_tutorial_island.data.Vars;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

public class BankItems extends TIWorker {


    public BankItems(TIMission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        if (!Vars.get().at_end_bank_items)
            return false;

        return Inventory.getCount() > 0;
    }

    @Override
    public void work() {
        if (Bank.isOpen()) {
            if (Bank.depositInventory())
                Time.sleepUntil(() -> Inventory.getCount() <= 0, 1500);
        } else {
            if (BankLocation.getNearest().getPosition().distance() <= 15) {
                if (Bank.open(BankLocation.getNearest()))
                    Time.sleepUntil(Bank::isOpen, 1500);
            } else {
                if (Movement.walkTo(BankLocation.getNearest().getPosition().randomize(3)))
                    Time.sleepUntil(() -> BankLocation.getNearest().getPosition().distance() <= 15, 1500);
            }
        }
    }

    @Override
    public String toString() {
        return "[END]: Banking items";
    }
}

