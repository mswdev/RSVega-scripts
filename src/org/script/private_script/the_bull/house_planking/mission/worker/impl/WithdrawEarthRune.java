package org.script.private_script.the_bull.house_planking.mission.worker.impl;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.worker.banking.WithdrawWorker;
import org.rspeer.runetek.adapter.component.Item;
import org.script.private_script.the_bull.house_planking.mission.HousePlankingMission;

import java.util.function.Predicate;

public class WithdrawEarthRune extends Worker {

    public static final Predicate<Item> EARTH_RUNE = a -> a.getName().equals("Earth rune");
    private static WithdrawWorker WITHDRAW_EARTH_RUNE;

    public WithdrawEarthRune(HousePlankingMission mission) {
        WITHDRAW_EARTH_RUNE = new WithdrawWorker(mission, EARTH_RUNE, 0);
    }

    @Override
    public void work() {
        WITHDRAW_EARTH_RUNE.work();
    }

    @Override
    public String toString() {
        return "Withdrawing Earth runes";
    }
}

