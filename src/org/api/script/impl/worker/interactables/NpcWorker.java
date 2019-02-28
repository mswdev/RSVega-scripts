package org.api.script.impl.worker.interactables;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.worker.DialogueWorker;
import org.api.script.impl.worker.MovementWorker;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;

import java.util.function.Predicate;

public class NpcWorker extends Worker {

    private final Predicate<Npc> npc_predicate;
    private final Predicate<String> action;
    private final DialogueWorker dialogue_worker;
    private final MovementWorker movement_worker;

    public NpcWorker(Predicate<Npc> npc_predicate) {
        this(npc_predicate, a -> true, new DialogueWorker(), null);
    }

    public NpcWorker(Predicate<Npc> npc_predicate, Predicate<String> action) {
        this(npc_predicate, action, new DialogueWorker(), null);
    }

    public NpcWorker(Predicate<Npc> npc_predicate, DialogueWorker dialogue_worker) {
        this(npc_predicate, a -> true, dialogue_worker, null);
    }

    public NpcWorker(Predicate<Npc> npc_predicate, MovementWorker movement_worker) {
        this(npc_predicate, a -> true, new DialogueWorker(), movement_worker);
    }

    public NpcWorker(Predicate<Npc> npc_predicate, DialogueWorker dialogue_worker, MovementWorker movement_worker) {
        this(npc_predicate, a -> true, dialogue_worker, movement_worker);
    }

    public NpcWorker(Predicate<Npc> npc_predicate, Predicate<String> action, DialogueWorker dialogue_worker) {
        this(npc_predicate, action, dialogue_worker, null);
    }

    public NpcWorker(Predicate<Npc> npc_predicate, Predicate<String> action, MovementWorker movement_worker) {
        this(npc_predicate, action, new DialogueWorker(), movement_worker);
    }

    public NpcWorker(Predicate<Npc> npc_predicate, Predicate<String> action, DialogueWorker dialogue_worker, MovementWorker movement_worker) {
        this.npc_predicate = npc_predicate;
        this.action = action;
        this.dialogue_worker = dialogue_worker;
        this.movement_worker = movement_worker;
    }

    @Override
    public boolean needsRepeat() {
        return dialogue_worker.needsRepeat() || (movement_worker != null && movement_worker.needsRepeat());
    }

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1)
            return;

        if (Dialog.isOpen() || Game.isInCutscene() || Game.isLoadingRegion() || Dialog.isProcessing()) {
            dialogue_worker.work();
            return;
        }

        final Npc npc = Npcs.getNearest(npc_predicate);
        if (npc == null) {
            if (movement_worker == null)
                return;

            movement_worker.work();
            return;
        }

        if (!npc.isPositionInteractable()) {
            Movement.walkTo(npc);
            return;
        }

        npc.interact(action);
        Time.sleepWhile(() -> Players.getLocal().isMoving(), 2500);
    }

    @Override
    public String toString() {
        return "Executing npc worker.";
    }
}

