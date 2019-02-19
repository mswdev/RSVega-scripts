package sphiinx.script.public_script.spx_aio_firemaking.api.script.impl.worker.banking;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.DepositBox;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.script.public_script.spx_aio_firemaking.api.script.framework.worker.Worker;

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

        // [TODO - 2018-12-13]: Temporary until Blast Furnace bank locations are fixed.
        Position temp_blast_furnace_position = new Position(1948, 4957);
        if (temp_blast_furnace_position.distance() <= 25) {
            final SceneObject bank_chest = SceneObjects.getNearest("Bank chest");
            if (bank_chest == null)
                return;

            bank_chest.click();
            return;
        }

        Bank.open(BankLocation.getNearest());
    }

    @Override
    public String toString() {
        return "Executing open bank worker.";
    }
}

