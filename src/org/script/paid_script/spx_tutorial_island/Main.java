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
import java.util.HashMap;
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
                            final HashMap<String, String> accountData = new HashMap<>();
                            final String[] proxyData = line.split(delimiter);
                            accountData.put("socks_ip", proxyData[0]);
                            accountData.put("socks_port", proxyData[1]);

                            if (proxyData.length > 2) {
                                accountData.put("socks_username", line.split(delimiter)[2]);
                                accountData.put("socks_password", line.split(delimiter)[3]);
                            }
                            missions.add(new TutorialIslandMission(this, args, accountData, true));
                        }
                    });
        } catch (IOException e) {
            for (int i = 0; i < args.accounts_to_create; i++) {
                final HashMap<String, String> accountData = new HashMap<>();
                missions.add(new TutorialIslandMission(this, args, accountData, true));
            }
        }

        if (!getAccount().getUsername().isEmpty() && !getAccount().getPassword().isEmpty()) {
            final HashMap<String, String> accountData = new HashMap<>();
            accountData.put("email", getAccount().getUsername());
            accountData.put("password", getAccount().getPassword());
            missions.add(new TutorialIslandMission(this, args, accountData, false));
        }

        try (Stream<String> lines = Files.lines(Paths.get(script_data_path + File.separator + args.account_list))) {
            lines.filter(line -> line.contains(delimiter))
                    .forEach(line -> {
                        final HashMap<String, String> accountData = new HashMap<>();
                        final String[] accountDetails = line.split(delimiter);
                        accountData.put("email", accountDetails[0]);
                        accountData.put("password", accountDetails[1]);

                        if (accountDetails.length > 2) {
                            accountData.put("socks_ip", accountDetails[2]);
                            accountData.put("socks_port", accountDetails[3]);
                        }

                        if (accountDetails.length > 4) {
                            accountData.put("socks_username", accountDetails[4]);
                            accountData.put("socks_password", accountDetails[5]);
                        }
                        missions.add(new TutorialIslandMission(this, args, accountData, false));
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.log(Level.WARNING, "Info", "[Total Accounts]: " + missions.size() + " | [Loaded File]: " + args.account_list + "| [Loaded Proxy File]: " + args.proxy_list);
        return missions;
    }

    @Override
    public void notify(LoginResponseEvent loginResponseEvent) {
        Log.severe("Login failed; setting next account.");
        setAccount(null);
        getMissionHandler().endCurrent();
    }
}

