package org.api.script.impl.mission.nmz_mission.worker;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.mission.nmz_mission.worker.impl.EnterDream;
import org.api.script.impl.mission.nmz_mission.worker.impl.potions.PotionType;
import org.api.script.impl.worker.interactables.ItemWorker;
import org.rspeer.runetek.api.Varps;

public class DrinkPotion extends Worker {

    private final ItemWorker item_worker = new ItemWorker(a -> a.getName().contains(PotionType.ABSORPTION.getName()));

    @Override
    public boolean needsRepeat() {
        return Varps.getBitValue(PotionType.ABSORPTION.getActiveVarpbit()) <= 950 && Varps.getBitValue(EnterDream.ACTIVE_DREAM_VARPBIT) == 1;
    }

    @Override
    public void work() {
        item_worker.work();
    }

    @Override
    public String toString() {
        return "Drinking absorption potion.";
    }
}

