package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stage.combat_instructor;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Interfaces;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.mission.TutorialIslandMission;

import java.util.function.Predicate;

public class OpenEquipmentStats extends Worker<TutorialIslandMission> {

    private static final int INTER_MASTER = 387;
    private static final int INTER_EQUIPMENT_CHILD = 17;
    private static final Predicate<String> EQUIPMENT_VIEW_BUTTON = a -> a.equals("View equipment stats");

    @Override
    public void work() {
        final InterfaceComponent STATS = Interfaces.getComponent(INTER_MASTER, INTER_EQUIPMENT_CHILD);
        if (STATS == null)
            return;

        if (STATS.interact(EQUIPMENT_VIEW_BUTTON))
            Time.sleepUntil(() -> Interfaces.getComponent(INTER_MASTER, INTER_EQUIPMENT_CHILD).isVisible(), 1500);
    }

    @Override
    public String toString() {
        return "Opening equipment stats";
    }
}

