package org.api.script.impl.worker.interactables;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.worker.MovementWorker;
import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.runetek.api.scene.Players;

import java.util.function.Predicate;

public class PickableWorker extends Worker {

    private final Predicate<Pickable> pickable_predicate;
    private final Predicate<String> action;
    private final MovementWorker movement_worker;

    public PickableWorker(Predicate<Pickable> pickable_predicate) {
        this(pickable_predicate, a -> true, null);
    }

    public PickableWorker(Predicate<Pickable> pickable_predicate, Predicate<String> action) {
        this(pickable_predicate, action, null);
    }

    public PickableWorker(Predicate<Pickable> pickable_predicate, MovementWorker movement_worker) {
        this(pickable_predicate, a -> true, movement_worker);
    }

    public PickableWorker(Predicate<Pickable> pickable_predicate, Predicate<String> action, MovementWorker movement_worker) {
        this.pickable_predicate = pickable_predicate;
        this.action = action;
        this.movement_worker = movement_worker;
    }

    @Override
    public boolean needsRepeat() {
        return movement_worker != null && movement_worker.needsRepeat();
    }

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1)
            return;

        final Pickable pickable = Pickables.getNearest(pickable_predicate);
        if (pickable == null) {
            if (movement_worker == null)
                return;

            movement_worker.work();
            return;
        }

        if (!pickable.isPositionInteractable()) {
            Movement.walkTo(pickable);
            return;
        }

        pickable.interact(action);
        Time.sleepWhile(() -> Players.getLocal().isMoving(), 2500);
    }

    @Override
    public String toString() {
        return "Executing pickable worker.";
    }
}

