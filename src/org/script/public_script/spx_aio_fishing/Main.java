package org.script.public_script.spx_aio_fishing;

import org.api.script.SPXScript;
import org.api.script.framework.mission.Mission;
import org.api.script.impl.mission.fishing_mission.Fishingmission;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.script.public_script.spx_aio_fishing.data.Args;

import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.FIREMAKING, name = "[SPX] AIO Fishing", desc = "")
public class Main extends SPXScript {

    public static final Args ARGS = new Args();

    @Override
    public Object getArguments() {
        return ARGS;
    }

    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        missions.add(new Fishingmission(this));
        return missions;
    }
}
