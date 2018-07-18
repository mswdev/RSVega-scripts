package antiban.script.public_script.antiban_account_checker;

import org.tribot.api.General;
import org.tribot.api.input.Keyboard;
import org.tribot.api.input.Mouse;
import org.tribot.script.ScriptManifest;
import antiban.api.AntibanScript;
import antiban.api.gui.GUI;
import antiban.priority_framework.PriorityEvent;
import antiban.script.public_script.antiban_account_checker.data.Vars;
import antiban.script.public_script.antiban_account_checker.events.GeneralData;
import antiban.script.public_script.antiban_account_checker.events.LoginToAccount;
import antiban.script.public_script.antiban_account_checker.events.LogoutOfAccount;

import java.io.*;

@ScriptManifest(authors = "Antiban", category = "Tools", name = "Antiban Account Checker")
public class Main extends AntibanScript {

    private final String ACCOUNT_DEATILS_FILE_PATH = "C:\\Users\\07Scripting\\Desktop\\Account Lists\\test_list.txt";

    @Override
    protected GUI getGUI() {
        return null;
    }

    @Override
    public void run() {
        Vars.reset();
        CLIENT_SOCKET_MANAGER.initialize();
        Keyboard.setSpeed(0.1);
        Mouse.setSpeed(1000);
        this.setLoginBotState(false);
        createAccountMap();
        super.run();
    }

    @Override
    public void addEvents(PriorityEvent... events) {
        super.addEvents(new GeneralData(CLIENT_SOCKET_MANAGER), new LogoutOfAccount(), new LoginToAccount());
    }

    private void createAccountMap() {
        try {
            final BufferedReader READER = new BufferedReader(new FileReader(ACCOUNT_DEATILS_FILE_PATH));

            String line = READER.readLine();
            while (line != null) {
                final String[] ACCOUNT_DETAILS = line.split(":");
                if (ACCOUNT_DETAILS.length <= 1)
                    continue;

                Vars.get().account_details.add(ACCOUNT_DETAILS[0] + ":" +  ACCOUNT_DETAILS[1]);
                line = READER.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        General.println("Accounts Loaded: " + Vars.get().account_details.size());
    }

}
