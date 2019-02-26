package org.script.public_script.spx_aio_walking;

import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.api.script.SPXScript;
import org.api.script.framework.mission.Mission;
import org.api.ui.swingui.GUI;
import org.script.public_script.spx_aio_walking.data.args.Args;
import org.script.public_script.spx_aio_walking.mission.WalkingMission;

import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.OTHER, name = "[SPX] AIO Walking", desc = "")
public class Main extends SPXScript {

    public static final Args ARGS = new Args();

    @Override
    public Object getArguments() {
        return ARGS;
    }

    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        missions.add(new WalkingMission(this));
        return missions;
    }

    @Override
    public GUI getGUI() {
        return new org.script.public_script.spx_aio_walking.TempWalkingGUI();
    }
}

