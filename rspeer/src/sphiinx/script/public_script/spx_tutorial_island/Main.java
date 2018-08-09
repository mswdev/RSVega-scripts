package sphiinx.script.public_script.spx_tutorial_island;

import org.rspeer.runetek.api.Login;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.event.listeners.GameStateListener;
import org.rspeer.runetek.event.types.GameStateEvent;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.ui.Log;
import sphiinx.api.SPXScript;
import sphiinx.api.framework.mission.Mission;
import sphiinx.api.framework.ui.SPXScriptGUI;
import sphiinx.script.public_script.spx_tutorial_island.data.Vars;
import sphiinx.script.public_script.spx_tutorial_island.mission.TIMission;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.OTHER, name = "[SPX] Tutorial Island", desc = "Completes tutorial island.")
public class Main extends SPXScript implements GameStateListener {

    private final LinkedList<Mission> MISSIONS = new LinkedList<>();
    private String ACCOUNT_DEATILS_FILE_PATH;
    private LinkedHashMap<String, String> account_details = new LinkedHashMap<>();
    private TIMission mission;

    @Override
    public void onStart() {
        loadScriptArgs();
        if (Vars.get().load_accounts_from_list) {
            createAccountMap();
            Log.fine("[ACCOUNT]: Loaded " + account_details.size() + " accounts");
        }

        super.onStart();
    }

    @Override
    public Queue<Mission> createMissionQueue() {
        mission = new TIMission(this, account_details);
        MISSIONS.add(mission);
        return MISSIONS;
    }

    @Override
    public SPXScriptGUI setGUI(SPXScript script) {
        return null;
    }

    private void loadScriptArgs() {
        final String ARG = getArgs();
        if (ARG == null) {
            Log.fine("[ARGS]: No script arguments found; using default settings");
            return;
        }

        //todo CHANGE THIS UP
        Vars.get().at_start_hide_roofs = getArgs().contains("hide_roofs");
        Vars.get().at_start_turn_off_audio = getArgs().contains("turn_off_audio");
        Vars.get().at_start_turn_up_brightness = getArgs().contains("turn_up_brightness");
        Vars.get().at_start_zoom_out = getArgs().contains("zoom_out");
        Vars.get().at_end_drop_items = getArgs().contains("drop_items");
        Vars.get().at_end_bank_items = getArgs().contains("bank_items");
        Vars.get().at_end_get_iron_man_items = getArgs().contains("get_iron_man_items");
        Vars.get().at_end_logout = getArgs().contains("logout");
        Vars.get().at_end_stay_logged_in = getArgs().contains("stay_logged_in");
        Vars.get().at_end_walk_to_location = getArgs().contains("walk_to_location");
        final String[] ARGS = getArgs().split(" ");
        for (String arg : ARGS) {
            if (arg.contains("account_list")) {
                Vars.get().load_accounts_from_list = true;
                ACCOUNT_DEATILS_FILE_PATH = script_data_path + File.separator + arg.replace("-account_list:", "");
            }
            if (arg.contains("walk_to_location")) {
                final String POSITION = arg.replace("-walk_to_location:", "");
                final String[] POSITIONS = POSITION.split(",");
                Vars.get().at_end_walk_position = new Position(Integer.parseInt(POSITIONS[0]), Integer.parseInt(POSITIONS[1]), Integer.parseInt(POSITIONS[2]));
            }
        }
        Log.fine("[ARGS]: Loaded script arguments");
    }

    /**
     * Loads the account username and passwords from the specified file. Accounts must be separated as
     * "username:password"
     *
     * @return A HashMap of the loaded accounts.
     */
    private void createAccountMap() {
        final String DELIMITER = ":";
        try (Stream<String> lines = Files.lines(Paths.get(ACCOUNT_DEATILS_FILE_PATH))) {
            lines.filter(line -> line.contains(DELIMITER))
                    .forEach(line -> account_details.putIfAbsent(line.split(DELIMITER)[0], line.split(DELIMITER)[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notify(GameStateEvent gameStateEvent) {
        //TODO possibly add in setting the correct world.
        //Arrays.stream(LoginAccount.getResponseLines()).anyMatch(str -> str.contains("You need a members account"))
        if (Login.getState() == 3 || Login.getState() == 4) {
            Log.severe("[ACCOUNT]: Failed to login to account; moving to next.");
            mission.setNextAccount();
        }
    }

    @Override
    public void onStop() {
        //TODO Remove this soon, only using it to show accs that completed tutorial island.
        /*Log.severe("ACCOUNTS THAT HAVE COMPLETED TUTORIAL ISLAND");
        for (String account : Vars.get().TEMP)
            Log.info(account);*/

        super.onStop();
    }
}

