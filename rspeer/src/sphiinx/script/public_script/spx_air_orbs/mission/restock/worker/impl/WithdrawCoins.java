package sphiinx.script.public_script.spx_air_orbs.mission.restock.worker.impl;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.Bank;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.banking.WithdrawWorker;

import java.util.function.Predicate;

public class WithdrawCoins extends Worker {

    public boolean has_coins;
    private final Predicate<Item> ITEM = a -> a.getId() == 995;
    private final WithdrawWorker WITHDRAW_COINS = new WithdrawWorker(ITEM, Bank.WithdrawMode.ITEM, 0);

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        WITHDRAW_COINS.work();
        has_coins = WITHDRAW_COINS.itemNotFound();
    }

    @Override
    public String toString() {
        return "Withdrawing coins.";
    }
}

