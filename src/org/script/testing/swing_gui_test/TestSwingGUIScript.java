package org.script.testing.swing_gui_test;

import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.api.script.SPXScript;
import org.api.script.framework.mission.Mission;
import org.api.ui.swingui.GUI;

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

