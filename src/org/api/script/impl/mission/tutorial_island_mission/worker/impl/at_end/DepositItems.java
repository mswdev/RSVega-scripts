package org.api.script.impl.mission.tutorial_island_mission.worker.impl.at_end;

import org.api.script.framework.worker.Worker;
import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;

public class DepositItems extends Worker {


    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Players.getLocal().isMoving() && Movement.getDestinationDistance() > 10)
            return;

        if (Bank.isOpen()) {
            if (Bank.depositInventory())
                Time.sleepUntil(() -> Inventory.getCount() <= 0, 1500);
        } else {
            if (Bank.open(BankLocation.getNearestWithdrawable()))
                Time.sleepUntil(Bank::isOpen, 1500);
        }
    }

    @Override
    public String toString() {
        return "Depositing items.";
    }
}

