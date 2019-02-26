package org.script.testing.data_testing.data_test_mission_2;

import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.api.script.SPXScript;
import org.api.script.framework.mission.Mission;
import org.script.testing.data_testing.data_test_mission_2.mission.DataTestMission;

import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.TOOL, name = "[SPX] Data Test Mission 2", desc = "Data Test Mission 2")
public class Main extends SPXScript {

    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        missions.add(new DataTestMission(this));
        return missions;
    }

}