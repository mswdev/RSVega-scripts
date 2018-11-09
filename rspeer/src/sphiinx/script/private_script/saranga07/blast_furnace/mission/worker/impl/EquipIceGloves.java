package sphiinx.script.private_script.saranga07.blast_furnace.mission.worker.impl;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.WithdrawItemWorker;
import sphiinx.script.private_script.saranga07.blast_furnace.mission.BlastFurnaceMission;
import sphiinx.script.private_script.saranga07.blast_furnace.mission.worker.BlastFurnaceWorker;

import java.util.function.Predicate;

public class EquipIceGloves extends BlastFurnaceWorker {

    private static final Predicate<Item> ICE_GLOVES = a -> a.getName().equals("Ice gloves");
    private final WithdrawItemWorker withdraw_ice_gloves;

    public EquipIceGloves(BlastFurnaceMission mission) {
        super(mission);
        withdraw_ice_gloves = new WithdrawItemWorker(ICE_GLOVES, Bank.WithdrawMode.ITEM);
    }

    @Override
    public void work() {
        final Item item = Inventory.getFirst(ICE_GLOVES);
        if (item == null) {
            withdraw_ice_gloves.work();
            mission.can_end = withdraw_ice_gloves.itemNotFound();
        } else {
            if (item.click())
                Time.sleepUntil(() -> Inventory.getFirst(ICE_GLOVES) == null, 1500);
        }
    }

    @Override
    public String toString() {
        return "Equipping ice gloves";
    }
}

