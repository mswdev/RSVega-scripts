package sphiinx.script.public_script.spx_aio_walking;

import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import sphiinx.api.SPXScript;
import sphiinx.api.framework.mission.Mission;
import sphiinx.script.public_script.spx_aio_walking.mission.WalkMission;

import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.MONEY_MAKING, name = "SPX AIO Walking", desc = "AIO Walking")
public class Main extends SPXScript {

    @Override
    public Queue<Mission> createMissionQueue() {
        LinkedList<Mission> generated = new LinkedList<>();
        generated.add(new WalkMission());
        return generated;
    }

}

