package org.script.testing.test_script_mission;

import org.api.script.SPXScript;
import org.api.script.framework.mission.Mission;
import org.api.script.impl.mission.tutorial_island_mission.data.args.Args;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.script.testing.test_script_mission.mission.TestScriptMission;

import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.TOOL, name = "[SPX] Test Script Mission", desc = "Test Script Mission")
public class TestScript extends SPXScript {

    private final Args args = new Args();

    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        /*missions.add(new TutorialIslandMission(this, args, getAccount().getUsername(), getAccount().getPassword()));
        missions.add(new SheepShearerMission(this));
        missions.add(new RuneMysteriesMission(this));
        missions.add(new RestlessGhostMission(this));
        missions.add(new RomeoAndJulietMission(this));
        missions.add(new Fishingmission(this));*/
        missions.add(new TestScriptMission(this));
        return missions;
    }

    @Override
    public Object getArguments() {
        return args;
    }
}
