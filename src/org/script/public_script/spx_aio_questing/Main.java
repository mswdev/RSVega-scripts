package org.script.public_script.spx_aio_questing;

import org.api.script.SPXScript;
import org.api.script.framework.mission.Mission;
import org.api.script.impl.mission.questing.sheep_shearer_mission.SheepShearerMission;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;

import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.QUESTING, name = "[SPX] AIO Questing", desc = "")
public class Main extends SPXScript {


    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        missions.add(new SheepShearerMission(this));
        //missions.add(new RuneMysteriesMission(this));
        //missions.add(new RestlessGhostMission(this));
        //missions.add(new RomeoAndJulietMission(this));
        return missions;
    }
}
