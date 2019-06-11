package org.script.free_script.spx_aio_questing;

import org.api.game.questing.QuestType;
import org.api.script.SPXScript;
import org.api.script.framework.mission.Mission;
import org.api.script.impl.mission.questing.data.Args;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.ui.Log;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

@ScriptMeta(developer = "Sphiinx", category = ScriptCategory.QUESTING, name = "[SPX] AIO Questing", desc = "[SPX] AIO Questing")
public class Main extends SPXScript {

    private static final Args ARGS = new Args();

    @Override
    public Queue<Mission> createMissionQueue() {
        final LinkedList<Mission> missions = new LinkedList<>();
        if (ARGS.sevenQp) {
            int questPoints = 0;
            for (QuestType questType : QuestType.values()) {
                if (questPoints >= 7)
                    break;

                if (questType.getMission() == null)
                    continue;

                missions.add(questType.getMission());
                questPoints += questType.getPoints();
            }
        } else {
            for (QuestType questType : ARGS.questTypes) {
                missions.add(questType.getMission());
            }
        }

        if (ARGS.randomizeOrder)
            Collections.shuffle(missions);

        Log.fine(Arrays.toString(missions.toArray()));
        return missions;
    }

    @Override
    public Object getArguments() {
        return ARGS;
    }
}
