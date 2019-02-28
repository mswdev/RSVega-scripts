package org.api.script.impl.mission.tutorial_island_mission.worker.impl;

import org.api.script.framework.worker.Worker;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;

import java.util.function.BooleanSupplier;
import java.util.stream.Stream;

public class HintWorker extends Worker {

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Players.getLocal().isMoving() && Movement.getDestinationDistance() > 10)
            return;

        if (Players.getLocal().getAnimation() != -1 || Game.getClient().getHintArrowType() <= 0)
            return;

        final SceneObject object = getHintSceneObject();
        final BooleanSupplier boolean_supplier = () -> Players.getLocal().isMoving();
        if (object != null) {
            if (object.isPositionInteractable()) {
                if (object.interact(a -> true))
                    Time.sleepUntil(boolean_supplier, 1500);
            } else {
                if (Movement.walkTo(object))
                    Time.sleepUntil(boolean_supplier, 1500);
            }
        } else {
            final Npc npc = getHintNPC();
            if (npc == null)
                return;

            if (npc.isPositionInteractable()) {
                if (npc.interact(a -> true))
                    Time.sleepUntil(boolean_supplier, 1500);
            } else {
                if (Movement.walkTo(npc))
                    Time.sleepUntil(boolean_supplier, 1500);
            }
        }
    }

    private Npc getHintNPC() {
        return Npcs.getAt(Game.getClient().getHintArrowNpcIndex());
    }

    private SceneObject getHintSceneObject() {
        return Stream.of(SceneObjects.getAt(getPosition()))
                .filter(a -> a.containsAction(b -> true))
                .findFirst()
                .orElse(null);
    }

    private Position getPosition() {
        return new Position(Game.getClient().getHintArrowX(), Game.getClient().getHintArrowY(), Players.getLocal().getFloorLevel());
    }

    @Override
    public String toString() {
        return "Executing hint worker.";
    }
}

