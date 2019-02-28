package org.api.script.impl.mission.tutorial_island_mission.worker.impl.at_start;

import org.api.script.framework.worker.Worker;
import org.api.script.impl.mission.tutorial_island_mission.TutorialIslandMission;
import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.Varps;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.EnterInput;
import org.rspeer.runetek.api.component.Interfaces;

public class CharacterDisplayNameWorker extends Worker {

    public static final int DISPLAY_NAME_VARPBIT = 5605;
    private static final int CHOOSE_DISPLAY_NAME_INTER = 558;
    private static final int CHOOSE_DISPLAY_NAME_LOOKUP_INTER = 17;
    private static final int CHOOSE_DISPLAY_NAME_SET_INTER = 18;
    private final TutorialIslandMission mission;
    private int lookup_count;

    public CharacterDisplayNameWorker(TutorialIslandMission mission) {
        this.mission = mission;
    }

    @Override
    public boolean needsRepeat() {
        return Varps.getBitValue(DISPLAY_NAME_VARPBIT) != 0;
    }

    @Override
    public void work() {
        if (Varps.getBitValue(DISPLAY_NAME_VARPBIT) != 4) {
            lookup_count++;
            final InterfaceComponent lookup_display_name = Interfaces.getComponent(CHOOSE_DISPLAY_NAME_INTER, CHOOSE_DISPLAY_NAME_LOOKUP_INTER);
            if (lookup_display_name == null)
                return;

            if (!EnterInput.isOpen()) {
                lookup_display_name.click();
                Time.sleepUntil(EnterInput::isOpen, 1500);
            }

            String display_name = mission.getUsername().split("@")[0];
            if (lookup_count > 1)
                display_name += Random.nextInt(0, 1000);

            EnterInput.initiate(display_name);
            return;
        }

        final InterfaceComponent set_display_name = Interfaces.getComponent(CHOOSE_DISPLAY_NAME_INTER, CHOOSE_DISPLAY_NAME_SET_INTER);
        if (set_display_name == null)
            return;

        lookup_count = 0;
        set_display_name.click();
    }

    @Override
    public String toString() {
        return "Executing character display name worker.";
    }
}

