package sphiinx.script.public_script.spx_air_orbs.mission.restock;

import sphiinx.script.public_script.spx_tutorial_island.api.framework.goal.GoalList;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.mission.Mission;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.SPXScript;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.AirOrbChargeMission;
import sphiinx.script.public_script.spx_air_orbs.mission.level.AirOrbLevelMission;
import sphiinx.script.public_script.spx_air_orbs.mission.restock.worker.AirOrbRestockWorkerHandler;

public class AirOrbRestockMission extends Mission {


    public boolean can_end;
    private final SPXScript script;
    private final AirOrbRestockWorkerHandler handler = new AirOrbRestockWorkerHandler(this);

    public AirOrbRestockMission(SPXScript script) {
        this.script = script;
    }

    @Override
    public String getMissionName() {
        return "Air Orb Restocking";
    }

    @Override
    public String getWorkerName() {
        Worker<AirOrbRestockMission> c = handler.getCurrent();
        return c == null ? "WORKER" : c.getClass().getSimpleName();
    }

    @Override
    public String getWorkerString() {
        Worker<AirOrbRestockMission> c = handler.getCurrent();
        return c == null ? "WORKER" : c.toString();
    }

    @Override
    public boolean shouldPrintWorkerString() {
        return true;
    }

    @Override
    public boolean canEnd() {
        return can_end;
    }

    @Override
    public GoalList getGoals() {
        return null;
    }

    @Override
    public int execute() {
        handler.work();
        return 100;
    }

    @Override
    public void onMissionStart() {

    }

    @Override
    public void onMissionEnd() {
        script.getMissionHandler().getMissions().add(new AirOrbLevelMission());
        script.getMissionHandler().getMissions().add(new AirOrbChargeMission(script));
        script.getMissionHandler().getMissions().add(new AirOrbRestockMission(script));
    }

    @Override
    public String[] getMissionPaint() {
        return new String[0];
    }

    @Override
    public void resetPaint() {

    }
}

