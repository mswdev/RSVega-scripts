package sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.magic;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.Bank;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.banking.WithdrawWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.level.AirOrbLevelMission;

import java.util.function.Predicate;

public class WithdrawLawRunes extends Worker {

    public static final Predicate<Item> ITEM = a -> a.getName().equals("Law rune");
    private final WithdrawWorker withdraw_law_runes = new WithdrawWorker(ITEM, Bank.WithdrawMode.ITEM, 0);
    private final AirOrbLevelMission mission;

    public WithdrawLawRunes(AirOrbLevelMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        withdraw_law_runes.work();
        mission.can_end = withdraw_law_runes.itemNotFound();
    }

    @Override
    public String toString() {
        return "Withdrawing Law runes.";
    }
}

