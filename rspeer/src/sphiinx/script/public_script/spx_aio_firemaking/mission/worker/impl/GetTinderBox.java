package sphiinx.script.public_script.spx_aio_firemaking.mission.worker.impl;

import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import sphiinx.script.public_script.spx_aio_firemaking.mission.FM_Mission;
import sphiinx.script.public_script.spx_aio_firemaking.mission.worker.FM_Worker;

public class GetTinderBox extends FM_Worker {

    public GetTinderBox(FM_Mission mission) {
        super(mission);
    }

    @Override
    public void work() {
        if (Bank.isOpen()) {
            if (Bank.withdraw(mission.TINDERBOX, 1))
                Time.sleepUntil(() -> Inventory.contains(mission.TINDERBOX), 1500);
        } else {
            if (Bank.open(BankLocation.getNearest())) {
                Time.sleepUntil(Bank::isOpen, 1500);
            } else {
                if (Movement.walkTo(BankLocation.getNearest().getPosition().randomize(5))) {
                    Time.sleepUntil(() -> BankLocation.getNearest().getPosition().distance() <= 10, 1500);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Getting tinderbox";
    }
}

