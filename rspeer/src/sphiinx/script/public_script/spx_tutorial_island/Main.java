package sphiinx.script.public_script.spx_tutorial_island;

import org.rspeer.runetek.api.Login;
import org.rspeer.runetek.event.listeners.GameStateListener;
import org.rspeer.runetek.event.types.GameStateEvent;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.ui.Log;
import sphiinx.api.SPXScript;
import sphiinx.api.framework.mission.Mission;
import sphiinx.script.public_script.spx_tutorial_island.data.Vars;
import sphiinx.script.public_script.spx_tutorial_island.mission.TI_Mission;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.OTHER, name = "SPX Tutorial Island", desc = "Completes tutorial island.")
public class Main extends SPXScript implements GameStateListener {

    private LinkedHashMap<String, String> account_details = new LinkedHashMap<>();
    private TI_Mission mission;
    private final LinkedList<Mission> MISSIONS = new LinkedList<>();
    private final String ACCOUNT_DEATILS_FILE_PATH = getDataDirectory() + File.separator + "SPX" + File.separator + getMeta().name() + File.separator + "accounts.txt";

    @Override
    public void onStart() {
        loadScriptArgs();
        account_details = loadAccounts();
        Log.fine("[ACCOUNT]: Loaded " + account_details.size() + " accounts");
        super.onStart();
    }

    @Override
    public Queue<Mission> createMissionQueue() {
        mission = new TI_Mission(this, account_details);
        MISSIONS.add(mission);
        return MISSIONS;
    }

    @Override
    public void loadScriptArgs() {
        super.loadScriptArgs();
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
    }

    /**
     * Loads the account username and passwords from the specified file. Accounts must be separated as
     * "username:password"
     *
     * @return A HashMap of the loaded accounts.
     */
    private LinkedHashMap<String, String> loadAccounts() {
        final LinkedHashMap<String, String> ACCOUNTS = new LinkedHashMap<>();
        try {
            final BufferedReader READER = new BufferedReader(new FileReader(ACCOUNT_DEATILS_FILE_PATH));
            String line = READER.readLine();
            while (line != null) {
                final String[] ACCOUNT_DETAILS = line.split(":");
                if (ACCOUNT_DETAILS.length <= 1)
                    continue;

                ACCOUNTS.put(ACCOUNT_DETAILS[0], ACCOUNT_DETAILS[1]);
                line = READER.readLine();
            }
            READER.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ACCOUNTS;
    }

    @Override
    public void notify(GameStateEvent gameStateEvent) {
        //TODO possibly add in setting the correct world.
        //Arrays.stream(Login.getResponseLines()).anyMatch(str -> str.contains("You need a members account"))
        if (Login.getState() == 3 || Login.getState() == 4) {
            Log.severe("[ACCOUNT]: Failed to login to account; moving to next.");
            mission.setNextAccount();
        }
    }

    @Override
    public void onStop() {
        //TODO Remove this soon, only using it to show accs that completed tutorial island.
        Log.severe("ACCOUNTS THAT HAVE COMPLETED TUTORIAL ISLAND");
        for (String account : Vars.get().TEMP)
            Log.info(account);

        super.onStop();
    }
}

