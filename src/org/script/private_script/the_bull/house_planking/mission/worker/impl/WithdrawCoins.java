package org.script.private_script.the_bull.house_planking.mission.worker.impl;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.worker.banking.WithdrawWorker;
import org.rspeer.runetek.adapter.component.Item;

import java.util.function.Predicate;

public class WithdrawCoins extends Worker {

    public static final Predicate<Item> COINS = a -> a.getName().equals("Coins");
    private static final WithdrawWorker WITHDRAW_COINS = new WithdrawWorker(COINS, 0);

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        WITHDRAW_COINS.work();
    }

    @Override
    public String toString() {
        return "Withdrawing coins";
    }
}

