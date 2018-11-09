package sphiinx.script.private_script.zerker.revenants.mission.worker.impl.travel_to_revenant;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.workers.ItemActionWorker;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.private_script.zerker.revenants.mission.RevenantMission;

import java.util.function.Predicate;

public class TeleportToLavaMaze extends Worker<RevenantMission> {

    static final Predicate<Item> BURNING_AMULET = a -> a.getName().contains("Burning amulet(");
    private static final Predicate<String> RUB_OPTION = a -> a.equals("Lava Maze") || a.contains("Okay, teleport to");
    private static final ItemActionWorker RUB_BURNING_AMULET = new ItemActionWorker(BURNING_AMULET, a -> a.equals("Rub"));

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
        return "Teleporting to Lava Maze";
    }
}

