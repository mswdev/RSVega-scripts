package org.script.public_script.spx_tutorial_island;

import org.rspeer.runetek.event.listeners.LoginResponseListener;
import org.rspeer.runetek.event.types.LoginResponseEvent;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.events.LoginScreen;
import org.rspeer.ui.Log;
import org.api.script.SPXScript;
import org.api.script.framework.mission.Mission;
import org.api.script.impl.mission.tutorial_island_mission.TutorialIslandMission;
import org.api.script.impl.mission.tutorial_island_mission.data.args.Args;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

// [TODO - 2018-10-30]: Add script statics api such as my logo, SPX name, etc..
// [TODO - 2018-10-30]: Convert everything to a mission in my api and then the script pulls from that..
@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.OTHER, name = "[SPX] Tutorial Island", desc = "")
public class Main extends SPXScript implements LoginResponseListener {

    public final Args args = new Args();

    @Override
    public void onStart() {
        removeBlockingEvent(LoginScreen.class);
        addBlockingEvent(new BlockLoginEvent(this));
        super.onStart();
    }

    @Override
    public Object getArguments() {
        return args;
    }

    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        if (!getAccount().getUsername().isEmpty() && !getAccount().getPassword().isEmpty())
            missions.add(new TutorialIslandMission(this, args, getAccount().getUsername(), getAccount().getPassword()));

        final String delimiter = ":";
        try (Stream<String> lines = Files.lines(Paths.get(script_data_path + File.separator + args.load_accounts))) {
            lines.filter(line -> line.contains(delimiter))
                    .forEach(line -> missions.add(new TutorialIslandMission(this, args, line.split(delimiter)[0], line.split(delimiter)[1])));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.fine("Loaded " + missions.size() + " account(s)");
        return missions;
    }

    @Override
    public void notify(LoginResponseEvent loginResponseEvent) {
        Log.severe("Login failed; setting next account.");
        setAccount(null);
        getMissionHandler().endCurrent();
    }
}

