package sphiinx.script.public_script.spx_air_orbs.mission.charge;

import sphiinx.api.framework.goal.GoalList;
import sphiinx.api.framework.mission.Mission;
import sphiinx.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_air_orbs.mission.charge.worker.AirOrbChargeWorkerHandler;

public class AirOrbChargeMission extends Mission {

    private final AirOrbChargeWorkerHandler MANAGER = new AirOrbChargeWorkerHandler(this);
    public boolean can_end;

    @Override
    public String getMissionName() {
        return "Air Orb Charging";
    }

    @Override
    public String getWorkerName() {
        Worker<AirOrbChargeMission> c = MANAGER.getCurrent();
        return c == null ? "WORKER" : c.getClass().getSimpleName();
    }

    @Override
    public String getWorkerString() {
        Worker<AirOrbChargeMission> c = MANAGER.getCurrent();
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
        MANAGER.work();
        return 150;
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

