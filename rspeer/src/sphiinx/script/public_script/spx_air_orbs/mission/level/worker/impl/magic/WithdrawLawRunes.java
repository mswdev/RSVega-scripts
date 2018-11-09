package sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.magic;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.Bank;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.WithdrawItemWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.level.AirOrbLevelMission;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.AirOrbLevelWorker;

import java.util.function.Predicate;

public class WithdrawLawRunes extends AirOrbLevelWorker {

    public static final Predicate<Item> ITEM = a -> a.getName().equals("Law rune");
    private final WithdrawItemWorker withdraw_law_runes;

    public WithdrawLawRunes(AirOrbLevelMission mission) {
        super(mission);
        withdraw_law_runes = new WithdrawItemWorker(ITEM, Bank.WithdrawMode.ITEM, 0);
    }

    @Override
    public void work() {
        withdraw_law_runes.work();
        mission.can_end = withdraw_law_runes.itemNotFound();
    }

    @Override
    public String toString() {
        return "Withdrawing Law runes";
    }
}

