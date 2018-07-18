package antiban.script.public_script.antiban_test;

import org.tribot.script.ScriptManifest;
import antiban.api.AntibanScript;
import antiban.api.gui.GUI;
import antiban.priority_framework.PriorityEvent;
import antiban.script.public_script.antiban_test.events.Test;

@ScriptManifest(authors = "Antiban", category = "Tools", name = "Antiban Test")
public class Main extends AntibanScript {

    @Override
    protected GUI getGUI() {
        return null;
    }

    @Override
    public void run() {
        this.setLoginBotState(false);
        super.run();
    }

    @Override
    public void addEvents(PriorityEvent... events) {
        super.addEvents(new Test());
    }
}
