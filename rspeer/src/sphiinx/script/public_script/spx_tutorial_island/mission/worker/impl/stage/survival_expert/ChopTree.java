package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stage.survival_expert;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.api.game_util.skills.firemaking.LogType;
import sphiinx.script.public_script.spx_tutorial_island.api.game_util.skills.woodcutting.TreeType;
import sphiinx.script.public_script.spx_tutorial_island.mission.TutorialIslandMission;

public class ChopTree extends Worker<TutorialIslandMission> {

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
        return "Chopping tree";
    }
}

