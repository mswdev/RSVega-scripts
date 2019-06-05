package org.script.paid_script.spx_account_creator;

import org.api.script.SPXScript;
import org.api.script.SPXScriptUtil;
import org.api.script.framework.mission.Mission;
import org.api.script.impl.mission.account_creation_mission.AccountCreationMission;
import org.api.script.impl.mission.account_creation_mission.data.Args;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.events.LoginScreen;
import org.rspeer.ui.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.stream.Stream;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.OTHER, name = "[SPX] Account Creator", desc = "[SPX] Account Creator")
public class Main extends SPXScript {

    public final Args args = new Args();

    @Override
    public void onStart() {
        removeBlockingEvent(LoginScreen.class);
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

        final Path proxyPath = Paths.get(SPXScriptUtil.getScriptDataPath(getMeta().name()) + File.separator + args.proxyList);
        if (proxyPath.toFile().exists()) {
            try (Stream<String> lines = Files.lines(proxyPath)) {
                lines.filter(line -> line.contains(delimiter))
                        .forEach(line -> {
                            for (int i = 0; i < args.accountsPerProxy; i++) {
                                final HashMap<String, String> accountData = new HashMap<>();
                                final String[] proxyData = line.split(delimiter);
                                accountData.put("socks_ip", proxyData[0]);
                                accountData.put("socks_port", proxyData[1]);

                                if (proxyData.length > 2) {
                                    accountData.put("socks_username", line.split(delimiter)[2]);
                                    accountData.put("socks_password", line.split(delimiter)[3]);
                                }
                                missions.add(new AccountCreationMission(this, args, accountData));
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            for (int i = 0; i < args.accountsToCreate; i++) {
                final HashMap<String, String> accountData = new HashMap<>();
                missions.add(new AccountCreationMission(this, args, accountData));
            }
        }

        final Path accountList = Paths.get(SPXScriptUtil.getScriptDataPath(getMeta().name()) + File.separator + args.accountList);
        if (accountList.toFile().exists()) {
            try (Stream<String> lines = Files.lines(accountList)) {
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
                            missions.add(new AccountCreationMission(this, args, accountData));
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Log.log(Level.WARNING, "Info", "[Total Accounts]: " + missions.size() + " | [Loaded Accounts File]: " + args.accountList + "| [Loaded Proxy File]: " + args.proxyList);
        return missions;
    }
}
