package sphiinx.script.public_script.spx_aio_firemaking.mission.worker.impl;


import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.ui.Log;
import sphiinx.script.public_script.spx_aio_firemaking.data.Vars;
import sphiinx.script.public_script.spx_aio_firemaking.mission.FireMakingMission;
import sphiinx.script.public_script.spx_aio_firemaking.mission.worker.FireMakingWorker;

public class GetLogs extends FireMakingWorker {

    public GetLogs(FireMakingMission mission) {
        super(mission);
    }

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1)
            return;

        if (Bank.isOpen()) {
            if (Vars.get().is_progressive)
                Vars.get().log_type = mission.getBestUsableLog(false, true);

            if (Vars.get().log_type != null && Bank.contains(Vars.get().log_type.getName())) {
                if (Bank.withdrawAll(Vars.get().log_type.getName()))
                    Time.sleepUntil(() -> Inventory.contains(Vars.get().log_type.getName()), 1500);
            } else {
                Log.severe("[FIREMAKING]: You do not have any usable logs in your bank.");
                mission.can_end = true;
            }
        } else {
            if (BankLocation.getNearestWithdrawable().getPosition().distance() <= 15) {
                if (Bank.open(BankLocation.getNearestWithdrawable()))
                    Time.sleepUntil(Bank::isOpen, 1500);
            } else {
                if (Movement.walkTo(BankLocation.getNearestWithdrawable().getPosition().randomize(3)))
                    Time.sleepUntil(() -> BankLocation.getNearestWithdrawable().getPosition().distance() <= 15, 1500);
            }
        }
    }

    @Override
    public String toString() {
        return "Getting logs";
    }
}

