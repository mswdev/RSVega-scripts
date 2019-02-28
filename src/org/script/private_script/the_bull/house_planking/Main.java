package org.script.private_script.the_bull.house_planking;

import org.api.script.SPXScript;
import org.api.script.framework.mission.Mission;
import org.rspeer.runetek.api.commons.StopWatch;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.script.private_script.the_bull.house_planking.data.Args;
import org.script.private_script.the_bull.house_planking.mission.HousePlankingMission;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.MONEY_MAKING, name = "[P-SPX] House Planking", desc = "")
public class Main extends SPXScript implements RenderListener {

    public static int PLANKS_CREATED = 0;
    private Args args = new Args();
    private StopWatch stop_watch;

    @Override
    public void onStart() {
        super.onStart();
        stop_watch = StopWatch.start();
    }

    @Override
    public Object getArguments() {
        return args;
    }

    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            missions.add(new HousePlankingMission(this, args.LOG_TYPE1, args.LOG_TYPE1_QUANTITY));
            missions.add(new HousePlankingMission(this, args.LOG_TYPE2, args.LOG_TYPE2_QUANTITY));
        }
        return missions;
    }

    @Override
    public void notify(RenderEvent renderEvent) {
        final Graphics G = renderEvent.getSource();
        G.drawString("[RUNTIME]: " + stop_watch.toElapsedString(), 10, 305);
        G.drawString("[PLANKS]: " + PLANKS_CREATED + " (" + (3600000 / stop_watch.getElapsed().toMillis() * PLANKS_CREATED) + "/hr)", 10, 320);
    }
}

