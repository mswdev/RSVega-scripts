package org.script.free_script.spx_tutorial_island_lite;

import org.api.script.SPXScript;
import org.api.script.framework.mission.Mission;
import org.api.script.impl.mission.tutorial_island_mission.TutorialIslandMission;
import org.api.script.impl.mission.tutorial_island_mission.data.args.Args;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.ui.Log;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.OTHER, name = "[SPX] Tutorial Island Lite", desc = "[SPX] Tutorial Island Lite")
public class Main extends SPXScript {

    public final Args args = new Args();

    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        final HashMap<String, String> accountData = new HashMap<>();

        accountData.put("username", getAccount().getUsername());
        accountData.put("password", getAccount().getPassword());
        missions.add(new TutorialIslandMission(this, args, accountData, false));
        Log.fine("Loaded " + missions.size() + " account(s)");
        return missions;
    }
}

