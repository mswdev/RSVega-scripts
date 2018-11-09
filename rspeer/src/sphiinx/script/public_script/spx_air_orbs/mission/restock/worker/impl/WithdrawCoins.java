package sphiinx.script.public_script.spx_air_orbs.mission.restock.worker.impl;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.Bank;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.WithdrawItemWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.restock.AirOrbRestockMission;
import sphiinx.script.public_script.spx_air_orbs.mission.restock.worker.AirOrbRestockWorker;

import java.util.function.Predicate;

public class WithdrawCoins extends AirOrbRestockWorker {

    public boolean has_coins;
    private final Predicate<Item> ITEM = a -> a.getId() == 995;
    private final WithdrawItemWorker WITHDRAW_COINS;

    public WithdrawCoins(AirOrbRestockMission mission) {
        super(mission);
        WITHDRAW_COINS = new WithdrawItemWorker<>(ITEM, Bank.WithdrawMode.ITEM, 0);
    }

    @Override
    public void work() {
        WITHDRAW_COINS.work();
        has_coins = WITHDRAW_COINS.itemNotFound();
    }

    @Override
    public String toString() {
        return "Withdrawing coins";
    }
}

