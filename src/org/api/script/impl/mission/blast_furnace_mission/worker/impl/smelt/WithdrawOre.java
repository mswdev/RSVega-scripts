package org.api.script.impl.mission.blast_furnace_mission.worker.impl.smelt;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.mission.blast_furnace_mission.BlastFurnaceMission;
import org.api.script.impl.mission.blast_furnace_mission.worker.impl.WithdrawCoalBag;
import org.api.script.impl.worker.banking.WithdrawWorker;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.tab.Inventory;

import java.util.function.Predicate;

public class WithdrawOre extends Worker {

    private static final int COAL_VARP = 547;
    private final int ingrediant_one_minimum = (BlastFurnaceMission.bar_type.getIngrediantOneAmount() / 2) * 27;
    private final Predicate<Item> ingrediant_one = a -> a.getName().equals(BlastFurnaceMission.bar_type.getIngrediantOne().getName());
    private final Predicate<Item> ingrediant_two = a -> a.getName().equals(BlastFurnaceMission.bar_type.getIngrediantTwo().getName());
    private final WithdrawWorker withdraw_worker_ingrediant_one = new WithdrawWorker(ingrediant_one, 0, Bank.WithdrawMode.ITEM);
    private final WithdrawWorker withdraw_worker_ingrediant_two = new WithdrawWorker(ingrediant_two, 0, Bank.WithdrawMode.ITEM);
    private final BlastFurnaceMission mission;

    public WithdrawOre(BlastFurnaceMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (mission.is_coal_bag_empty) {
            if (Inventory.contains(ingrediant_one)) {
                fillCoalBag();
            } else {
                withdrawIngrediantOne();
            }
        } else if (Varps.get(COAL_VARP) <= ingrediant_one_minimum - 27) {
            if (Inventory.contains(ingrediant_one)) {
                mission.is_smelting = true;
            } else {
                withdrawIngrediantOne();
            }
        } else {
            if (Inventory.contains(ingrediant_two)) {
                mission.is_smelting = true;
            } else {
                withdrawIngrediantTwo();
            }
        }
    }

    private void fillCoalBag() {
        final Item coal_bag = Inventory.getFirst(WithdrawCoalBag.COAL_BAG);
        if (coal_bag.click())
            if (Time.sleepUntil(Dialog::isOpen, 1500))
                mission.is_coal_bag_empty = false;
    }

    private void withdrawIngrediantOne() {
        withdraw_worker_ingrediant_one.work();
        mission.should_end = withdraw_worker_ingrediant_one.itemNotFound();
    }

    private void withdrawIngrediantTwo() {
        withdraw_worker_ingrediant_two.work();
        mission.should_end = withdraw_worker_ingrediant_two.itemNotFound();
    }

    @Override
    public String toString() {
        return "Withdrawing ore.";
    }
}

