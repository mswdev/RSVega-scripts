package sphiinx.script.private_script.zerker.revenants;

import com.beust.jcommander.JCommander;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.mission.Mission;
import sphiinx.script.public_script.spx_tutorial_island.api.framework.script.SPXScript;
import sphiinx.script.private_script.zerker.revenants.data.Args;
import sphiinx.script.private_script.zerker.revenants.mission.RevenantMission;

import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.SMITHING, name = "[P-SPX] Revenants", desc = "")
public class Main extends SPXScript {

    private static final Args ARGS = new Args();

    @Override
    public void onStart() {
        JCommander.newBuilder()
                .addObject(ARGS)
                .build()
                .parse(getArgs());
    }

    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        missions.add(new RevenantMission());
        return missions;
    }

    public static Args getParsedArgs() {
        return ARGS;
    }
}

