package sphiinx.script.public_script.spx_air_orbs.mission.level;

import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.goal.GoalList;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.mission.Mission;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_air_orbs.mission.level.worker.AirOrbLevelWorkerHandler;

public class AirOrbLevelMission extends Mission {

    public boolean can_end;
    public boolean has_loaded_magic;
    private final AirOrbLevelWorkerHandler handler = new AirOrbLevelWorkerHandler(this);

    @Override
    public String getMissionName() {
        return "Air Orb Leveler";
    }

    @Override
    public String getWorkerName() {
        Worker<AirOrbLevelMission> c = handler.getCurrent();
        return c == null ? "WORKER" : c.getClass().getSimpleName();
    }

    @Override
    public String getWorkerString() {
        Worker<AirOrbLevelMission> c = handler.getCurrent();
        return c == null ? "WORKER" : c.toString();
    }

    @Override
    public boolean shouldPrintWorkerString() {
        return true;
    }

    @Override
    public boolean canEnd() {
        return can_end || (Skills.getLevel(Skill.MAGIC) >= 66 && Skills.getLevel(Skill.DEFENCE) >= 30);
    }

    @Override
    public GoalList getGoals() {
        return null;
    }

    @Override
    public int execute() {
        if (Dialog.canContinue())
            Dialog.processContinue();

        handler.work();
        return 100;
    }

    @Override
    public void onMissionStart() {

    }

    @Override
    public void onMissionEnd() {

    }

    @Override
    public String[] getMissionPaint() {
        return new String[0];
    }

    @Override
    public void resetPaint() {

    }
}

