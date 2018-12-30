package sphiinx.script.private_script.saranga07.blast_furnace.mission.worker.impl;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.Bank;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.banking.WithdrawWorker;
import sphiinx.script.private_script.saranga07.blast_furnace.mission.BlastFurnaceMission;

import java.util.function.Predicate;

public class WithdrawCoalBag extends Worker {

    public static final Predicate<Item> COAL_BAG = a -> a.getName().equals("Coal bag");
    private final WithdrawWorker withdraw_worker = new WithdrawWorker(COAL_BAG, Bank.WithdrawMode.ITEM);
    private final BlastFurnaceMission mission;

    public WithdrawCoalBag(BlastFurnaceMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        withdraw_worker.work();
        mission.can_end = withdraw_worker.itemNotFound();
    }

    @Override
    public String toString() {
        return "Getting coal bag.";
    }
}

