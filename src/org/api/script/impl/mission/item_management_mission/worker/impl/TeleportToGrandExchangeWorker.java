package org.api.script.impl.mission.item_management_mission.worker.impl;

import org.api.script.framework.worker.Worker;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.scene.Players;

import java.util.function.Predicate;

public class TeleportToGrandExchangeWorker extends Worker {

    public static final Predicate<Item> RING_OF_WEALTH = a -> a.getName().contains("Ring of wealth (");
    public static final int[] RING_OF_WEALTH_IDS = new int[]{
            11980,
            11982,
            11984,
            11986,
            11988
    };

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Equipment.interact(RING_OF_WEALTH, "Grand Exchange"))
            Time.sleepWhile(() -> Players.getLocal().getAnimation() != -1, 2500);
    }

    @Override
    public String toString() {
        return "Executing teleport to Grand Exchange worker.";
    }
}
