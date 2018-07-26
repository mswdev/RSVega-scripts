package sphiinx.script.public_script.spx_aio_woodcutting;

import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import sphiinx.script.public_script.spx_tutorial_island.api.SPXScript;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.mission.Mission;

import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.WOODCUTTING, name = "SPX AIO Woodcutting", desc = "AIO Woodcutting")
public class Main extends SPXScript {

    @Override
    public Queue<Mission> createMissionQueue() {
        return null;
    }

}

