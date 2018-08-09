package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stages.mining_instructor;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

import java.util.function.Predicate;

public class MineTin extends TIWorker {

    private final Predicate<Item> TIN_ORE = a -> a.getName().equals("Tin ore");

    public MineTin(TIMission mission) {
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

        if (mission.interactWithHint(mission.getHintSceneObject()) && Time.sleepUntil(() -> Players.getLocal().getAnimation() != -1 || Players.getLocal().isMoving(), 3500))
            Time.sleepUntil(() -> Inventory.getCount(TIN_ORE) > 0, 8500);
    }

    @Override
    public String toString() {
        return "[MINING GUIDE]: Mining Tin ore";
    }
}

