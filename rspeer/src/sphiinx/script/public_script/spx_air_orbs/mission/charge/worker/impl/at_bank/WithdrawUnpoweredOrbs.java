package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.Bank;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.WithdrawItemWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.AirOrbChargeWorker;

import java.util.function.Predicate;

public class WithdrawUnpoweredOrbs extends AirOrbChargeWorker {

    public static final Predicate<Item> UNPOWERED_ORB = a -> a.getName().equals("Unpowered orb");
    private final WithdrawItemWorker withdraw_unpowered_orbs;

    public WithdrawUnpoweredOrbs(AirOrbChargeMission mission) {
        super(mission);
        withdraw_unpowered_orbs = new WithdrawItemWorker<>(UNPOWERED_ORB, Bank.WithdrawMode.ITEM, 0);
    }

    @Override
    public void work() {
        withdraw_unpowered_orbs.work();
        mission.can_end = withdraw_unpowered_orbs.itemNotFound();
    }

    @Override
    public String toString() {
        return "Withdrawing Unpowered orbs";
    }
}

