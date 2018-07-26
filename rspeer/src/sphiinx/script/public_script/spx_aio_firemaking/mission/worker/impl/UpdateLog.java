package sphiinx.script.public_script.spx_aio_firemaking.mission.worker.impl;

import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.ui.Log;
import sphiinx.script.public_script.spx_aio_firemaking.data.Vars;
import sphiinx.script.public_script.spx_aio_firemaking.mission.FM_Mission;
import sphiinx.script.public_script.spx_aio_firemaking.mission.worker.FM_Worker;

public class UpdateLog extends FM_Worker {


    public UpdateLog(FM_Mission mission) {
        super(mission);
    }

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1)
            return;

        if (Bank.isOpen()) {
            Vars.get().log_type = mission.getBestUsableLog();
            if (!Bank.contains(Vars.get().log_type.getName())) {
                Log.severe("[FIREMAKING]: You do not have any usable log in your bank.");
            }
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
        return "Updating log type";
    }
}

