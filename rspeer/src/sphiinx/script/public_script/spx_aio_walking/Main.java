package sphiinx.script.public_script.spx_aio_walking;

import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import sphiinx.api.SPXScript;
import sphiinx.api.framework.mission.Mission;
import sphiinx.api.framework.ui.SPXScriptGUI;
import sphiinx.script.public_script.spx_aio_walking.mission.WalkingMission;

import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.FIREMAKING, name = "[SPX] AIO Walking", desc = "AIO Walking")
public class Main extends SPXScript {

    @Override
    public Queue<Mission> createMissionQueue() {
        LinkedList<Mission> MISSIONS = new LinkedList<>();
        MISSIONS.add(new WalkingMission(null));
        return MISSIONS;
    }

    @Override
    public SPXScriptGUI setGUI(SPXScript script) {
        return new WalkingGUI(script);
    }

}

