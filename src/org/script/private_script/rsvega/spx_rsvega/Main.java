package org.script.private_script.rsvega.spx_rsvega;

import org.api.script.SPXScript;
import org.api.script.framework.mission.Mission;
import org.api.script.impl.mission.fishing_mission.Fishingmission;
import org.api.script.impl.mission.questing.restless_ghost_mission.RestlessGhostMission;
import org.api.script.impl.mission.questing.romeo_and_juliet.RomeoAndJulietMission;
import org.api.script.impl.mission.questing.rune_mysteries_mission.RuneMysteriesMission;
import org.api.script.impl.mission.questing.sheep_shearer_mission.SheepShearerMission;
import org.api.script.impl.mission.tutorial_island_mission.TutorialIslandMission;
import org.api.script.impl.mission.tutorial_island_mission.data.args.Args;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.TOOL, name = "[SPX] RSVega", desc = "[SPX] RSVega")
public class Main extends SPXScript {

    private final Args args = new Args();

    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        missions.add(new SheepShearerMission(this));
        missions.add(new RuneMysteriesMission(this));
        missions.add(new RestlessGhostMission(this));
        missions.add(new RomeoAndJulietMission(this));
        Collections.shuffle(missions);

        if (getArgs() != null && getArgs().length() > 0)
            missions.addFirst(new TutorialIslandMission(this, args, new HashMap<>(), true));

        missions.addLast(new Fishingmission(this));
        return missions;
    }

    @Override
    public Object getArguments() {
        return args;
    }
}
