package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.interactables.ItemWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;

import java.util.function.Predicate;

public class EquipStaffOfAir extends Worker {

    public static final String ITEM_NAME = "Staff of air";
    private final Predicate<Item> staff_of_air = a -> a.getName().equals(ITEM_NAME);
    private final ItemWorker equip_staff_of_air = new ItemWorker(staff_of_air);
    private final AirOrbChargeMission mission;

    public EquipStaffOfAir(AirOrbChargeMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        equip_staff_of_air.work();
        mission.can_end = equip_staff_of_air.itemNotFound();
    }

    @Override
    public String toString() {
        return "Equipping Staff of air.";
    }
}

