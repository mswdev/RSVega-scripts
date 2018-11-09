package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.ItemActionWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.AirOrbChargeWorker;

import java.util.function.Predicate;

public class EquipStaffOfAir extends AirOrbChargeWorker {

    public static final String ITEM_NAME = "Staff of air";
    private final Predicate<Item> staff_of_air = a -> a.getName().equals(ITEM_NAME);
    private final ItemActionWorker equip_staff_of_air;

    public EquipStaffOfAir(AirOrbChargeMission mission) {
        super(mission);
        equip_staff_of_air = new ItemActionWorker(staff_of_air);
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

