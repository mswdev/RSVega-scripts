package org.script.private_script.the_bull.house_planking.mission.worker.impl;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.worker.banking.WithdrawWorker;
import org.api.script.impl.worker.interactables.ItemWorker;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.script.private_script.the_bull.house_planking.mission.HousePlankingMission;

import java.util.function.Predicate;

public class EquipStaffOfAir extends Worker {

    public static final String STAFF_OF_AIR = "Staff of air";
    private final Predicate<Item> staffOfAir = a -> a.getName().equals(STAFF_OF_AIR);
    private final WithdrawWorker withdrawStaffOfAir = new WithdrawWorker(staffOfAir);
    private final ItemWorker equipStaffOfAir = new ItemWorker(staffOfAir, withdrawStaffOfAir);
    private final HousePlankingMission mission;

    public EquipStaffOfAir(HousePlankingMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return !Equipment.contains(STAFF_OF_AIR);
    }

    @Override
    public void work() {
        equipStaffOfAir.work();
        mission.shouldEnd = equipStaffOfAir.itemNotFound();
    }

    @Override
    public String toString() {
        return "Equipping Staff of air";
    }
}

