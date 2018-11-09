package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.ItemActionWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.AirOrbChargeWorker;

import java.util.function.Predicate;

public class EquipGlory extends AirOrbChargeWorker {

    public final String item_name = "glory(";
    private final Predicate<Item> item = a -> a.getName().contains(item_name);
    private final ItemActionWorker equip_glory;

    public EquipGlory(AirOrbChargeMission mission) {
        super(mission);
        equip_glory = new ItemActionWorker<>(item);
    }

    @Override
    public void work() {
        equip_glory.work();
        mission.can_end = equip_glory.itemNotFound();
    }

    @Override
    public String toString() {
        return "Equipping Amulet of glory";
    }
}

