package org.script.paid_script.spx_tutorial_island;

import org.api.script.SPXScript;
import org.api.script.framework.mission.Mission;
import org.api.script.impl.mission.tutorial_island_mission.TutorialIslandMission;
import org.api.script.impl.mission.tutorial_island_mission.data.args.Args;
import org.rspeer.runetek.event.listeners.LoginResponseListener;
import org.rspeer.runetek.event.types.LoginResponseEvent;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.events.LoginScreen;
import org.rspeer.ui.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.stream.Stream;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.OTHER, name = "[SPX] Tutorial Island", desc = "[SPX] Tutorial Island")
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

        final String delimiter = ":";
        try (Stream<String> lines = Files.lines(Paths.get(script_data_path + File.separator + args.proxy_list))) {
            lines.filter(line -> line.contains(delimiter))
                    .forEach(line -> {
                        for (int i = 0; i < args.accounts_per_proxy; i++) {
                            missions.add(new TutorialIslandMission(this, args, true, null, null, line.split(delimiter)[0], line.split(delimiter)[1], line.split(delimiter)[2], line.split(delimiter)[3]));
                        }
                    });
        } catch (IOException e) {
            Log.fine("Proxy file not found; using no proxy.");
            for (int i = 0; i < args.accounts_to_create; i++) {
                missions.add(new TutorialIslandMission(this, args, true, null, null));
            }
        }

        if (!getAccount().getUsername().isEmpty() && !getAccount().getPassword().isEmpty())
            missions.add(new TutorialIslandMission(this, args, false, getAccount().getUsername(), getAccount().getPassword()));

        try (Stream<String> lines = Files.lines(Paths.get(script_data_path + File.separator + args.account_list))) {
            lines.filter(line -> line.contains(delimiter))
                    .forEach(line -> missions.add(new TutorialIslandMission(this, args, false, line.split(delimiter)[0], line.split(delimiter)[1])));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.log(Level.WARNING, "Info", "[Accounts To Create]: " + args.accounts_to_create + " | [Loaded Proxy File]: " + args.proxy_list);
        Log.log(Level.WARNING, "Info", "[Loaded Accounts]: " + missions.size() + " | [Loaded File]: " + args.account_list);
        return missions;
    }

    @Override
    public void notify(LoginResponseEvent loginResponseEvent) {
        Log.severe("Login failed; setting next account.");
        setAccount(null);
        getMissionHandler().endCurrent();
    }
}

