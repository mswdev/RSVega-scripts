package org.api.script.impl.mission.tutorial_island_mission.worker.impl.stage.survival_expert;

import org.api.game.skills.firemaking.LogType;
import org.api.game.skills.woodcutting.TreeType;
import org.api.script.framework.worker.Worker;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;

public class ChopTree extends Worker {

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1)
            return;

        final SceneObject object = SceneObjects.getNearest(TreeType.TREE.getName());
        if (object == null)
            return;

        if (object.click() && Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1, 3500))
            Time.sleepUntil(() -> Inventory.contains(LogType.LOGS.getName()), 6500);
    }

    @Override
    public String toString() {
        return "Chopping tree.";
    }
}

