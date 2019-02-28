package org.script.private_script.saranga07.blast_furnace;

import org.api.script.SPXScript;
import org.api.script.framework.mission.Mission;
import org.api.script.impl.mission.blast_furnace_mission.BlastFurnaceMission;
import org.api.ui.swingui.GUI;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;

import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.SMITHING, name = "[P-SPX] Blast Furnace", desc = "")
public class Main extends SPXScript {


    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        missions.add(new BlastFurnaceMission(this));
        return missions;
    }

    @Override
    public GUI getGUI() {
        return new BlastFurnaceUI();
    }
}

