package antiban.script.public_script.antiban_flax_picker;

import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Ending;
import antiban.api.AntibanScript;
import antiban.api.gui.GUI;
import antiban.priority_framework.PriorityEvent;
import antiban.script.public_script.antiban_flax_picker.events.BankItems;
import antiban.script.public_script.antiban_flax_picker.events.PickFlax;
import antiban.script.public_script.antiban_flax_picker.events.WalkToBank;
import antiban.script.public_script.antiban_flax_picker.events.WalkToFlax;

@ScriptManifest(authors = "Antiban", category = "Money Making", name = "Antiban Flax Picker")
public class Main extends AntibanScript implements Ending {

    @Override
    protected GUI getGUI() {
        return new GUI("https://pastebin.com/raw/bL44RtEF");
    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    public void addEvents(PriorityEvent... events) {
        super.addEvents(new BankItems(), new WalkToBank(), new WalkToFlax(), new PickFlax());
    }

}
