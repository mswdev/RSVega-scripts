package sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.magic;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.ItemActionWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.level.AirOrbLevelMission;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.AirOrbLevelWorker;

import java.util.function.Predicate;

public class EquipStaffOfAir extends AirOrbLevelWorker {

    public static final String ITEM_NAME = "Staff of air";
    private final Predicate<Item> item = a -> a.getName().equals(ITEM_NAME);
    private final ItemActionWorker equip_staff_of_air;

    public EquipStaffOfAir(AirOrbLevelMission mission) {
        super(mission);
        equip_staff_of_air = new ItemActionWorker(item);
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

