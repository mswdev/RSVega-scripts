package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.Bank;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.banking.WithdrawWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;

import java.util.function.Predicate;

public class WithdrawCosmicRunes extends Worker {

    public static final Predicate<Item> ITEM = a -> a.getName().contains("Cosmic rune");
    private final WithdrawWorker withdraw_cosmic_runes = new WithdrawWorker(ITEM, Bank.WithdrawMode.ITEM, 81);
    private final AirOrbChargeMission mission;

    public WithdrawCosmicRunes(AirOrbChargeMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        withdraw_cosmic_runes.work();
        mission.can_end = withdraw_cosmic_runes.itemNotFound();
    }

    @Override
    public String toString() {
        return "Withdrawing Cosmic runes.";
    }
}

