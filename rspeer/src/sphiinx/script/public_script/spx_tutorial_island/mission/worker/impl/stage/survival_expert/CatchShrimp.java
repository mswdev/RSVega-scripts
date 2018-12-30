package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stage.survival_expert;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.api.game.skills.fishing.FishType;

import java.util.function.Predicate;

public class CatchShrimp extends Worker {

    private static final Predicate<Npc> FISHING_SPOT = a -> a.getName().equals("Fishing spot");

    @Override
    public boolean needsRepeat() {
        return false;
    }

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1)
            return;

        final Npc npc = Npcs.getNearest(FISHING_SPOT);
        if (npc == null)
            return;

        if (npc.interact(FishType.SHRIMP.getAction()) && Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1, 3500))
            Time.sleepUntil(() -> Inventory.contains(FishType.SHRIMP.getName()), 6500);
    }

    @Override
    public String toString() {
        return "Catching shrimp.";
    }
}

