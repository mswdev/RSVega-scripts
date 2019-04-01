package org.api.script.impl.mission.tutorial_island_mission.worker.impl.stage.mining_instructor;

import org.api.script.framework.worker.Worker;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.SceneObjects;

public class TempSmeltOreWorker extends Worker {
    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        final SceneObject furnace = SceneObjects.getNearest(a -> a.getName().equals("Furnace"));
        if (furnace == null)
            return;

        if (furnace.click())
            Time.sleepUntil(() -> Inventory.getFirst(a -> a.getName().equals("Bronze bar")) != null, 4000);
    }

    @Override
    public String toString() {
        return "Executing hint worker.";
    }
}
