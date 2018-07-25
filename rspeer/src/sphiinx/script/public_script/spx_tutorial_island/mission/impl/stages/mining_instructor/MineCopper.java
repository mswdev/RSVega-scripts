package sphiinx.script.public_script.spx_tutorial_island.mission.impl.stages.mining_instructor;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TI_Worker;

import java.util.function.Predicate;

public class MineCopper extends TI_Worker {

    private final Predicate<Item> COPPER_ORE = a -> a.getName().equals("Copper ore");

    public MineCopper(TI_Mission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void work() {
        if (Players.getLocal().getAnimation() != -1)
            return;

        if (mission.interactWithHint() && Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1 || Players.getLocal().isMoving(), 3500))
            Time.sleepUntil(() -> Inventory.getCount(COPPER_ORE) > 0, 8500);
    }

    @Override
    public String toString() {
        return "[MINING GUIDE]: Mining Copper ore";
    }
}

