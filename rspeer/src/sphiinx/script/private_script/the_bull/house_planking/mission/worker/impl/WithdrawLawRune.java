package sphiinx.script.private_script.the_bull.house_planking.mission.worker.impl;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.banking.WithdrawWorker;
import sphiinx.script.private_script.the_bull.house_planking.mission.HousePlankingMission;

import java.util.function.Predicate;

public class WithdrawLawRune extends Worker {

    private final HousePlankingMission mission;

    public WithdrawLawRune(HousePlankingMission mission) {
        this.mission = mission;
    }

    public static final Predicate<Item> LAW_RUNE = a -> a.getName().equals("Law rune");
    private static final WithdrawWorker WITHDRAW_LAW_RUNE = new WithdrawWorker(LAW_RUNE, 0);

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        WITHDRAW_LAW_RUNE.work();
        if (WITHDRAW_LAW_RUNE.itemNotFound())
            mission.can_end = true;
    }

    @Override
    public String toString() {
        return "Withdrawing Law runes";
    }
}

