package sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.survival_expert;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

import java.util.function.Predicate;

public class CatchShrimp extends TI_Worker {

    private final Predicate<Item> RAW_SHRIMPS = a -> a.getName().equals("Raw shrimps");
    private final Predicate<Npc> FISHING_SPOT = a -> a.getName().equals("Fishing spot");
    private final Predicate<String> NET = a -> a.equals("Net");

    public CatchShrimp(TI_Mission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return Inventory.getCount(RAW_SHRIMPS) < 2;
    }

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1)
            return;

        final Npc NPC = Npcs.getNearest(FISHING_SPOT);
        if (NPC == null)
            return;

        if (NPC.interact(NET) && Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1 || NPC.distance() <= 1, 3500))
            Time.sleepUntil(() -> Inventory.contains(RAW_SHRIMPS), 8500);
    }

    @Override
    public String toString() {
        return "[SURVIVAL EXPERT]: Fishing shrimp";
    }
}

