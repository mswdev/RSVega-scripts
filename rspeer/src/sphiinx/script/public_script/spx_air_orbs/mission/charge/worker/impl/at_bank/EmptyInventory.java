package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank;

import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.AirOrbChargeWorker;

public class EmptyInventory extends AirOrbChargeWorker {


    public EmptyInventory(AirOrbChargeMission mission) {
        super(mission);
    }

    @Override
    public void work() {
        if (Bank.isOpen()) {
            if (Bank.depositInventory())
                Time.sleepUntil(Inventory::isEmpty, 1500);
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
        return "Emptying inventory";
    }
}

