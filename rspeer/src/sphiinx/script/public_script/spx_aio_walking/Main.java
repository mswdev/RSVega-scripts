package sphiinx.script.public_script.spx_aio_walking;

import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.mission.Mission;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.SPXScript;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.ui.fxui.FXGUI;
import sphiinx.script.public_script.spx_aio_walking.mission.WalkingMission;

import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.OTHER, name = "[SPX] AIO Walking", desc = "")
public class Main extends SPXScript {

    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        missions.add(new WalkingMission());
        return missions;
    }

    @Override
    public FXGUI getFXGUI() {
        return new WalkingGUI();
    }

}

