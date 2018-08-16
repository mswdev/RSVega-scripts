package sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.ui.Log;
import sphiinx.script.public_script.spx_air_orbs.mission.level.AirOrbLevelMission;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.AirOrbLevelWorker;

import java.util.function.Predicate;

public class GetItemFromBank extends AirOrbLevelWorker {

    protected Predicate<Item> item;
    protected int amount;

    public GetItemFromBank(AirOrbLevelMission mission) {
        super(mission);
    }

    @Override
    public void work() {
            if (Bank.isOpen()) {
                if (Bank.contains(item)) {
                    if (amount == 0) {
                        if (Bank.withdrawAll(item))
                            Time.sleepUntil(() -> Inventory.contains(item), 1500);
                    } else {
                        if (Bank.withdraw(item, amount))
                            Time.sleepUntil(() -> Inventory.contains(item), 1500);
                    }
                } else {
                    //todo NEEDS MULE
                    Log.severe("We don't have anymore of the item, we need to mule.");
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
        return "Getting items from the bank";
    }
}

