package org.api.script.impl.worker;

import org.api.script.framework.worker.Worker;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;

public class MovementWorker extends Worker {

    private final Position position;
    private final int off_set;

    public MovementWorker(Position position) {
        this(position, 0);
    }

    public MovementWorker(Position position, int off_set) {
        this.position = position;
        this.off_set = off_set;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Players.getLocal().isMoving() && Movement.getDestinationDistance() > 10)
            return;

        //Temporary until webwalker supports enabling run energy.
        if (!Movement.isRunEnabled() && Movement.getRunEnergy() > 10)
            Movement.toggleRun(true);

        if (position.distance() <= off_set + 1)
            return;

        Movement.walkTo(position.randomize(off_set));
    }

    @Override
    public String toString() {
        return "Executing movement worker. [Distance: " + position.distance() + "] | [" + position.toString() + "]";
    }
}

