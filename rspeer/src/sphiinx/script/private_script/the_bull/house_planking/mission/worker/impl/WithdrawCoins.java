package sphiinx.script.private_script.the_bull.house_planking.mission.worker.impl;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.banking.WithdrawWorker;
import sphiinx.script.private_script.the_bull.house_planking.mission.HousePlankingMission;

import java.util.function.Predicate;

public class WithdrawCoins extends Worker {


    public static final Predicate<Item> COINS = a -> a.getName().equals("Coins");
    private static final WithdrawWorker WITHDRAW_COINS = new WithdrawWorker(COINS, 0);
    private final HousePlankingMission mission;

    public WithdrawCoins(HousePlankingMission mission) {
        this.mission = mission;
    }

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

