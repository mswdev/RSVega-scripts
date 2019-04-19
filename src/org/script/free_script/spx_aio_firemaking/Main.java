package org.script.free_script.spx_aio_firemaking;

import org.api.script.SPXScript;
import org.api.script.framework.mission.Mission;
import org.api.script.impl.mission.firemaking_mission.FireMakingMission;
import org.api.script.impl.mission.firemaking_mission.data.Args;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;

import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.FIREMAKING, name = "[SPX] AIO Firemaking", desc = "")
public class Main extends SPXScript {

    private static final Args ARGS = new Args();

    @Override
    public Object getArguments() {
        return ARGS;
    }


    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        missions.add(new FireMakingMission(this, ARGS));
        return missions;
    }
}

