package sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.magic.level_to_25;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.Bank;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.WithdrawItemWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.level.AirOrbLevelMission;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.AirOrbLevelWorker;

import java.util.function.Predicate;

public class WithdrawMindRunes extends AirOrbLevelWorker {

    public static final Predicate<Item> ITEM = a -> a.getName().equals("Mind rune");
    private final WithdrawItemWorker withdraw_mind_runes;

    public WithdrawMindRunes(AirOrbLevelMission mission) {
        super(mission);
        withdraw_mind_runes = new WithdrawItemWorker(ITEM, Bank.WithdrawMode.ITEM, 0);
    }

    @Override
    public void work() {
        withdraw_mind_runes.work();
        mission.can_end = withdraw_mind_runes.itemNotFound();
    }

    @Override
    public String toString() {
        return "Withdrawing Mind runes";
    }
}

