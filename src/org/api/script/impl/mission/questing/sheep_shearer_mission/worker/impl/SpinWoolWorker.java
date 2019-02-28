package org.api.script.impl.mission.questing.sheep_shearer_mission.worker.impl;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.worker.MovementWorker;
import org.api.script.impl.worker.interactables.SceneObjectWorker;
import org.rspeer.runetek.api.commons.StopWatch;
import org.rspeer.runetek.api.component.Production;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;

public class SpinWoolWorker extends Worker {

    private final SceneObjectWorker scene_object_worker = new SceneObjectWorker(a -> a.getName().equals("Spinning wheel"), new MovementWorker(new Position(3209, 3213, 1)));
    private StopWatch stop_watch = StopWatch.start();

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1) {
            stop_watch = StopWatch.start();
            return;
        }

        if (stop_watch.getElapsed().getSeconds() >= 3) {
            if (Production.isOpen()) {
                Production.initiate(0);
                return;
            }

            scene_object_worker.work();
        }
    }

    @Override
    public String toString() {
        return scene_object_worker.toString();
    }
}

