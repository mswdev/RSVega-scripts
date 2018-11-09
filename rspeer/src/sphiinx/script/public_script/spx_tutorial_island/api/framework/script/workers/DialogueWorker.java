package sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers;

import org.rspeer.runetek.adapter.Positionable;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.mission.Mission;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;

import java.util.function.Predicate;

public class DialogueWorker<mission extends Mission> extends Worker<mission> {

    private final Predicate<Npc> npc;
    private final Predicate<String> action;
    private final Positionable position;

    public DialogueWorker(Predicate<Npc> npc) {
        this(npc, a -> true, null);
    }

    public DialogueWorker(Predicate<Npc> npc, Predicate<String> action) {
        this(npc, action, null);
    }

    public DialogueWorker(Predicate<Npc> npc, Positionable position) {
        this(npc, a -> true, position);
    }

    public DialogueWorker(Predicate<Npc> npc, Predicate<String> action, Positionable position) {
        this.npc = npc;
        this.action = action;
        this.position = position;
    }

    @Override
    public boolean needsRepeat() {
        return Dialog.isOpen() || Dialog.isProcessing();
    }

    @Override
    public void work() {
        if (Dialog.isProcessing())
            return;

        if (Dialog.canContinue()) {
            if (Dialog.processContinue())
                Time.sleepUntil(Dialog::isProcessing, 1500);
        } else if (Dialog.isViewingChatOptions()) {
            if (Dialog.process(action))
                Time.sleepUntil(Dialog::isProcessing, 1500);
        } else {
            final Npc npc = Npcs.getNearest(this.npc);
            if (npc == null) {
                if (position == null || (Players.getLocal().isMoving() && Movement.getDestinationDistance() > 10))
                    return;

                if (Movement.walkTo(position))
                    Time.sleepUntil(() -> Npcs.getNearest(this.npc) != null, 1500);
                return;
            }

            if (npc.isPositionInteractable()) {
                if (npc.interact(a -> true))
                    Time.sleepUntil(Dialog::canContinue, 1500);
            } else if (Movement.walkTo(npc))
                Time.sleepUntil(npc::isPositionInteractable, 1500);
        }
    }

    @Override
    public String toString() {
        return "Executing chat dialogue worker";
    }
}

