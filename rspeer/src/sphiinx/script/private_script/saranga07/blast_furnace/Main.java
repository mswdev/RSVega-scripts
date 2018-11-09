package sphiinx.script.private_script.saranga07.blast_furnace;

import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.mission.Mission;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.SPXScript;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.ui.swingui.GUI;
import sphiinx.script.private_script.saranga07.blast_furnace.mission.BlastFurnaceMission;

import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.SMITHING, name = "[P-SPX] Blast Furnace", desc = "")
public class Main extends SPXScript {


    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        missions.add(new BlastFurnaceMission());
        return missions;
    }

    @Override
    public GUI getGUI() {
        return new BlastFurnaceUI();
    }
}

