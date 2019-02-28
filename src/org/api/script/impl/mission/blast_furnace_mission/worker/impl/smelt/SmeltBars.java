package org.api.script.impl.mission.blast_furnace_mission.worker.impl.smelt;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.mission.blast_furnace_mission.BlastFurnaceMission;
import org.api.script.impl.mission.blast_furnace_mission.worker.impl.WithdrawCoalBag;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.SceneObjects;

import java.util.function.Predicate;

public class SmeltBars extends Worker {

    private static final Predicate<SceneObject> CONVEYOR_BELT = a -> a.getName().equals("Conveyor belt") && a.containsAction("Put-ore-on");
    private static final Predicate<String> EMPTY_COAL_BAG = a -> a.equals("Empty");
    private final BlastFurnaceMission mission;

    public SmeltBars(BlastFurnaceMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        final SceneObject conveyor_belt = SceneObjects.getNearest(CONVEYOR_BELT);
        if (conveyor_belt == null)
            return;

        if (Inventory.isFull()) {
            if (conveyor_belt.click())
                Time.sleepUntil(() -> !Inventory.isFull(), 1500);

            if (mission.is_coal_bag_empty)
                mission.is_smelting = false;
        } else {
            final Item coal_bag = Inventory.getFirst(WithdrawCoalBag.COAL_BAG);
            if (coal_bag == null)
                return;

            if (coal_bag.interact(EMPTY_COAL_BAG))
                if (Time.sleepUntil(Inventory::isFull, 1500))
                    mission.is_coal_bag_empty = true;
        }
    }

    @Override
    public String toString() {
        return "Smelting bars.";
    }
}

