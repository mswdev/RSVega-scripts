package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stages.mining_instructor;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

import java.util.function.Predicate;

public class ProspectTin extends TIWorker {

    private final Position TIN = new Position(3077, 9504, 0);
    private final Predicate<String> PROSPECT = a -> a.equals("Prospect");

    public ProspectTin(TIMission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1)
            return;

        final SceneObject OBJECT = SceneObjects.getFirstAt(TIN);
        if (OBJECT == null)
            return;

        if (OBJECT.interact(PROSPECT) && Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1 || OBJECT.distance() <= 1, 3500))
            Time.sleepUntil(Interfaces::canContinue, 1500);
    }

    @Override
    public String toString() {
        return "[MINING GUIDE]: Prospecting Tin ore";
    }
}

