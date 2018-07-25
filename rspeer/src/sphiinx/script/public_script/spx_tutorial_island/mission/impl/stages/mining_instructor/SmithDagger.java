package sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.mining_instructor;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

import java.util.function.Predicate;

public class SmithDagger extends TI_Worker {

    private final int SMITH_MASTER = 312, DAGGER_CHILD = 2;
    private final Predicate<String> SMITH_DAGGER = a -> a.equals("Smith 1");

    public SmithDagger(TI_Mission mission) {
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

        final InterfaceComponent SMITH = Interfaces.getComponent(SMITH_MASTER, DAGGER_CHILD);
        if (SMITH != null) {
            if (SMITH.interact(SMITH_DAGGER))
                Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1, 1500);
        } else {
            if (mission.interactWithHint() && Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1 || Players.getLocal().isMoving(), 3500))
                Time.sleepUntil(() -> Interfaces.getComponent(SMITH_MASTER, DAGGER_CHILD) != null, 1500);
        }
    }

    @Override
    public String toString() {
        return "[MINING GUIDE]: Smithing dagger";
    }
}

