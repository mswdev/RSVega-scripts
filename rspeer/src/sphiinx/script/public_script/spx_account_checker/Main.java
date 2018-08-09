package sphiinx.script.public_script.spx_account_checker;

import org.rspeer.runetek.event.listeners.GameStateListener;
import org.rspeer.runetek.event.types.GameStateEvent;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.events.LoginScreen;
import org.rspeer.script.events.WelcomeScreen;
import sphiinx.api.SPXScript;
import sphiinx.api.framework.mission.Mission;
import sphiinx.api.framework.ui.SPXScriptGUI;
import sphiinx.script.public_script.spx_account_checker.mission.AccountCheckerMission;
import sphiinx.script.public_script.spx_account_checker.mission.worker.impl.LoginAccount.BlockLoginEvent;
import sphiinx.script.public_script.spx_account_checker.mission.worker.impl.LoginAccount.BlockWelcomeScreenEvent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.TOOL, name = "[SPX] Account Checker", desc = "Checks Accounts")
public class Main extends SPXScript implements GameStateListener {

    private final String ACCOUNT_DEATILS_FILE_PATH = "/Users/matt/Desktop/Runescape/Accounts/test_list.txt";
    private final LinkedHashMap<String, String> ACCOUNT_DETAILS = new LinkedHashMap<>();

    @Override
    public void onStart() {
        removeBlockingEvent(WelcomeScreen.class);
        removeBlockingEvent(LoginScreen.class);
        addBlockingEvent(new BlockWelcomeScreenEvent(this));
        addBlockingEvent(new BlockLoginEvent(this));
        createAccountMap();
        super.onStart();
    }

    @Override
    public Queue<Mission> createMissionQueue() {
        LinkedList<Mission> MISSIONS = new LinkedList<>();
        MISSIONS.add(new AccountCheckerMission(this, ACCOUNT_DETAILS));
        return MISSIONS;
    }

    @Override
    public SPXScriptGUI setGUI(SPXScript script) {
        return null;
    }

    private void createAccountMap() {
        final String DELIMITER = ":";
        try (Stream<String> lines = Files.lines(Paths.get(ACCOUNT_DEATILS_FILE_PATH))) {
            lines.filter(line -> line.contains(DELIMITER))
                    .forEach(line -> ACCOUNT_DETAILS.putIfAbsent(line.split(DELIMITER)[0], line.split(DELIMITER)[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notify(GameStateEvent gameStateEvent) {

    }
}

