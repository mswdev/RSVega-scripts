package sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers;

import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.DepositBox;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.mission.Mission;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;

public class OpenBankWorker<mission extends Mission> extends Worker<mission> {

    private final boolean deposit_box;

    public OpenBankWorker(boolean deposit_box) {
        this.deposit_box = deposit_box;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Players.getLocal().isMoving() && Movement.getDestinationDistance() > 10)
            return;

        if (deposit_box) {
            if (DepositBox.open(BankLocation.getNearestDepositBox()))
                Time.sleepUntil(DepositBox::isOpen, 1500);
        } else {
            if (Bank.open(BankLocation.getNearestWithdrawable()))
                Time.sleepUntil(Bank::isOpen, 1500);
        }
    }

    @Override
    public String toString() {
        return "Executing bank open worker";
    }
}

