package sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.magic.level_to_45;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.Bank;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.banking.WithdrawWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.level.AirOrbLevelMission;

import java.util.function.Predicate;

public class WithdrawFireRunes extends Worker {

    public static final Predicate<Item> ITEM = a -> a.getName().equals("Fire rune");
    private final WithdrawWorker withdraw_fire_runes = new WithdrawWorker(ITEM, Bank.WithdrawMode.ITEM, 0);
    private final AirOrbLevelMission mission;

    public WithdrawFireRunes(AirOrbLevelMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        withdraw_fire_runes.work();
        mission.can_end = withdraw_fire_runes.itemNotFound();
    }

    @Override
    public String toString() {
        return "Withdrawing Fire runes.";
    }
}

