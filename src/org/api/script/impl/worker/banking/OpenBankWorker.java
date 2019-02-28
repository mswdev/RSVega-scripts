package org.api.script.impl.worker.banking;

import org.api.script.framework.worker.Worker;
import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.DepositBox;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;

public class OpenBankWorker extends Worker {

    private final boolean deposit_box;

    public OpenBankWorker() {
        this(false);
    }

    public OpenBankWorker(boolean deposit_box) {
        this.deposit_box = deposit_box;
    }

    @Override
    public boolean needsRepeat() {
        return !Bank.isOpen() && !DepositBox.isOpen();
    }

    @Override
    public void work() {
        if (Players.getLocal().isMoving() && Movement.getDestinationDistance() > 10)
            return;

        if (deposit_box) {
            DepositBox.open(BankLocation.getNearestDepositBox());
            return;
        }

        Bank.open(BankLocation.getNearestWithdrawable());
    }

    @Override
    public String toString() {
        return "Executing open bank worker.";
    }
}

