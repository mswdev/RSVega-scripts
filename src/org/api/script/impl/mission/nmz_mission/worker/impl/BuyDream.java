package org.api.script.impl.mission.nmz_mission.worker.impl;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.worker.DialogueWorker;
import org.api.script.impl.worker.interactables.NpcWorker;
import org.rspeer.runetek.adapter.scene.Npc;

import java.util.function.Predicate;

public class BuyDream extends Worker {

    public static int dream_potion_varbit = 3946;
    private final Predicate<Npc> dominic_onion_predicate = a -> a.getName().equals("Dominic Onion");
    private final DialogueWorker dialogue_worker = new DialogueWorker(a -> a.contains("Previous") || a.contains("Yes"));
    private final NpcWorker npc_worker = new NpcWorker(dominic_onion_predicate, a -> a.contains("Dream"), dialogue_worker);

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        npc_worker.work();
    }

    @Override
    public String toString() {
        return "Buying dream";
    }
}

