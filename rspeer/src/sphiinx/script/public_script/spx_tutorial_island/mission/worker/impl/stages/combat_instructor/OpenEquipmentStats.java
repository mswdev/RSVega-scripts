package sphiinx.script.public_script.spx_tutorial_island.mission.worker.impl.stages.combat_instructor;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Interfaces;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;
import sphiinx.script.public_script.spx_tutorial_island.mission.worker.TIWorker;

import java.util.function.Predicate;

public class OpenEquipmentStats extends TIWorker {

    private final Predicate<String> EQUIPMENT = a -> a.equals("View equipment stats");
    private final int MASTER = 387, CHILD = 17;

    public OpenEquipmentStats(TIMission mission) {
        super(mission);
    }

    @Override
    public boolean shouldExecute() {
        return true;
    }

    @Override
    public void work() {
        final InterfaceComponent STATS = Interfaces.getComponent(MASTER, CHILD);
        if (STATS == null)
            return;

        if (STATS.interact(EQUIPMENT))
            Time.sleepUntil(() -> Interfaces.getComponent(MASTER, CHILD).isVisible(), 1500);
    }

    @Override
    public String toString() {
        return "[COMBAT INSTRUCTOR]: Opening equipment stats";
    }
}

