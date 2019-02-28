package org.api.script.impl.worker.interactables;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.worker.MovementWorker;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;

import java.util.function.Predicate;

public class SceneObjectWorker extends Worker {

    private final Predicate<SceneObject> scene_object_predicate;
    private final Predicate<String> action;
    private final MovementWorker movement_worker;

    public SceneObjectWorker(Predicate<SceneObject> scene_object_predicate) {
        this(scene_object_predicate, a -> true, null);
    }

    public SceneObjectWorker(Predicate<SceneObject> scene_object_predicate, Predicate<String> action) {
        this(scene_object_predicate, action, null);
    }

    public SceneObjectWorker(Predicate<SceneObject> scene_object_predicate, MovementWorker movement_worker) {
        this(scene_object_predicate, a -> true, movement_worker);
    }

    public SceneObjectWorker(Predicate<SceneObject> scene_object_predicate, Predicate<String> action, MovementWorker movement_worker) {
        this.scene_object_predicate = scene_object_predicate;
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

        final SceneObject scene_object = SceneObjects.getNearest(scene_object_predicate);
        if (scene_object == null) {
            if (movement_worker == null)
                return;

            movement_worker.work();
            return;
        }

        if (!scene_object.isPositionInteractable()) {
            Movement.walkTo(scene_object);
            return;
        }

        scene_object.interact(action);
        Time.sleepWhile(() -> Players.getLocal().isMoving(), 2500);
    }

    @Override
    public String toString() {
        return "Executing scene object worker.";
    }
}

