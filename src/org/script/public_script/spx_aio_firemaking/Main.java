package org.script.public_script.spx_aio_firemaking;

import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.api.script.framework.mission.Mission;
import org.api.script.SPXScript;
import org.script.public_script.spx_aio_firemaking.data.Args;
import org.api.script.impl.mission.firemaking_mission.FireMakingMission;

import java.util.LinkedList;
import java.util.Queue;

// [TODO - 2018-10-26]: Experiment with a linear no branching design style. Test it out with this script.
@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.FIREMAKING, name = "[SPX] AIO Firemaking", desc = "")
public class Main extends SPXScript {

    public static final Args ARGS = new Args();

    @Override
    public Object getArguments() {
        return ARGS;
    }

    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        missions.add(new FireMakingMission(this));
        return missions;
    }
}
