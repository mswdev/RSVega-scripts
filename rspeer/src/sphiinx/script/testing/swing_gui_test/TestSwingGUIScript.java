package sphiinx.script.testing.swing_gui_test;

import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import sphiinx.api.script.SPXScript;
import sphiinx.api.script.framework.mission.Mission;
import sphiinx.api.ui.swingui.GUI;

import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.TOOL, name = "[SPX] Test Swing GUI Script", desc = "Test Swing GUI Script")
public class TestSwingGUIScript extends SPXScript {

    @Override
    public Queue<Mission> createMissionQueue() {
        return null;
    }

    @Override
    public GUI getGUI() {
        return new TestSwingGUI();
    }
}

