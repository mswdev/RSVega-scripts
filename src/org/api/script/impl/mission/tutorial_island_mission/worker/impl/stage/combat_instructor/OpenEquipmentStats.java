package org.api.script.impl.mission.tutorial_island_mission.worker.impl.stage.combat_instructor;

import org.api.script.framework.worker.Worker;
import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Interfaces;

import java.util.function.Predicate;

public class OpenEquipmentStats extends Worker {

    private static final int INTER_MASTER = 387;
    private static final int INTER_EQUIPMENT_CHILD = 17;
    private static final Predicate<String> EQUIPMENT_VIEW_BUTTON = a -> a.equals("View equipment stats");

    @Override
    public boolean needsRepeat() {
        return false;
    }

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
        return "Opening equipment stats.";
    }
}

