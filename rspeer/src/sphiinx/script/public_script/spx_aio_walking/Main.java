package sphiinx.script.public_script.spx_aio_walking;

import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import sphiinx.api.SPXScript;
import sphiinx.api.framework.mission.Mission;
import sphiinx.api.framework.ui.javafx.FXGUI;
import sphiinx.script.public_script.spx_aio_walking.mission.WalkingMission;

import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.OTHER, name = "[SPX] AIO Walking", desc = "")
public class Main extends SPXScript {

    @Override
    public Queue<Mission> createMissionQueue() {
        LinkedList<Mission> MISSIONS = new LinkedList<>();
        MISSIONS.add(new WalkingMission());
        return MISSIONS;
    }

    @Override
    public FXGUI getFXGUI() {
        return new WalkingGUI();
    }
}

