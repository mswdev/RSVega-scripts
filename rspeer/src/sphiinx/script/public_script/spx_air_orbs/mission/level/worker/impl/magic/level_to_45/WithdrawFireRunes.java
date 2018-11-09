package sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.magic.level_to_45;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.Bank;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.WithdrawItemWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.level.AirOrbLevelMission;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.AirOrbLevelWorker;

import java.util.function.Predicate;

public class WithdrawFireRunes extends AirOrbLevelWorker {

    public static final Predicate<Item> ITEM = a -> a.getName().equals("Fire rune");
    private final WithdrawItemWorker withdraw_fire_runes;

    public WithdrawFireRunes(AirOrbLevelMission mission) {
        super(mission);
        withdraw_fire_runes = new WithdrawItemWorker(ITEM, Bank.WithdrawMode.ITEM, 0);
    }

    @Override
    public void work() {
        withdraw_fire_runes.work();
        mission.can_end = withdraw_fire_runes.itemNotFound();
    }

    @Override
    public String toString() {
        return "Withdrawing Fire runes";
    }
}

