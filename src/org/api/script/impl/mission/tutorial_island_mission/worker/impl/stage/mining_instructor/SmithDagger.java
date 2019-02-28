package org.api.script.impl.mission.tutorial_island_mission.worker.impl.stage.mining_instructor;

import org.api.script.framework.worker.Worker;
import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.scene.Players;

import java.util.function.Predicate;

public class SmithDagger extends Worker {

    private static final int INTER_MASTER = 312;
    private static final int INTER_DAGGER_CHILD = 2;
    private static final Predicate<String> SMITH_DAGGER = a -> a.equals("Smith 1");

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1)
            return;

        final InterfaceComponent smith = Interfaces.getComponent(INTER_MASTER, INTER_DAGGER_CHILD);
        if (smith == null)
            return;

        if (smith.interact(SMITH_DAGGER))
            Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1, 1500);
    }

    @Override
    public String toString() {
        return "Smithing dagger.";
    }
}

