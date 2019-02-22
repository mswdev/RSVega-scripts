package sphiinx.script.testing.data_testing.data_test_mission_1;

import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import sphiinx.api.script.SPXScript;
import sphiinx.api.script.framework.mission.Mission;
import sphiinx.script.testing.data_testing.data_test_mission_1.mission.DataTestMission;

import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.TOOL, name = "[SPX] Data Test Mission 1", desc = "Data Test Mission 1")
public class Main extends SPXScript {

    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        missions.add(new DataTestMission(this));
        return missions;
    }

}
