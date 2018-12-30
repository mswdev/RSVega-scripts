package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stage.combat_instructor;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Equipment;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.interactables.ItemWorker;
import sphiinx.script.public_script.spx_tutorial_island.data.TutorialState;

import java.util.function.Predicate;

public class FightRat extends Worker {

    private static final Predicate<Npc> RAT = a -> a.getName().equals("Giant rat") && a.getTargetIndex() == -1;
    private static final ItemWorker EQUIP_BOW_AND_ARROWS = new ItemWorker(a -> a.getName().equals("Shortbow") || a.getName().equals("Bronze arrow"));

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Players.getLocal().isMoving() && Movement.getDestinationDistance() > 10)
            return;

        if (Players.getLocal().getTargetIndex() != -1)
            return;

        if (TutorialState.isInState(TutorialState.RANGE_RAT) && !Equipment.containsAll("Shortbow", "Bronze arrow")) {
            EQUIP_BOW_AND_ARROWS.work();
        } else {
            final Npc npc = Npcs.getNearest(RAT);
            if (npc == null)
                return;

            if (npc.isPositionInteractable() || TutorialState.isInState(TutorialState.RANGE_RAT)) {
                if (npc.click())
                    Time.sleepUntil(() -> Players.getLocal().getTargetIndex() != -1, 1500);
            } else {
                if (Movement.walkTo(npc))
                    Time.sleepUntil(npc::isPositionInteractable, 1500);
            }
        }
    }

    @Override
    public String toString() {
        return "Fighting rat.";
    }
}

