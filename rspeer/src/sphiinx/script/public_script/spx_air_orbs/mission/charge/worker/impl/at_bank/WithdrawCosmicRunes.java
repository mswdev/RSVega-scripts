package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.Bank;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.WithdrawItemWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.AirOrbChargeWorker;

import java.util.function.Predicate;

public class WithdrawCosmicRunes extends AirOrbChargeWorker {

    public static final Predicate<Item> ITEM = a -> a.getName().contains("Cosmic rune");
    private final WithdrawItemWorker withdraw_cosmic_runes;

    public WithdrawCosmicRunes(AirOrbChargeMission mission) {
        super(mission);
        withdraw_cosmic_runes = new WithdrawItemWorker(ITEM, Bank.WithdrawMode.ITEM, 81);
    }

    @Override
    public void work() {
        withdraw_cosmic_runes.work();
        mission.can_end = withdraw_cosmic_runes.itemNotFound();
    }

    @Override
    public String toString() {
        return "Withdrawing Cosmic runes";
    }
}

