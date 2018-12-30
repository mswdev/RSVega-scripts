package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.Bank;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.banking.WithdrawWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;

import java.util.function.Predicate;

public class WithdrawUnpoweredOrbs extends Worker {

    public static final Predicate<Item> UNPOWERED_ORB = a -> a.getName().equals("Unpowered orb");
    private final WithdrawWorker withdraw_unpowered_orbs = new WithdrawWorker(UNPOWERED_ORB, Bank.WithdrawMode.ITEM, 0);
    private final AirOrbChargeMission mission;

    public WithdrawUnpoweredOrbs(AirOrbChargeMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        withdraw_unpowered_orbs.work();
        mission.can_end = withdraw_unpowered_orbs.itemNotFound();
    }

    @Override
    public String toString() {
        return "Withdrawing Unpowered orbs.";
    }
}

