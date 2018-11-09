package sphiinx.script.private_script.saranga07.blast_furnace.mission.worker.impl;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.Bank;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.WithdrawItemWorker;
import sphiinx.script.private_script.saranga07.blast_furnace.mission.BlastFurnaceMission;
import sphiinx.script.private_script.saranga07.blast_furnace.mission.worker.BlastFurnaceWorker;

import java.util.function.Predicate;

public class WithdrawCoalBag extends BlastFurnaceWorker {

    public static final Predicate<Item> COAL_BAG = a -> a.getName().equals("Coal bag");
    private final WithdrawItemWorker withdraw_coal_bag;

    public WithdrawCoalBag(BlastFurnaceMission mission) {
        super(mission);
        withdraw_coal_bag = new WithdrawItemWorker<>(COAL_BAG, Bank.WithdrawMode.ITEM);
    }

    @Override
    public void work() {
        withdraw_coal_bag.work();
        mission.can_end = withdraw_coal_bag.itemNotFound();
    }

    @Override
    public String toString() {
        return "Getting coal bag";
    }
}

