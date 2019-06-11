package org.script.private_script.the_bull.house_planking.mission.worker.impl;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.worker.banking.WithdrawWorker;
import org.rspeer.runetek.adapter.component.Item;
import org.script.private_script.the_bull.house_planking.mission.HousePlankingMission;

import java.util.function.Predicate;

public class WithdrawLawRune extends Worker {

    public static final Predicate<Item> LAW_RUNE = a -> a.getName().equals("Law rune");
    private static WithdrawWorker WITHDRAW_LAW_RUNE;

    public WithdrawLawRune(HousePlankingMission mission) {
        WITHDRAW_LAW_RUNE = new WithdrawWorker(mission, LAW_RUNE, 0);
    }

    @Override
    public void work() {
        WITHDRAW_LAW_RUNE.work();
    }

    @Override
    public String toString() {
        return "Withdrawing Law runes";
    }
}

