package sphiinx.script.public_script.spx_air_orbs.mission.level.worker.impl.defence.level_to_30;

import org.rspeer.runetek.adapter.component.Item;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.interactables.ItemWorker;
import sphiinx.script.public_script.spx_air_orbs.mission.level.AirOrbLevelMission;

import java.util.function.Predicate;

public class EquipBronzeSword extends Worker {

    public static final String ITEM_NAME = "Bronze sword";
    private final Predicate<Item> item = a -> a.getName().equals(ITEM_NAME);
    private final ItemWorker equip_bronze_sword = new ItemWorker(item);
    private final AirOrbLevelMission mission;

    public EquipBronzeSword(AirOrbLevelMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        equip_bronze_sword.work();
        mission.can_end = equip_bronze_sword.itemNotFound();
    }

    @Override
    public String toString() {
        return "Equipping Bronze sword.";
    }
}

