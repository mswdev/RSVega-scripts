package sphiinx.script.private_script.the_bull.house_planking;

import org.rspeer.runetek.api.commons.StopWatch;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import sphiinx.api.script.framework.mission.Mission;
import sphiinx.api.script.SPXScript;
import sphiinx.script.private_script.the_bull.house_planking.data.Args;
import sphiinx.script.private_script.the_bull.house_planking.mission.HousePlankingMission;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.MONEY_MAKING, name = "[P-SPX] House Planking", desc = "")
public class Main extends SPXScript implements RenderListener {

    public static final Args ARGS = new Args();
    private StopWatch stop_watch;

    // [TODO - 2018-11-28]: This is temporary.
    public static int PLANKS_CREATED = 0;

    @Override
    public void onStart() {
        super.onStart();
        stop_watch = StopWatch.start();
    }

    @Override
    public Object getArguments() {
        return ARGS;
    }

    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        missions.add(new HousePlankingMission(this));
        return missions;
    }

    @Override
    public void notify(RenderEvent renderEvent) {
        final Graphics G = renderEvent.getSource();
        G.drawString("[RUNTIME]: " + stop_watch.toElapsedString(), 10, 305);
        G.drawString("[PLANKS]: " + PLANKS_CREATED + " (" + (3600000 / stop_watch.getElapsed().toMillis() * PLANKS_CREATED) + "/hr)", 10, 320);
    }
}

