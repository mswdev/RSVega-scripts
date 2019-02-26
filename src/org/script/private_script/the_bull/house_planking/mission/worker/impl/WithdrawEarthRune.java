package org.script.private_script.the_bull.house_planking.mission.worker.impl;

import org.rspeer.runetek.adapter.component.Item;
import org.api.script.framework.worker.Worker;
import org.api.script.impl.worker.banking.WithdrawWorker;

import java.util.function.Predicate;

public class WithdrawEarthRune extends Worker {


    public static final Predicate<Item> EARTH_RUNE = a -> a.getName().equals("Earth rune");
    private static final WithdrawWorker WITHDRAW_EARTH_RUNE = new WithdrawWorker(EARTH_RUNE, 0);

    @Override
    public boolean needsRepeat() {
        return false;
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

