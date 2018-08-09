package sphiinx.script.public_script.spx_aio_firemaking.mission;

import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.movement.position.Position;
import sphiinx.api.framework.goal.GoalList;
import sphiinx.api.framework.goal.impl.InfiniteGoal;
import sphiinx.api.framework.mission.Mission;
import sphiinx.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_aio_firemaking.data.LogType;
import sphiinx.script.public_script.spx_aio_firemaking.mission.worker.FireMakingWorkerHandler;

import java.util.LinkedList;

public class FireMakingMission extends Mission {

    private final FireMakingWorkerHandler MANAGER = new FireMakingWorkerHandler(this);
    public Position current_lane_start_position;
    public boolean is_stuck_in_lane;
    public boolean can_end;
    public LinkedList<Position> ignored_tiles = new LinkedList<>();
    public Position search_position;
    public int search_distance = 20;
    public int lane_length = 27;
    public int minimum_score = 0;

    @Override
    public boolean canEnd() {
        return can_end;
    }

    @Override
    public String getMissionName() {
        return "SPX AIO Firemaking";
    }

    @Override
    public String getWorkerName() {
        Worker<FireMakingMission> c = MANAGER.getCurrent();
        return c == null ? "WORKER" : c.getClass().getSimpleName();
    }

    @Override
    public String getWorkerString() {
        Worker<FireMakingMission> c = MANAGER.getCurrent();
        return c == null ? "Waiting for worker" : c.toString();
    }

    @Override
    public boolean shouldPrintWorkerString() {
        return true;
    }

    @Override
    public GoalList getGoals() {
        return new GoalList(new InfiniteGoal());
    }

    @Override
    public String[] getMissionPaint() {
        return new String[0];
    }

    @Override
    public void resetPaint() {

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

    /**
     * Gets the best usable log.
     *
     * @param check_inventory True if the inventory should be checked; false otherwise.
     * @param check_bank      True if the bank should be checked; false otherwise.
     * @return The best usable log.
     */
    public LogType getBestUsableLog(boolean check_inventory, boolean check_bank) {
        final LogType[] LOGS = LogType.values();
        for (int i = LOGS.length - 1; i >= 0; i--) {
            final LogType LOG = LOGS[i];
            if (Skills.getLevel(Skill.FIREMAKING) < LOG.getRequiredLevel())
                continue;

            if (check_inventory && !Inventory.contains(LOG.getName()))
                continue;

            if (check_bank && !Bank.contains(LOG.getName()))
                continue;

            return LOG;
        }

        return LogType.LOGS;
    }
}

