package sphiinx.script.public_script.spx_aio_firemaking.mission;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.movement.position.Position;
import sphiinx.api.framework.goal.GoalList;
import sphiinx.api.framework.goal.impl.InfiniteGoal;
import sphiinx.api.framework.mission.Mission;
import sphiinx.api.framework.worker.Worker;
import sphiinx.script.public_script.spx_aio_firemaking.data.LogType;
import sphiinx.script.public_script.spx_aio_firemaking.mission.worker.FM_WorkerManager;

import java.util.function.Predicate;

public class FM_Mission extends Mission {

    public Position current_lane_position;

    public final Predicate<Item> TINDERBOX = a -> a.getName().equals("Tinderbox");
    public final FM_WorkerManager MANAGER = new FM_WorkerManager(this);

    @Override
    public boolean canEnd() {
        return goals.hasReachedGoals();
    }

    @Override
    public String getMissionName() {
        return "SPX AIO Firemaking";
    }

    @Override
    public String getWorkerName() {
        Worker<FM_Mission> c = MANAGER.getCurrent();
        return c == null ? "WORKER" : c.getClass().getSimpleName();
    }

    @Override
    public String getWorkerString() {
        Worker<FM_Mission> c = MANAGER.getCurrent();
        return c == null ? "Loading workers" : c.toString();
    }

    @Override
    public boolean shouldPrintWorkerString() {
        return true;
    }

    @Override
    public String getEndMessage() {
        return null;
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
     * Gets the best usable log in the players bank.
     *
     * @return The best usable log.
     * */
    public LogType getBestUsableLog() {
        final LogType[] LOGS = LogType.values();
        for (int i = LOGS.length - 1; i >= 0; i--) {
            final LogType LOG = LOGS[i];
            if (Skills.getLevel(Skill.FIREMAKING) < LOG.getRequiredLevel())
                continue;

            if (!Bank.contains(LOG.getName()))
                continue;

            return LOG;
        }

        return null;
    }
}

