package sphiinx.script.private_script.the_bull.house_planking.mission.worker.impl;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.banking.WithdrawWorker;
import sphiinx.script.private_script.the_bull.house_planking.mission.HousePlankingMission;

import java.util.function.Predicate;

public class WithdrawEarthRune extends Worker {


    public static final Predicate<Item> EARTH_RUNE = a -> a.getName().equals("Earth rune");
    private static final WithdrawWorker WITHDRAW_EARTH_RUNE = new WithdrawWorker(EARTH_RUNE, 0);
    private final HousePlankingMission mission;

    public WithdrawEarthRune(HousePlankingMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        WITHDRAW_EARTH_RUNE.work();
        if (WITHDRAW_EARTH_RUNE.itemNotFound())
            mission.can_end = true;
    }

    @Override
    public String toString() {
        return "Withdrawing Earth runes";
    }
}

