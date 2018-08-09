package sphiinx.script.public_script.spx_aio_firemaking.mission.worker.impl;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import sphiinx.script.public_script.spx_aio_firemaking.mission.FireMakingMission;
import sphiinx.script.public_script.spx_aio_firemaking.mission.worker.FireMakingWorker;

import java.util.function.Predicate;

public class GetTinderBox extends FireMakingWorker {

    public static final Predicate<Item> TINDERBOX = a -> a.getName().equals("Tinderbox");

    public GetTinderBox(FireMakingMission mission) {
        super(mission);
    }

    @Override
    public void work() {
        if (Bank.isOpen()) {
            if (Bank.withdraw(TINDERBOX, 1))
                Time.sleepUntil(() -> Inventory.contains(TINDERBOX), 1500);
        } else {
            if (Bank.open(BankLocation.getNearestWithdrawable())) {
                Time.sleepUntil(Bank::isOpen, 1500);
            } else {
                if (Movement.walkTo(BankLocation.getNearestWithdrawable().getPosition().randomize(5))) {
                    Time.sleepUntil(() -> BankLocation.getNearestWithdrawable().getPosition().distance() <= 10, 1500);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Getting tinderbox";
    }
}

