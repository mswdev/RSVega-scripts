package org.api.script.impl.mission.blast_furnace_mission.worker.impl;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.mission.blast_furnace_mission.BlastFurnaceMission;
import org.api.script.impl.worker.banking.WithdrawWorker;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;

import java.util.function.Predicate;

public class EquipIceGloves extends Worker {

    private static final Predicate<Item> ICE_GLOVES = a -> a.getName().equals("Ice gloves");
    private final WithdrawWorker withdraw_worker = new WithdrawWorker(ICE_GLOVES, Bank.WithdrawMode.ITEM);
    private final BlastFurnaceMission mission;

    public EquipIceGloves(BlastFurnaceMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        final Item item = Inventory.getFirst(ICE_GLOVES);
        if (item == null) {
            withdraw_worker.work();
            mission.should_end = withdraw_worker.itemNotFound();
        } else {
            if (item.click())
                Time.sleepUntil(() -> Inventory.getFirst(ICE_GLOVES) == null, 1500);
        }
    }

    @Override
    public String toString() {
        return "Equipping ice gloves.";
    }
}

