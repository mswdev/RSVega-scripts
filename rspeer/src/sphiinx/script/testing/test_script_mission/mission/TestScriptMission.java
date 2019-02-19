package sphiinx.script.testing.test_script_mission.mission;

import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import sphiinx.api.game.skills.firemaking.LogType;
import sphiinx.api.game.skills.woodcutting.AxeType;
import sphiinx.api.script.SPXScript;
import sphiinx.api.script.framework.goal.GoalList;
import sphiinx.api.script.framework.goal.impl.InfiniteGoal;
import sphiinx.api.script.framework.goal.impl.SkillGoal;
import sphiinx.api.script.framework.item_management.ItemManagement;
import sphiinx.api.script.framework.item_management.ItemManagementEntry;
import sphiinx.api.script.framework.mission.Mission;
import sphiinx.api.script.framework.worker.Worker;
import sphiinx.script.testing.test_script_mission.mission.worker.TestScriptWorkerHandler;

public class TestScriptMission extends Mission implements ItemManagement {

    private final TestScriptWorkerHandler worker_handler;


    public TestScriptMission(SPXScript script) {
        super(script);
        worker_handler = new TestScriptWorkerHandler();
    }

    @Override
    public String getMissionName() {
        return "Test Script Mission";
    }

    @Override
    public String getWorkerName() {
        Worker c = worker_handler.getCurrent();
        return c == null ? "WORKER" : c.getClass().getSimpleName();
    }

    @Override
    public String getWorkerString() {
        Worker c = worker_handler.getCurrent();
        return c == null ? "Waiting for worker." : c.toString();
    }

    @Override
    public boolean shouldPrintWorkerString() {
        return true;
    }

    @Override
    public boolean shouldEnd() {
        return false;
    }

    @Override
    public GoalList getGoals() {
        return new GoalList(new InfiniteGoal());
    }

    @Override
    public int execute() {
        worker_handler.work();
        return 100;
    }

    @Override
    public void onMissionStart() {
    }

    @Override
    public void onMissionEnd() {
    }

    @Override
    public ItemManagementEntry[] itemsToBuy() {
        return new ItemManagementEntry[] {
                new ItemManagementEntry(this, AxeType.BRONZE.getItemID(), 1, 3000, new GoalList(new SkillGoal(Skills.getLevel(Skill.WOODCUTTING), AxeType.BRONZE.getRequiredWoodcuttingLevel()))),
                new ItemManagementEntry(this, AxeType.RUNE.getItemID(), 1, 10000, new GoalList(new SkillGoal(Skills.getLevel(Skill.WOODCUTTING), AxeType.BRONZE.getRequiredWoodcuttingLevel())))
        };
    }

    @Override
    public int[] itemsToSell() {
        return new int[] {
                LogType.MAPLE.getItemID(),
                LogType.LOGS.getItemID()
        };
    }
}
