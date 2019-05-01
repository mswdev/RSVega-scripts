package org.script.private_script.wyd.nmz;

import org.api.script.SPXScript;
import org.api.script.framework.mission.Mission;
import org.api.script.impl.mission.nmz_mission.NMZMission;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;

import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.COMBAT, name = "[P-SPX] NMZ", desc = "")
public class Main extends SPXScript {

    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        missions.add(new NMZMission(this));
        return missions;
    }
}

