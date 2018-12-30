package sphiinx.script.private_script.the_bull.house_planking.mission.worker.impl;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.tab.Equipment;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.interactables.ItemWorker;
import sphiinx.script.private_script.the_bull.house_planking.mission.HousePlankingMission;

import java.util.function.Predicate;

public class EquipStaffOfAir extends Worker {

    public static final String STAFF_OF_AIR = "Staff of air";
    private final Predicate<Item> staff_of_air = a -> a.getName().equals(STAFF_OF_AIR);
    private final ItemWorker equip_staff_of_air = new ItemWorker(staff_of_air);
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
        equip_staff_of_air.work();
        mission.can_end = equip_staff_of_air.itemNotFound();
    }

    @Override
    public String toString() {
        return "Equipping Staff of air";
    }
}

