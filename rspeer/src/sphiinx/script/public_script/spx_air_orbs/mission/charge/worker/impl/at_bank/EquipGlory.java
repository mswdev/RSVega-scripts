package sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.impl.at_bank;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.interactables.ItemWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;

import java.util.function.Predicate;

public class EquipGlory extends Worker {

    public final String item_name = "glory(";
    private final Predicate<Item> item = a -> a.getName().contains(item_name);
    private final ItemWorker equip_glory = new ItemWorker(item);
    private final AirOrbChargeMission mission;

    public EquipGlory(AirOrbChargeMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        equip_glory.work();
        mission.can_end = equip_glory.itemNotFound();
    }

    @Override
    public String toString() {
        return "Equipping Amulet of glory.";
    }
}

