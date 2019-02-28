package org.api.script.impl.mission.questing.restless_ghost_mission.worker.impl;

import org.api.script.framework.worker.Worker;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.SceneObjects;

public class PlaceSkullWorker extends Worker {


    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        final SceneObject coffin = SceneObjects.getNearest("Coffin");
        Inventory.use(a -> a.getName().equals("Ghost's skull"), coffin);
    }

    @Override
    public String toString() {
        return "Executing item worker.";
    }
}

