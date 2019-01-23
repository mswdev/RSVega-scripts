package sphiinx.script.private_script.wyd.nmz;

import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import sphiinx.api.script.framework.mission.Mission;
import sphiinx.api.script.SPXScript;
import sphiinx.script.private_script.wyd.nmz.data.Args;
import sphiinx.api.script.impl.mission.nmz_mission.NMZMission;

import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.COMBAT, name = "[P-SPX] NMZ", desc = "")
public class Main extends SPXScript {


    public static final Args ARGS = new Args();

    @Override
    public Object getArguments() {
        return ARGS;
    }

    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        missions.add(new NMZMission());
        return missions;
    }
}

