package sphiinx.script.public_script.spx_aio_walking;

import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import sphiinx.script.public_script.spx_tutorial_island.api.script.SPXScript;
import sphiinx.script.public_script.spx_tutorial_island.api.script.framework.mission.Mission;
import sphiinx.script.public_script.spx_tutorial_island.api.ui.swingui.GUI;
import sphiinx.script.public_script.spx_aio_walking.data.args.Args;
import sphiinx.script.public_script.spx_aio_walking.mission.WalkingMission;

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
        missions.add(new WalkingMission());
        return missions;
    }

    @Override
    public GUI getGUI() {
        return new TempWalkingGUI();
    }
}

