package org.script.free_script.spx_tutorial_island_lite;

import org.api.script.SPXScript;
import org.api.script.framework.mission.Mission;
import org.api.script.impl.mission.tutorial_island_mission.TutorialIslandMission;
import org.api.script.impl.mission.tutorial_island_mission.data.args.Args;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.events.LoginScreen;
import org.rspeer.ui.Log;
import org.script.free_script.spx_account_checker.mission.worker.impl.LoginAccount.BlockLoginEvent;

import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.OTHER, name = "[SPX] Tutorial Island Lite", desc = "[SPX] Tutorial Island Lite")
public class Main extends SPXScript {

    public final Args args = new Args();

    @Override
    public void onStart() {
        removeBlockingEvent(LoginScreen.class);
        addBlockingEvent(new BlockLoginEvent(this));
        super.onStart();
    }

    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        missions.add(new TutorialIslandMission(this, args, getAccount().getUsername(), getAccount().getPassword()));
        Log.fine("Loaded " + missions.size() + " account(s)");
        return missions;
    }
}
