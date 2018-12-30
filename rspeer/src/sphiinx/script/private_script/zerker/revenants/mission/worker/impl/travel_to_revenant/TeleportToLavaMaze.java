package sphiinx.script.private_script.zerker.revenants.mission.worker.impl.travel_to_revenant;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.script.impl.worker.interactables.ItemWorker;
import sphiinx.script.private_script.zerker.revenants.mission.RevenantMission;

import java.util.function.Predicate;

public class TeleportToLavaMaze extends Worker {

    static final Predicate<Item> BURNING_AMULET = a -> a.getName().contains("Burning amulet(");
    private static final Predicate<String> RUB_OPTION = a -> a.equals("Lava Maze") || a.contains("Okay, teleport to");
    private static final ItemWorker RUB_BURNING_AMULET = new ItemWorker(BURNING_AMULET, a -> a.equals("Rub"));
    private final RevenantMission mission;

    public TeleportToLavaMaze(RevenantMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1)
            return;

        if (Dialog.isViewingChatOptions()) {
            if (Dialog.process(RUB_OPTION))
                Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1, 1500);
        } else {
            RUB_BURNING_AMULET.work();
            mission.can_end = RUB_BURNING_AMULET.itemNotFound();
        }
    }

    @Override
    public String toString() {
        return "Teleporting to Lava Maze.";
    }
}

